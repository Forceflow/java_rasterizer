package raster;

import support.Color3f;
import support.TexCoord2f;
import support.Vector4f;
import util.Point4f;
import util.WorldTriangle;
import util.Constants;

public class BSPNode 
{
	public WorldTriangle root;
	public Vector4f root_normal;
	public BSPNode min;
	public BSPNode plus;
	
	public BSPNode(WorldTriangle t)
	{
		// set root triangle and compute its normal (once and for all)
		this.root = t;
		this.root_normal = (root.b.add(root.a.scale(-1))).cross((root.c.add(root.a.scale(-1))));
	}
	
	/**
	 * Adding a triangle to a Node : Page 183
	 * @param t
	 */
	public void add(WorldTriangle t)
	{
		float f_a = func_root(t.a);
		float f_b = func_root(t.b);
		float f_c = func_root(t.c);
		if(Math.abs(f_a)<Constants.BSP_EPSILON){f_a = 0;}
		if(Math.abs(f_b)<Constants.BSP_EPSILON){f_b = 0;}
		if(Math.abs(f_c)<Constants.BSP_EPSILON){f_c = 0;}
		if((f_a <= 0) & (f_b <= 0) & (f_c <= 0))
		{
			if(this.min == null) {min = new BSPNode(t);}
			else{min.add(t);}
		}
		else if((f_a >= 0) & (f_b >= 0) & (f_c >= 0))
		{
			if(this.plus == null) {plus = new BSPNode(t);}
			else{plus.add(t);}
		}
		else
		{
			System.out.println("cut");
			cut(t,f_a,f_b,f_c);
		}	
	}
	
	public float func_root(Point4f p)
	{
		return root_normal.dot(p.add(root.a.scale(-1)));
	}
	
	private void cut (WorldTriangle t, float f_a, float f_b, float f_c)
	{
		// swapping corners
		if((f_a * f_c) >= 0)
		{
			float temp;
			temp = f_b;
			f_b = f_c;
			f_c = temp;
			t.swapBC();
			temp = f_b;
			f_b = f_a;
			f_a = temp;
			t.swapAB();
		}
		else if (f_b * f_c >= 0)
		{
			float temp;
			temp = f_a;
			f_a = f_c;
			f_c = temp;
			t.swapAC();
			temp = f_b;
			f_b = f_a;
			f_a = temp;
			t.swapAB();
		}
		WorldTriangle[] newtriangles = computeNewTriangles(t);
		if(f_c >= 0)
		{
			if(this.min == null) {min = new BSPNode(newtriangles[0]);}
			else{min.add(newtriangles[0]);}
			min.add(newtriangles[1]);
			if(this.plus == null) {plus = new BSPNode(newtriangles[2]);}
			else{plus.add(newtriangles[2]);}
		}
		else
		{
			if(this.plus == null) {plus = new BSPNode(newtriangles[0]);}
			else{plus.add(newtriangles[0]);}
			plus.add(newtriangles[1]);
			if(this.min == null) {min = new BSPNode(newtriangles[2]);}
			else{min.add(newtriangles[2]);}
		}
		
	}
	
	private WorldTriangle[] computeNewTriangles(WorldTriangle t) {
		// compute new cornerpoints
		Point4f A = computeNewCornerPoint(t.a, t.c);
		Point4f B = computeNewCornerPoint(t.b, t.c);
		// interpolate all other properties before making new triangles
		// get barycoordinates
		Point4f bary_A = t.getBaryCentric(A);
		Point4f bary_B = t.getBaryCentric(B);
		// calculate normals
		Vector4f A_normal = t.a_normal.scale(bary_A.x).add((t.b_normal.scale(bary_A.y).add(t.c_normal.scale(bary_A.z))));
		Vector4f B_normal = t.a_normal.scale(bary_B.x).add((t.b_normal.scale(bary_B.y).add(t.c_normal.scale(bary_B.z))));
		// calculate texture coords
		TexCoord2f A_tex = null;
		TexCoord2f B_tex = null;
		if (t.hasTextureCoords())
		{
			A_tex = t.a_tex.scale(bary_A.x).add((t.b_tex.scale(bary_A.y).add(t.c_tex.scale(bary_A.z))));
			B_tex = t.a_tex.scale(bary_B.x).add((t.b_tex.scale(bary_B.y).add(t.c_tex.scale(bary_B.z))));
		}
		// creation of triangles
		Point4f[] t1points = {t.a,t.b,A};
		Vector4f[] t1normals = {t.a_normal,t.b_normal,A_normal};
		TexCoord2f[] t1texs = {t.a_tex,t.b_tex,A_tex};
		WorldTriangle T1 = new WorldTriangle(t1points,t1normals,t1texs);
		Point4f[] t2points = {t.b,B,A};
		Vector4f[] t2normals = {t.b_normal,B_normal,A_normal};
		TexCoord2f[] t2texs = {t.b_tex,B_tex,A_tex};
		WorldTriangle T2 = new WorldTriangle(t2points,t2normals,t2texs);
		Point4f[] t3points = {A,B,t.c};
		Vector4f[] t3normals = {A_normal,B_normal,t.c_normal};
		TexCoord2f[] t3texs = {A_tex,B_tex,t.c_tex};
		WorldTriangle T3 = new WorldTriangle(t3points,t3normals,t3texs);
		WorldTriangle[] answer = {T1,T2,T3};
		return answer;
	}

	/**
	 * Helper function for CUT method
	 */
	private float computeT(Point4f one, Point4f two)
	{
		Vector4f n = root_normal;
		float teller = n.dot(one.toVector4f());
		float noemer = n.dot(two.add(one.scale(-1)));
		return (teller/noemer);
	}
	
	private Point4f computeNewCornerPoint(Point4f one, Point4f two)
	{
		float tN = computeT(one, two);
		Vector4f m = two.add(one.scale(-1));
		return one.add(m.scale(tN));
	}
	
	@Override
	public String toString()
	{
		return "[Root : " + root + " Min: " + min + " Plus: " + plus+"]";
	}
}
