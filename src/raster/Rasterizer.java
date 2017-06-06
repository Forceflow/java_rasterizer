package raster;

import java.awt.Panel;
import java.util.ArrayList;

import shading.Noise;
import support.CgPanel;
import support.Color3f;
import support.Vector4f;
import util.Camerasystem;
import util.Constants;
import util.Light;
import util.Lightsystem;
import util.Point4f;
import util.ScreenTriangle;
import util.WorldTriangle;

public class Rasterizer 
{
	private Camerasystem cameras;
	private Lightsystem lights;
	private CgPanel panel;
	private Color3f[][] image;
	private Transformer trans;
	
	public Rasterizer(Camerasystem c, Lightsystem ll, CgPanel g)
	{
		this.cameras = c;
		this.lights = ll;
		this.panel = g;
		trans = new Transformer(cameras);
		image = new Color3f[c.getScreenx()][c.getScreeny()];
	}
	
	public Rasterizer(Camerasystem c, Lightsystem ll)
	{
		this.cameras = c;
		this.lights = ll;
		trans = new Transformer(cameras);
		image = new Color3f[c.getScreenx()][c.getScreeny()];
	}
	
	public Color3f[][] getImage()
	{
		return image;
	}
	
	/**
	 * Draw a triangle on a panel, with usage of a Z-Buffer
	 * @param t
	 * @param g
	 * @param z
	 */
	public void RasterizeTriangle(WorldTriangle t, ZBuffer z_buff)
	{
		if((Constants.BACKFACE_ELIMINATION) && (t.a_normal.dot(cameras.getCamera().toVector4f())< 0)){return;}
		ScreenTriangle screent = trans.transform(t);
		int bounds[] = calculateBounds(screent);
		for(int y = bounds[2]; y <= bounds[3]; y++)
		{
			for(int x = bounds[0]; x <= bounds[1]; x++)
			{
				if(screent.isInTriangle(new Point4f(x,y,0)))
				{
					float z = calculate_Zbuff(screent, x, y);
					if(z < z_buff.get(x, y))
					{
						Color3f c = calculateColor(x,y,screent);
						if(panel != null) {panel.drawPixel(x, y, c.x, c.y, c.z);}
						image[x][y] = c;
						z_buff.set(x,y,z);
					}
				}
			}
		}
	}
	
	/**
	 * Draw a triangle on the screen. Any already colored pixels will be over-written.
	 */
	public void RasterizeTriangle(WorldTriangle t)
	{
		if((Constants.BACKFACE_ELIMINATION) && (t.a_normal.dot(cameras.getCamera().toVector4f())< 0)){return;}
		ScreenTriangle screent = trans.transform(t);
		int bounds[] = calculateBounds(screent);
		for(int y = bounds[2]; y <= bounds[3]; y++)
		{
			for(int x = bounds[0]; x <= bounds[1]; x++)
			{
				if(screent.isInTriangle(new Point4f(x,y,0)))
				{
						Color3f c = calculateColor(x,y,screent);
						if(panel != null) {panel.drawPixel(x, y, c.x, c.y, c.z);}
						image[x][y] = c;
				}
			}
		}
	}
	
	/**
	 * Rasterize a list of triangles, output on the screen
	 * @param triangles
	 * @param z
	 */
	public void RasterizeTriangles(ArrayList<WorldTriangle> triangles, ZBuffer z)
	{
		for(WorldTriangle tri : triangles){RasterizeTriangle(tri,z);}
	}
	
