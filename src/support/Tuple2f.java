package support;

import java.io.Serializable;

public abstract class Tuple2f implements Serializable, Cloneable
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
     * Constructs and initializes a Tuple2f to (0,0).
     */

    public Tuple2f()
    {
        x = 0.0F;
        y = 0.0F;
    }

    /**
     * Constructs and initializes a Tuple2f from the specified xy coordinates
     */
    public Tuple2f(float x, float y)
    {
        this.x = x;
        this.y = y;
    }


    /**
     * Constructs and initializes a Tuple2f from the specified array.
     * @param t
     */

    public Tuple2f(float t[])
    {
        x = t[0];
        y = t[1];
    }


    /**
     * Constructs and initializes a Tuple2f from the specified Tuple2f. 
     * @param t
     */

    public Tuple2f(Tuple2f t)
    {
        x = t.x;
        y = t.y;
    }


    /**
     * Sets the value of this tuple to the specified xy coordinates.
     */

    public final void set(float x, float y)
    {
        this.x = x;
        this.y = y;
    }


    /**
     * Sets the value of this tuple from the 2 values specified in the array.
     * @param t
     */

    public final void set(float t[])
    {
        x = t[0];
        y = t[1];
    }


    /**
     * Sets the value of this tuple to the value of the Tuple2f argument.
     * @param t
     */

    public final void set(Tuple2f t)
    {
        x = t.x;
        y = t.y;
    }


    /**
     * Copies the value of the elements of this tuple into the array t.
     */

    public final void get(float t[])
    {
        t[0] = x;
        t[1] = y;
    }


    /**
     *  Returns a string that contains the values of this Tuple2f. 
     */

    public String toString()
    {
        return "(" + x + ", " + y + ")";
    }


    /**
     * clone
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
