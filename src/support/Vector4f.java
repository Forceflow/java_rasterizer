package support;

import java.io.Serializable;

import util.Point4f;

public class Vector4f extends Tuple4f implements Serializable
{
    /**
     * Constructs and initializes a Vector4f to (0,0,0,0).
     */

    public Vector4f()
    {
    }

    public Vector4f(float x, float y, float z)
	{
		super(x,y,z,0);
	}
    
    /**
	 * Normalize this vector
	 */
	public Vector4f normalize()
	{
		float length = getNorm();
		float new_x = x/length;
		float new_y = y/length;
		float new_z = z/length;
		float new_w = w/length;
		return new Vector4f(new_x,new_y,new_z,new_w);
	}
	
	public float getNorm()
	{
		return (float) Math.sqrt(Math.pow(x, 2)+Math.pow(y,2)+Math.pow(z,2)+Math.pow(w,2));
	}
	
	/**
	 * Scale this vector
	 */
	public Vector4f scale(float s)
	{
		float new_x = x*s;
		float new_y = y*s;
		float new_z = z*s;
		float new_w = w*s;
		return new Vector4f(new_x,new_y,new_z,new_w);
	}
	
	/**
	 * Cross product this vector with another vector and store the result in this vector
	 */
	public Vector4f cross(Vector4f s)
	{
		float new_x = y*s.z - z*s.y;
		float new_y = z*s.x - x*s.z;
		float new_z = x*s.y - y*s.x;
		return new Vector4f(new_x,new_y,new_z);
	}
	
	public Vector4f add(Vector4f s)
	{
		float new_x = x+s.x;
		float new_y = y+s.y;
		float new_z = z+s.z;
		float new_w = w+s.w;
		return new Vector4f(new_x,new_y,new_z,new_w);
	}
	
	public Vector4f left_mul(Matrix4f t)
	{
		float new_x = x*t.m00 + y*t.m01 + z*t.m02 + w*t.m03;
		float new_y = x*t.m10 + y*t.m11 + z*t.m12 + w*t.m13;
		float new_z = x*t.m20 + y*t.m21 + z*t.m22 + w*t.m23;
		float new_w = x*t.m30 + y*t.m31 + z*t.m32 + w*t.m33;
		return new Vector4f(new_x,new_y,new_z,new_w);
	}
	
	/**
	 * Dot product this vector with another vector and return the result
	 */
	public float dot(Vector4f b)
	{
		return this.x*b.x + this.y*b.y + this.z*b.z;
	}

    /**
     * Constructs and initializes a Vector4f from the specified xyzw coordinates.
     * @param x
     * @param y
     * @param z
     * @param w
     */

    public Vector4f(float x, float y, float z, float w)
    {
        super(x, y, z, w);
    }


    /**
     * Constructs and initializes a Vector4f from the array of length 4.
     * @param v
     */

    public Vector4f(float v[])
    {
        super(v);
    }


    /**
     * Constructs and initializes a Vector4f from the specified Vector4f.
     * @param v
     */

    public Vector4f(Vector4f v)
    {
        super(v);
    }


    /**
     *  Constructs and initializes a Vector4f from the specified Tuple4f.
     * @param t
     */

    public Vector4f(Tuple4f t)
    {
        super(t);
    }


    /**
     * Constructs and initializes a Vector4f from the specified Tuple3f.
     * @param t
     */

    public Vector4f(Tuple3f t)
    {
        super(t.x, t.y, t.z, 0.0F);
    }


    /**
     * Sets the x,y,z components of this vector to the corresponding components of tuple t1.
     * @param t
     */

    public final void set(Tuple3f t)
    {
        x = t.x;
        y = t.y;
        z = t.z;
        w = 0.0F;
    }

}