	/**
	 * Rasterize a list of triangles, output on the screen
	 * @param triangles
	 * @param z
	 */
	public void RasterizeTriangles(ArrayList<WorldTriangle> triangles, ZBuffer z, int aliasing)
	{
		// change camerasystem and create new panel-less rasterizer
		Camerasystem ss = new Camerasystem(cameras.screenx*aliasing,cameras.screeny*aliasing,cameras.getCamera(),cameras.gaze, cameras.viewup,cameras.fov_theta,cameras.n,cameras.f);
		ZBuffer newz = new ZBuffer(cameras.screenx*aliasing,cameras.screeny*aliasing);
		Rasterizer r = new Rasterizer(ss,lights);
		r.RasterizeTriangles(triangles, newz);
		Color3f[][] big = r.getImage();
		System.out.println(big);
		for(int i = 0; i<cameras.screenx*aliasing; i=i+aliasing)
		{
			for(int j = 0; j<cameras.screeny*aliasing; j=j+aliasing)
			{
				float xc = 0;
				float yc = 0;
				float zc = 0;
				for (int ro=i; ro<i+aliasing; ro++)
				{
					for (int co=j; co<j+aliasing; co++)
					{
						if(big[ro][co] != null)
						{
							xc=xc + big[ro][co].x;
							yc=yc + big[ro][co].y;
							zc=zc + big[ro][co].z;
						}
					}
				}
				xc = xc / (aliasing*aliasing);
				yc = yc / (aliasing*aliasing);
				zc = zc / (aliasing*aliasing);
				panel.drawPixel(i/aliasing, j/aliasing, xc,yc,zc);
			}
		}
	}
	
	/**
	 * Implementation of the painter's algorithm
	 * @param rootnode
	 */
	public void RasterizeTree(BSPNode rootnode)
	{
		if(rootnode == null)
		{
			// do nothing
			return;
		}
		if(rootnode.func_root(cameras.getCamera()) < 0)
		{
			RasterizeTree(rootnode.plus);
			RasterizeTriangle(rootnode.root);
			RasterizeTree(rootnode.min);
		}
		else
		{
			RasterizeTree(rootnode.min);
			RasterizeTriangle(rootnode.root);
			RasterizeTree(rootnode.plus);
		}
	}
	
	/**
	 * Calculate the color of a given pixel on the screen
	 * @param screenco_x
	 * @param screenco_y
	 * @param t
	 * @return
	 */
	private Color3f calculateColor(int screenco_x, int screenco_y, ScreenTriangle s)
	{
		if(Constants.NOSHADING) {return Constants.DIFFUSE_COLOUR;}; // NOSHADING override
		
		// Calculate base colors: texcolor, diffuse color, phong color
		// Set default, if triangle has color set, override default
		Color3f diffuse_color = Constants.DIFFUSE_COLOUR; if( s.getParent().diffuse_color != null) {diffuse_color = s.getParent().diffuse_color;}
		Color3f phong_color = Constants.PHONG_COLOUR; if( s.getParent().phong_color != null) {diffuse_color = s.getParent().phong_color;}
		
		// Calculate shading
		Color3f answer = new Color3f(0,0,0);
		for(Light l : lights.getLights()) // for every light source
		{
			// compute barycentric coordinates (SCREENSPACE)
			Point4f barries = s.getBaryCentric(new Point4f(screenco_x,screenco_y,0));		
			// no interpolation ? replace barries with dirty simple coords
			if(! Constants.SCREENSPACE_INTERPOLATION){barries = new Point4f(1,0,0);};
			
			// interpolate normal, light and camera vectors using barycentric coordinates
			Vector4f normal = interpolateNormalVector(barries,s.getParent());
			Vector4f light = interpolateLightVector(barries,s.getParent(),l);
			Vector4f camera = interpolateCameraVector(barries,s.getParent());
			
			// Add types of shading when enabled in Constants
			if(Constants.Diffuse){answer = answer.add(calculateDiffuse(diffuse_color,l,normal,light));}
			if(Constants.Phong){answer = answer.add(calculatePhong(phong_color,l,normal,light,camera));}
		}
		return answer;
	}
	

	private Color3f calculateDiffuse(Color3f base_color, Light l, Vector4f n, Vector4f light) 
	{
		float costheta = Math.max(0, (n.dot(light)));
		return new Color3f(Math.max(0,l.getColor().x*base_color.x*costheta),Math.max(0,l.getColor().y*base_color.y*costheta),Math.max(0,l.getColor().z*base_color.z*costheta));
	}
	
