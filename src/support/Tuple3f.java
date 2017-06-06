package support;
/**
 * A generic 3-element tuple that is represented by
 * single precision-floating point x,y,z coordinates.
 * 
 *
 *
 */

import java.io.Serializable;


public abstract class Tuple3f implements Serializable, Cloneable
{
    /**
     * The x coordinate.
     */

    public float x;


    /**
     * The y coordinate.
     */

    public float y;


    /**
     * The z coordinate.
     */

    public float z;

    /**
     * Constructs and initializes a Tuple3f to (0,0,0).
     */

    public Tuple3f()
    {
        x = 0.0F;
        y = 0.0F;
        z = 0.0F;
    }


    /**
     * Constructs and initializes a Tuple3f from the specified xyz coordinates.
     * @param x
     * @param y
     * @param z
     */ 

    public Tuple3f(float x, float y, float z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }


    /**
     * Constructs and initializes a Tuple3f from the array of length 3. 
     * @param t
     */

    public Tuple3f(float t[])
    {
        x = t[0];
        y = t[1];
        z = t[2];
    }


    /**
     * Constructs and initializes a Tuple3f from the specified Tuple3d.
     * @param t
     */

    public Tuple3f(Tuple3f t)
    {
        x = t.x;
        y = t.y;
        z = t.z;
    }


    /**
     * Returns a string that contains the values of this Tuple3f 
     */

    public String toString()
    {
        return "(" + x + ", " + y + ", " + z + ")";
    }


    /**
     *  Sets the value of this tuple to the specified xyz coordinates.
     * @param x
     * @param y
     * @param z
     */

    public final void set(float x, float y, float z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }


    /**
     * Sets the value of this tuple to the xyz coordinates specified in the array of length 3.
     * @param t
     */

    public final void set(float t[])
    {
        x = t[0];
        y = t[1];
        z = t[2];
    }


    /**
     * Sets the value of this tuple to the value of tuple t1.
     * @param t
     */

    public final void set(Tuple3f t)
    {
        x = t.x;
        y = t.y;
        z = t.z;
    }


    /**
     *   Gets the value of this tuple and copies the values into t.
     * @param t
     */

    public final void get(float t[])
    {
        t[0] = x;
        t[1] = y;
        t[2] = z;
    }


    /**
     * Gets the value of this tuple and copies the values into t.
     * @param t
     */

    public final void get(Tuple3f t)
    {
        t.x = x;
        t.y = y;
        t.z = z;
    }


    /**
     *  Creates a new object of the same class as this object.
     */	

    public Object clone()
    {
        try
        {
            return super.clone();
        }
        catch(CloneNotSupportedException clonenotsupportedexception)
        {
            throw new InternalError();
        }
    }
}
