package support;

import java.awt.Color;
import java.io.Serializable;

public class Color3f extends Tuple3f implements Serializable
{
    /**
     * Constructs and initializes a Color3f to (0.0, 0.0, 0.0).
     */

    public Color3f()
    {
    }


    /**
     * Constructs and initializes a Color3f from the three xyz values.
     * @param r
     * @param g
     * @param b
     */

    public Color3f(float r, float g, float b)
    {
        super(r, g, b);
    }


    /**
     * Constructs and initializes a Color3f from the specified Color3f. 
     * @param c
     */

    public Color3f(Color3f c)
    {
        super(c);
    }


    /**
     * Constructs and initializes a Color3f from the specified Tuple3f.
     * @param t
     */

    public Color3f(Tuple3f t)
    {
        super(t);
    }

    public Color3f add(Color3f c)
    {
    	float new_x = Math.min(1, (this.x+c.x));
    	float new_y = Math.min(1, (this.y+c.y));
    	float new_z = Math.min(1, (this.z+c.z));
    	return new Color3f(new_x,new_y,new_z);
    }
    
    public Color3f add_nocap(Color3f c)
    {
    	float new_x = this.x+c.x;
    	float new_y = this.y+c.y;
    	float new_z = this.z+c.z;
    	return new Color3f(new_x,new_y,new_z);
    }
    
    public Color3f mul(Color3f c)
    {
    	float new_x = Math.min(1, (this.x*c.x));
    	float new_y = Math.min(1, (this.y*c.y));
    	float new_z = Math.min(1, (this.z*c.z));
    	return new Color3f(new_x,new_y,new_z);
    }
    
    public Color3f scale(float s)
    {
    	return new Color3f(this.x*s, this.y*s, this.z*s);
    }

    /**
     * Constructs and initializes a Color3f from the array of length 3.
     * @param c
     */

    public Color3f(float c[])
    {
        super(c);
    }


    /**
     * Constructs and initializes a Color3f from the specified AWT Color object.
     * @param c
     */

    public Color3f(Color c)
    {
        super((float)c.getRed() / 255F, (float)c.getGreen() / 255F, (float)c.getBlue() / 255F);
    }


    /**
     * Sets the r,g,b values of this Color3f object to those of the specified AWT Color object.
     * @param c
     */

    public final void set(Color c)
    {
        x = (float)c.getRed() / 255F;
        y = (float)c.getGreen() / 255F;
        z = (float)c.getBlue() / 255F;
    }


    /**
     * Returns a new AWT color object initialized with the r,g,b values of this Color3f object.
     * @return
     */

    public final Color get()
    {
        int i = Math.round(x * 255F);
        int j = Math.round(y * 255F);
        int k = Math.round(z * 255F);
        return new Color(i, j, k);
    }
}
