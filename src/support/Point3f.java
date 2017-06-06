package support;

import java.io.Serializable;

import util.Point4f;

public class Point3f extends Tuple3f implements Serializable
{
    /**
     * Constructs and initializes a Point3f to (0,0,0).
     */

    public Point3f()
    {
    }


    /**
     * Constructs and initializes a Point3f from the specified xyz coordinates.
     * @param x
     * @param y
     * @param z
     */
    public Point3f(float x, float y, float z)
    {
        super(x, y, z);
    }


    /**
     * Constructs and initializes a Point3f from the specified Point3f.
     * @param p
     */

    public Point3f(Point3f p)
    {
        super(p);
    }


    /**
     * Constructs and initializes a Point3f from the specified Tuple3f.
     * @param t
     */

    public Point3f(Tuple3f t)
    {
        super(t);
    }


    /**
     * Constructs and initializes a Point3f from the array of length 3.
     * @param p
     */

    public Point3f(float p[])
    {
        super(p);
    }
    
    public Point4f get4f()
    {
    	return new Point4f(this.x,this.y,this.z);
    }


}
