package util;

import support.Vector4f;
import support.Matrix4f;
import support.Tuple4f;

public class Point4f extends Tuple4f
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Point4f(float x, float y, float z)
	{
		super(x,y,z,1);
	}
	
	public Point4f(float x, float y, float z, float w)
	{
		super(x,y,z,w);
	}
	
	/**
	 * Left-Multiply this point with a 4*4 Matrix
	 */
	public Point4f left_mul(Matrix4f t)
	{
		float new_x = x*t.m00 + y*t.m01 + z*t.m02 + w*t.m03;
		float new_y = x*t.m10 + y*t.m11 + z*t.m12 + w*t.m13;
		float new_z = x*t.m20 + y*t.m21 + z*t.m22 + w*t.m23;
		float new_w = x*t.m30 + y*t.m31 + z*t.m32 + w*t.m33;
		return new Point4f(new_x,new_y,new_z,new_w);
	}
	
	/**
	 * Add a Point4f to this Point4f, and return the resulting vector
	 */
	public Vector4f add(Point4f b)
	{
		return new Vector4f(this.x+b.x, this.y+b.y, this.z+b.z, this.w+b.w);
	}
	
	/**
	 * Add a Vector4f to this Point4f, and return the resulting point
	 */
	public Point4f add(Vector4f b)
	{
		return new Point4f(this.x+b.x, this.y+b.y, this.z+b.z, this.w+b.w);
	}
	
	/** 
	 * Scale this point with a scalar, and return the result as a new Point4f
	 */
	public Point4f scale(float scalar)
	{
		return new Point4f(this.x*scalar, this.y*scalar, this.z*scalar, this.w*scalar);
	}
	
	public Vector4f toVector4f()
	{
		return new Vector4f(this.x,this.y,this.z,0);
	}

}
