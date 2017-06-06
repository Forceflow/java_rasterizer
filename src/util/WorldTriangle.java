package util;

import support.Color3f;
import support.Matrix4f;
import support.TexCoord2f;
import support.Vector4f;

/**
 * A triangle in the global 3D coordinate system
 * @author Jeroen
 *
 */
public class WorldTriangle extends Triangle
{
	// texture coords
	public TexCoord2f a_tex;
	public TexCoord2f b_tex;
	public TexCoord2f c_tex;
	// normals
	public Vector4f a_normal;
	public Vector4f b_normal;
	public Vector4f c_normal;
	// colors
	public Color3f diffuse_color;
	public Color3f phong_color;
	public float shininess;
	
	public WorldTriangle(Point4f[] corners, Vector4f[] normals, TexCoord2f[] texturecoords)
	{
		super(corners);
		a_normal = normals[0];
		b_normal = normals[1];
		c_normal = normals[2];
		a_tex = texturecoords[0];
		b_tex = texturecoords[1];
		c_tex = texturecoords[2];
	}
	
	public WorldTriangle(Point4f[] corners, Vector4f[] normals, TexCoord2f[] texturecoords, Color3f diff, Color3f phong, float shin)
	{
		super(corners);
		a_normal = normals[0];
		b_normal = normals[1];
		c_normal = normals[2];
		a_tex = texturecoords[0];
		b_tex = texturecoords[1];
		c_tex = texturecoords[2];
		this.diffuse_color = diff;
		this.phong_color = phong;
		this.shininess = shin;
	}
	
	public boolean hasTextureCoords()
	{
		if ( (a_tex == null) & (b_tex == null) | (c_tex == null)) {return false;}
		return true;
	}
	
	/**
	 * Make a clone of this WorldTriangle
	 */
	public WorldTriangle clone()
	{
		Point4f[] corners = {a,b,c};
		Vector4f[] normals = {a_normal,b_normal,c_normal};
		TexCoord2f[] texturecoords = {a_tex,b_tex,c_tex};
		return new WorldTriangle(corners, normals, texturecoords,diffuse_color,phong_color,shininess);
	}
	
	/**
	 * Transform one Worldtriangle into another using a transformation Matrix.
	 */
	public WorldTriangle transform(Matrix4f t)
	{
		// transform corners
		Point4f a_new = a.left_mul(t);
		Point4f b_new = b.left_mul(t);
		Point4f c_new = c.left_mul(t);
		// transform normals
		Matrix4f M_normals = t.invert().transpose();
		Vector4f a_normal_new = a_normal.left_mul(M_normals).normalize();
		Vector4f b_normal_new = b_normal.left_mul(M_normals).normalize();
		Vector4f c_normal_new = c_normal.left_mul(M_normals).normalize();
		// texcoords and diff/phong info stays the same
		// create and return new triangle
		Point4f[] corners = {a_new,b_new,c_new};
		Vector4f[] normals = {a_normal_new,b_normal_new,c_normal_new};
		TexCoord2f[] texturecoords = {a_tex,b_tex,c_tex};
		return new WorldTriangle(corners, normals, texturecoords,diffuse_color,phong_color,shininess);
	}
	
	/**
	 * SWAP Methods
	 * (hardcoded - it's very ugly, but I can't think of any other way at the moment)
	 */
	public void swapAB()
	{
		Point4f temp;
		// switch point
		temp = a;
		a = b;
		b = temp;
		// switch normal
		Vector4f temp2;
		temp2 = a_normal;
		a_normal = b_normal;
		b_normal = temp2;
		// switch tex
		TexCoord2f temp4 = a_tex;
		a_tex = b_tex;
		b_tex = temp4;
	}
	
	public void swapAC()
	{
		Point4f temp;
		// switch point
		temp = a;
		a = c;
		c = temp;
		// switch normal
		Vector4f temp2;
		temp2 = a_normal;
		a_normal = c_normal;
		c_normal = temp2;
		// switch tex
		TexCoord2f temp4 = a_tex;
		a_tex = c_tex;
		c_tex = temp4;
	}
	
	public void swapBC()
	{
		Point4f temp;
		// switch point
		temp = c;
		c = b;
		b = temp;
		// switch normal
		Vector4f temp2;
		temp2 = c_normal;
		c_normal = b_normal;
		b_normal = temp2;
		// switch tex
		TexCoord2f temp4 = c_tex;
		c_tex = b_tex;
		b_tex = temp4;
	}
}
