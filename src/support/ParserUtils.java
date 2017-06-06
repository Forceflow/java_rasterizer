package support;
import java.util.StringTokenizer;

/**
 * Util class used to parse Strings, floats, arrays of floats, ...
 */
public class ParserUtils
{
    /**
     * Parse a float from a string. 
     * @param string
     * @return
     * @throws SDLParseException
     */

    static float parseFloat(String string) throws ParseException
    {
        try
        {
            return Float.parseFloat(string);
        }
        catch (Exception e)
        {
            throw new ParseException("Could not parse float from \"" + string + "\".");
        }
    }


    /**
     * Parse a boolean from a string. 
     * @param string
     * @return
     * @throws SDLParseException
     */

    static boolean parseBoolean(String string) throws ParseException
    {
        if (string.equals("true"))
        {
            return true;
        }
        else if (string.equals("false"))
        {
            return false;
        }
        else
        {
            throw new ParseException("Could not parse boolean from \"" + string + "\".");
        }
    }


    /**
     * Parse a Vector3f from a string.
     * @param string
     * @return
     * @throws SDLParseException
     */

    static Vector3f parseVector3f(String string) throws ParseException
    {
        StringTokenizer stringTokenizer = new StringTokenizer(string, " ");

        Vector3f vector3f = new Vector3f();

        try
        {
            vector3f.x = Float.parseFloat(stringTokenizer.nextToken());
            vector3f.y = Float.parseFloat(stringTokenizer.nextToken());
            vector3f.z = Float.parseFloat(stringTokenizer.nextToken());
        }
        catch (Exception e)
        {
            throw new ParseException("Could not parse Vector3f from \"" + string + "\".");
        }

        if (stringTokenizer.hasMoreTokens())
        {
            throw new ParseException("Could not parse Vector3f from \"" + string + "\".");
        }

        return vector3f;
    }


    /**
     * Parse a Point3f from a string.
     * @param string
     * @return
     * @throws SDLParseException
     */

    static Point3f parsePoint3f(String string) throws ParseException
    {
        StringTokenizer stringTokenizer = new StringTokenizer(string, " ");

        Point3f point3f = new Point3f();

        try
        {
            point3f.x = Float.parseFloat(stringTokenizer.nextToken());
            point3f.y = Float.parseFloat(stringTokenizer.nextToken());
            point3f.z = Float.parseFloat(stringTokenizer.nextToken());
        }
        catch (Exception e)
        {
            throw new ParseException("Could not parse Vector3f from \"" + string + "\".");
        }

        if (stringTokenizer.hasMoreTokens())
        {
            throw new ParseException("Could not parse Vector3f from \"" + string + "\".");
        }

        return point3f;
    }


    /**
     * Parse a Color3f from a string.
     * @param string
     * @return
     * @throws SDLParseException
     */

    static Color3f parseColor3f(String string) throws ParseException
    {
        StringTokenizer stringTokenizer = new StringTokenizer(string, " ");

        Color3f color3f = new Color3f();

        try
        {
            color3f.x = Float.parseFloat(stringTokenizer.nextToken());
            color3f.y = Float.parseFloat(stringTokenizer.nextToken());
            color3f.z = Float.parseFloat(stringTokenizer.nextToken());
        }
        catch (Exception e)
        {
            throw new ParseException("Could not parse Vector3f from \"" + string + "\".");
        }

        if (stringTokenizer.hasMoreTokens())
        {
            throw new ParseException("Could not parse Vector3f from \"" + string + "\".");
        }

        return color3f;
    }


    /**
     * Parse a TexCoord2f from a string.
     * @param string
     * @return
     * @throws SDLParseException
     */

    static TexCoord2f parseTexCoord2f(String string) throws ParseException
    {
        StringTokenizer stringTokenizer = new StringTokenizer(string, " ");

        TexCoord2f texCoord2f = new TexCoord2f();

        try
        {
            texCoord2f.x = Float.parseFloat(stringTokenizer.nextToken());
            texCoord2f.y = Float.parseFloat(stringTokenizer.nextToken());
        }
        catch (Exception e)
        {
            throw new ParseException("Could not parse TexCoord2f from \"" + string + "\".");
        }

        if (stringTokenizer.hasMoreTokens())
        {
            throw new ParseException("Could not parse TexCoord2f from \"" + string + "\".");
        }

        return texCoord2f;
    }


    /**
     * Parse an array of ints from a string.
     * @param string
     * @return
     * @throws SDLParseException
     */

