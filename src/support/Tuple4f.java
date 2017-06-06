package support;

import java.io.Serializable;


public abstract class Tuple4f implements Serializable, Cloneable
{
    /**
     * The x component.
     */

    public float x;


    /**
     * The y component.
     */

    public float y;


    /**
     * The z component.
     */

    public float z;


    /**
     * The w component.
     */

    public float w;


    /**
     * Constructs and initializes a Tuple4f to (0,0,0,0).
     */

    public Tuple4f()
    {
        x = 0.0F;
        y = 0.0F;
        z = 0.0F;
        w = 0.0F;
    }


    /**
     * Constructs and initializes a Tuple4f from the specified xyzw coordinates.
     * @param x
     * @param y
     * @param z
     * @param w
     */

    public Tuple4f(float x, float y, float z, float w)
    {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }


    /**
     *  Constructs and initializes a Tuple4f from the array of length 4.
     * @param t
     */

    public Tuple4f(float t[])
    {
        x = t[0];
        y = t[1];
        z = t[2];
        w = t[3];
    }


    /**
     * Constructs and initializes a Tuple4f from the specified Tuple4f. 
     * @param t
     */

    public Tuple4f(Tuple4f t)
    {
        x = t.x;
        y = t.y;
        z = t.z;
        w = t.w;
    }


    /**
     * Sets the value of this tuple to the specified xyzw coordinates.
     */

    public final void set(float x, float y, float z, float w)
    {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }


    /**
     * Sets the value of this tuple to the specified coordinates in the array of length 4.
     * @param t
     */

    public final void set(float t[])
    {
        x = t[0];
        y = t[1];
        z = t[2];
        w = t[3];
    }


    /**
     * Sets the value of this tuple to the value of tuple t1.
     * @param t
     */

    public final void set(Tuple4f t)
    {
        x = t.x;
        y = t.y;
        z = t.z;
        w = t.w;
    }


    /**
     * Copies the values of this tuple into the array t.
     */

    public final void get(float t[])
    {
        t[0] = x;
        t[1] = y;
        t[2] = z;
        t[3] = w;
    }


    /**
     *  Copies the values of this tuple into the tuple t.
     * @param t
     */

    public final void get(Tuple4f t)
    {
        t.x = x;
        t.y = y;
        t.z = z;
        t.w = w;
    }


    /**
     * Returns a string that contains the values of this Tuple4f. 
     */

    public String toString()
    {
        return "(" + x + ", " + y + ", " + z + ", " + w + ")";
    }



    /**
     * Creates a new object of the same class as this object.
     */
    public Object clone()
    {
        try
        {
            return super.clone();
        }
        catch(CloneNotSupportedException e)
        {
            throw new InternalError();
        }
    }
}