	private Color3f calculatePhong(Color3f phongc, Light li, Vector4f n, Vector4f l, Vector4f e)
	{
		Vector4f h = (e.add(l)).normalize();
		return phongc.mul(li.getColor().scale((float) Math.pow((h.dot(n)),Constants.PHONG_P)));
	}
	
	private Vector4f interpolateNormalVector(Point4f barries, WorldTriangle w)
	{
		if(Constants.BumpMapping)
		{
			Vector4f new_a = bumpMap(w.a,w.a_normal);
			Vector4f new_b = bumpMap(w.b,w.b_normal);
			Vector4f new_c = bumpMap(w.c,w.c_normal);
			return (new_a.scale(barries.x).add(new_b.scale(barries.y).add(new_c.scale(barries.z))).normalize());
		}
		return (w.a_normal.scale(barries.x).add(w.b_normal.scale(barries.y).add(w.c_normal.scale(barries.z))).normalize());
	}

	private Vector4f interpolateLightVector(Point4f barries, WorldTriangle w,Light l)
	{
		Vector4f light_a = l.getPosition().add(w.a.scale(-1));
		Vector4f light_b = l.getPosition().add(w.b.scale(-1));
		Vector4f light_c = l.getPosition().add(w.c.scale(-1));
		return (light_a.scale(barries.x).add(light_b.scale(barries.y).add(light_c.scale(barries.z)))).normalize();
	}
	
	private Vector4f interpolateCameraVector(Point4f barries, WorldTriangle w)
	{
		Vector4f e_a = cameras.getCamera().add(w.a.scale(-1));
		Vector4f e_b = cameras.getCamera().add(w.b.scale(-1));
		Vector4f e_c = cameras.getCamera().add(w.c.scale(-1));
		return (e_a.scale(barries.x).add(e_b.scale(barries.y).add(e_c.scale(barries.z)))).normalize();
	}
	
	private Vector4f bumpMap(Point4f a,Vector4f a_normal) 
	{
		float x = a.x; float y = a.y; float z = a.z;
		float f0 = 0.2f*(Noise.getInstance().noise(x, y, z));
		float fx = 0.2f*(Noise.getInstance().noise(x+ Constants.BUMPMAPPING_EPSILON, y, z));
		float fy = 0.2f*(Noise.getInstance().noise(x, y+ Constants.BUMPMAPPING_EPSILON, z));
		float fz = 0.2f*(Noise.getInstance().noise(x, y, z+ Constants.BUMPMAPPING_EPSILON));
		Vector4f df = new Vector4f((fx-f0)/Constants.BUMPMAPPING_EPSILON,(fy-f0)/Constants.BUMPMAPPING_EPSILON, (fz-f0)/Constants.BUMPMAPPING_EPSILON);
		return (a_normal.add(df.scale(-1))).normalize();
	}
	
	private float calculate_Zbuff(ScreenTriangle t, int x, int y)
	{
		Point4f[] corners = t.getVertices();
		Point4f bary = t.getBaryCentric(new Point4f(x,y,0));
		float answer = (corners[0].z)*(bary.x) + (corners[1].z)*(bary.y) + (corners[2].z)*(bary.z);
		return answer;
	}
	
	private int[] calculateBounds(ScreenTriangle t)
	{
		// bounding calculations
		int[] bound = t.getBoundingBox();
		int[] answer = new int[4];
		answer[0] = 0;
		answer[1] = cameras.getScreenx()-1;
		answer[2] = 0;
		answer[3] = cameras.getScreeny()-1;
		if ((bound[0] > answer[0]) && (bound[0] < answer[1])) {answer[0] = bound[0];}
		if ((bound[1] < answer[1]) && (bound[1] > answer[0])) {answer[1] = bound[1];}
		if ((bound[2] > answer[2]) && (bound[2] < answer[3])) {answer[2] = bound[2];}
		if ((bound[3] < answer[3]) && (bound[3] > answer[2])) {answer[3] = bound[3];}
		return answer;
	}
}