    static int [] parseIntArray(String string) throws ParseException
    {
        if (string==null) return null;
        StringTokenizer stringTokenizer = new StringTokenizer(string, ", ");

        int num = stringTokenizer.countTokens();
        int [] array = new int[num];

        try
        {
            for (int i = 0; i < num; i++)
            {
                array[i] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }
        catch (Exception e)
        {
            throw new ParseException("Could not parse int array from \"" + string + "\".");
        }

        return array;
    }


    /**
     * Parse an array of Vector3f from a string.
     * @param string
     * @return
     * @throws SDLParseException
     */

    static Vector3f [] parseVector3fArray(String string) throws ParseException
    {
        if (string==null) return null;
        StringTokenizer stringTokenizer = new StringTokenizer(string, ",");

        int num = stringTokenizer.countTokens();
        Vector3f [] array = new Vector3f[num];

        try
        {
            for (int i = 0; i < num; i++)
            {
                array[i] = parseVector3f(stringTokenizer.nextToken());
            }
        }
        catch (Exception e)
        {
            throw new ParseException("Could not parse Vector3f array from \"" + string + "\".");
        }

        return array;
    }


    /**
     * Parse an array of Point3f from a string.
     * @param string
     * @return
     * @throws SDLParseException
     */

    static Point3f [] parsePoint3fArray(String string) throws ParseException
    {
        StringTokenizer stringTokenizer = new StringTokenizer(string, ",");

        int num = stringTokenizer.countTokens();
        Point3f [] array = new Point3f[num];

        try
        {
            for (int i = 0; i < num; i++)
            {
                array[i] = parsePoint3f(stringTokenizer.nextToken());
            }
        }
        catch (Exception e)
        {
            throw new ParseException("Could not parse Point3f array from \"" + string + "\".");
        }

        return array;
    }


    /**
     * Parse an array of Color3f from a string.
     * @param string
     * @return
     * @throws SDLParseException
     */

    static Color3f [] parseColor3fArray(String string) throws ParseException
    {
        if (string==null) return null;
        StringTokenizer stringTokenizer = new StringTokenizer(string, ", ");

        int num = stringTokenizer.countTokens();
        Color3f [] array = new Color3f[num];

        try
        {
            for (int i = 0; i < num; i++)
            {
                array[i] = parseColor3f(stringTokenizer.nextToken());
            }
        }
        catch (Exception e)
        {
            throw new ParseException("Could not parse Color3f array from \"" + string + "\".");
        }

        return array;
    }


    /**
     * Parse an array of TexCoord2f from a string.
     * @param string
     * @return
     * @throws SDLParseException
     */

    static TexCoord2f [] parseTexCoord2fArray(String string) throws ParseException
    {
        if (string==null) return null;
        StringTokenizer stringTokenizer = new StringTokenizer(string, ",");

        int num = stringTokenizer.countTokens();
        TexCoord2f [] array = new TexCoord2f[num];

        try
        {
            for (int i = 0; i < num; i++)
            {
                array[i] = parseTexCoord2f(stringTokenizer.nextToken());
            }
        }
        catch (Exception e)
        {
            throw new ParseException("Could not parse TexCoord2f array from \"" + string + "\".");
        }

        return array;
    }


    /**
     * Parse an array of strings from a string.
     * @param string
     * @return
     * @throws SDLParseException
     */

    static String [] parseStringArray(String string) throws ParseException
    {
        StringTokenizer stringTokenizer = new StringTokenizer(string, ", ");

        int num = stringTokenizer.countTokens();
        String [] array = new String[num];

        try
        {
            for (int i = 0; i < num; i++)
            {
                array[i] = stringTokenizer.nextToken();
            }
        }
        catch (Exception e)
        {
            throw new ParseException("Could not parse String array from \"" + string + "\".");
        }

        return array;
    }


    /**
     * 
     * @param tuple3f
     * @return
     */

    static String formatTuple3f(Tuple3f tuple3f)
    {
        return "" + tuple3f.x + " " + tuple3f.y + " " + tuple3f.z + "";
    }

    /**
     * 
     * @param tuple2f
     * @return
     */

    static String formatTuple2f(Tuple2f tuple2f)
    {
        return "" + tuple2f.x + " " + tuple2f.y + "";
    }


    /**
     * 
     * @param array
     * @return
     */

    static String formatTuple3fArray(Tuple3f [] array)
    {
        if (array.length == 0)
        {
            return null;
        }
        else
        {
            String string = formatTuple3f(array[0]);
            for (int i = 1; i < array.length; i++)
            {
                string += ", " + formatTuple3f(array[i]);
            }
            return string;
        }
    }


    /**
     * 
     * @param array
     * @return
     */

    static String formatTuple2fArray(Tuple2f [] array)
    {
        if (array.length == 0)
        {
            return null;
        }
        else
        {
            String string = formatTuple2f(array[0]);
            for (int i = 1; i < array.length; i++)
            {
                string += ", " + formatTuple2f(array[i]);
            }
            return string;
        }
    }


    /**
     * 
     * @param array
     * @return
     */

    static String formatIntArray(int [] array)
    {
        if (array.length == 0)
        {
            return null;
        }
        else
        {
            String string = "" + array[0];
            for (int i = 1; i < array.length; i++)
            {
                string += ", " + array[i];
            }
            return string;
        }
    }


    /**
     * 
     * @param array
     * @return
     */

    static String formatStringArray(String [] array)
    {
        if (array.length == 0)
        {
            return null;
        }
        else
        {
            String string = "" + array[0];
            for (int i = 1; i < array.length; i++)
            {
                string += ", " + array[i];
            }
            return string;
        }
    }
}
