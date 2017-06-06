package support;

import java.io.Serializable;

/**
 * Implements a 4x4 matrix of floats. 
 */
public class Matrix4f implements Serializable, Cloneable
{
    /**
     * The first element of the first row.
     */

    public float m00;


    /**
     * The second element of the first row.
     */

    public float m01;


    /**
     * The third element of the first row.
     */

    public float m02;


    /**
     * The forth element of the first row.
     */

    public float m03;


    /**
     * The first element of the second row.
     */

    public float m10;


    /**
     * The second element of the second row.
     */

    public float m11;


    /**
     * The third element of the second row.
     */

    public float m12;


    /**
     * The fourth element of the second row.
     */

    public float m13;


    /**
     * The first element of the third row.
     */

    public float m20;


    /**
     * The second element of the third row.
     */

    public float m21;


    /**
     * The thrid element of the third row.
     */

    public float m22;


    /**
     * The fourth element of the third row.
     */

    public float m23;


    /**
     * The first element of the fourth row.
     */

    public float m30;


    /**
     * The second element of the fourth row.
     */

    public float m31;


    /**
     * The third element of the fourth row.
     */

    public float m32;


    /**
     * The fourth element of the fourth row.
     */

    public float m33;


    /**
     * Constructs and initializes a Matrix4f to all zeros.	 
     */

    public Matrix4f()
    {
        m00 = 0.0F;
        m01 = 0.0F;
        m02 = 0.0F;
        m03 = 0.0F;
        m10 = 0.0F;
        m11 = 0.0F;
        m12 = 0.0F;
        m13 = 0.0F;
        m20 = 0.0F;
        m21 = 0.0F;
        m22 = 0.0F;
        m23 = 0.0F;
        m30 = 0.0F;
        m31 = 0.0F;
        m32 = 0.0F;
        m33 = 0.0F;
    }

    /**
     * Constructs and initializes a Matrix4f from the specified 16 element array
     * @param m
     */

    public Matrix4f(float m[])
    {
        m00 = m[0];
        m01 = m[1];
        m02 = m[2];
        m03 = m[3];
        m10 = m[4];
        m11 = m[5];
        m12 = m[6];
        m13 = m[7];
        m20 = m[8];
        m21 = m[9];
        m22 = m[10];
        m23 = m[11];
        m30 = m[12];
        m31 = m[13];
        m32 = m[14];
        m33 = m[15];
    }

    void get(float m[])
    {
        m[0] = m00;
        m[1] = m01;
        m[2] = m02;
        m[3] = m03;
        m[4] = m10;
        m[5] = m11;
        m[6] = m12;
        m[7] = m13;
        m[8] = m20;
        m[9] = m21;
        m[10] = m22;
        m[11] = m23;
        m[12] = m30;
        m[13] = m31;
        m[14] = m32;
        m[15] = m33;
    }

    /**
     * Constructs and initializes a Matrix4f from the specified 16 values
     * @param m00
     * @param m01
     * @param m02
     * @param m03
     * @param m10
     * @param m11
     * @param m12
     * @param m13
     * @param m20
     * @param m21
     * @param m22
     * @param m23
     * @param m30
     * @param m31
     * @param m32
     * @param m33
     */	

    public Matrix4f
    (
        float m00, float m01, float m02, float m03,
        float m10, float m11, float m12, float m13,
        float m20, float m21, float m22, float m23,
        float m30, float m31, float m32, float m33
    )
    {
        this.m00 = m00;
        this.m01 = m01;
        this.m02 = m02;
        this.m03 = m03;
        this.m10 = m10;
        this.m11 = m11;
        this.m12 = m12;
        this.m13 = m13;
        this.m20 = m20;
        this.m21 = m21;
        this.m22 = m22;
        this.m23 = m23;
        this.m30 = m30;
        this.m31 = m31;
        this.m32 = m32;
        this.m33 = m33;
    }




    /**
     * Constructs a new matrix with the same values as the Matrix4f parameter.
     * @param matrix4f
     */

    public Matrix4f(Matrix4f matrix4f)
    {
        m00 = matrix4f.m00;
        m01 = matrix4f.m01;
        m02 = matrix4f.m02;
        m03 = matrix4f.m03;
        m10 = matrix4f.m10;
        m11 = matrix4f.m11;
        m12 = matrix4f.m12;
        m13 = matrix4f.m13;
        m20 = matrix4f.m20;
        m21 = matrix4f.m21;
        m22 = matrix4f.m22;
        m23 = matrix4f.m23;
        m30 = matrix4f.m30;
        m31 = matrix4f.m31;
        m32 = matrix4f.m32;
        m33 = matrix4f.m33;
    }


    /**
     * Returns a string that contains the values of this Matrix4f.
     */

    public String toString()
    {
        return m00 + ", " + m01 + ", " + m02 + ", " + m03 + "\n" + m10 + ", " + m11 + ", " + m12 + ", " + m13 + "\n" + m20 + ", " + m21 + ", " + m22 + ", " + m23 + "\n" + m30 + ", " + m31 + ", " + m32 + ", " + m33 + "\n";
    }


    /**
     * Sets this Matrix4f to identity.
     */

    public final void setIdentity()
    {
        m00 = 1.0F;
        m01 = 0.0F;
        m02 = 0.0F;
        m03 = 0.0F;
        m10 = 0.0F;
        m11 = 1.0F;
        m12 = 0.0F;
        m13 = 0.0F;
        m20 = 0.0F;
        m21 = 0.0F;
        m22 = 1.0F;
        m23 = 0.0F;
        m30 = 0.0F;
        m31 = 0.0F;
        m32 = 0.0F;
        m33 = 1.0F;
    }


    /**
     * Sets the specified element of this matrix4f to the value provided.
     * @param row
     * @param j
     * @param f
     */

    public final void setElement(int row, int column, float value)
    {
        switch(row)
        {
        case 0:
            switch(column)
            {
            case 0:
                m00 = value;
                break;

            case 1:
                m01 = value;
                break;

            case 2:
                m02 = value;
                break;

            case 3:
                m03 = value;
                break;

            default:
                throw new ArrayIndexOutOfBoundsException();
            }
            break;

        case 1:
            switch(column)
            {
            case 0:
                m10 = value;
                break;

            case 1:
                m11 = value;
                break;

            case 2:
                m12 = value;
                break;

            case 3:
                m13 = value;
                break;

            default:
                throw new ArrayIndexOutOfBoundsException();
            }
            break;

        case 2:
            switch(column)
            {
            case 0:
                m20 = value;
                break;

            case 1:
                m21 = value;
                break;

            case 2:
                m22 = value;
                break;

            case 3:
                m23 = value;
                break;

            default:
                throw new ArrayIndexOutOfBoundsException();
            }
            break;

        case 3:
            switch(column)
            {
            case 0:
                m30 = value;
                break;

            case 1:
                m31 = value;
                break;

            case 2:
                m32 = value;
                break;

            case 3:
                m33 = value;
                break;

            default:
                throw new ArrayIndexOutOfBoundsException();
            }
            break;

        default:
            throw new ArrayIndexOutOfBoundsException();
        }
    }


    /**
     * Retrieves the value at the specified row and column of this matrix.
     * @param row
     * @param column
     * @return
     */

    public final float getElement(int row, int column)
    {
        switch(row)
        {
        default:
            break;

        case 0:
            switch(column)
            {
            case 0:
                return m00;

            case 1:
                return m01;

            case 2:
                return m02;

            case 3:
                return m03;
            }
            break;

        case 1:
            switch(column)
            {
            case 0:
                return m10;

            case 1:
                return m11;

            case 2:
                return m12;

            case 3:
                return m13;
            }
            break;

        case 2:
            switch(column)
            {
            case 0:
                return m20;

            case 1:
                return m21;

            case 2:
                return m22;

            case 3:
                return m23;
            }
            break;

        case 3:
            switch(column)
            {
            case 0:
                return m30;

            case 1:
                return m31;

            case 2:
                return m32;

            case 3:
                return m33;
            }
            break;
        }
        throw new ArrayIndexOutOfBoundsException();
    }


    /**
     * Copies the matrix values in the specified row into the vector parameter.
     * @param row
     * @param vector4f
     */

    public final void getRow(int row, Vector4f v)
    {
        if(row == 0)
        {
            v.x = m00;
            v.y = m01;
            v.z = m02;
            v.w = m03;
        } else
            if(row == 1)
            {
                v.x = m10;
                v.y = m11;
                v.z = m12;
                v.w = m13;
            } else
                if(row == 2)
                {
                    v.x = m20;
                    v.y = m21;
                    v.z = m22;
                    v.w = m23;
                } else
                    if(row == 3)
                    {
                        v.x = m30;
                        v.y = m31;
                        v.z = m32;
                        v.w = m33;
                    } else
                    {
                        throw new ArrayIndexOutOfBoundsException();
                    }
    }


    /**
     * Copies the matrix values in the specified row into the array parameter.
     * @param row
     * @param af
     */
    public final void getRow(int row, float v[])
    {
        if(row == 0)
        {
            v[0] = m00;
            v[1] = m01;
            v[2] = m02;
            v[3] = m03;
        } else
            if(row == 1)
            {
                v[0] = m10;
                v[1] = m11;
                v[2] = m12;
                v[3] = m13;
            } else
                if(row == 2)
                {
                    v[0] = m20;
                    v[1] = m21;
                    v[2] = m22;
                    v[3] = m23;
                } else
                    if(row == 3)
                    {
                        v[0] = m30;
                        v[1] = m31;
                        v[2] = m32;
                        v[3] = m33;
                    } else
                    {
                        throw new ArrayIndexOutOfBoundsException();
                    }
    }


    /**
     * Copies the matrix values in the specified column into the vector parameter.
     * @param i
     * @param vector4f
     */

    public final void getColumn(int column, Vector4f v)
    {
        if(column == 0)
        {
            v.x = m00;
            v.y = m10;
            v.z = m20;
            v.w = m30;
        } else
            if(column == 1)
            {
                v.x = m01;
                v.y = m11;
                v.z = m21;
                v.w = m31;
            } else
                if(column == 2)
                {
                    v.x = m02;
                    v.y = m12;
                    v.z = m22;
                    v.w = m32;
                } else
                    if(column == 3)
                    {
                        v.x = m03;
                        v.y = m13;
                        v.z = m23;
                        v.w = m33;
                    } else
                    {
                        throw new ArrayIndexOutOfBoundsException();
                    }
    }


    /**
     * Copies the matrix values in the specified column into the array parameter.
     * @param column
     * @param v
     */

    public final void getColumn(int column, float v[])
    {
        if(column == 0)
        {
            v[0] = m00;
            v[1] = m10;
            v[2] = m20;
            v[3] = m30;
        } else
            if(column == 1)
            {
                v[0] = m01;
                v[1] = m11;
                v[2] = m21;
                v[3] = m31;
            } else
                if(column == 2)
                {
                    v[0] = m02;
                    v[1] = m12;
                    v[2] = m22;
                    v[3] = m32;
                } else
                    if(column == 3)
                    {
                        v[0] = m03;
                        v[1] = m13;
                        v[2] = m23;
                        v[3] = m33;
                    } else
                    {
                        throw new ArrayIndexOutOfBoundsException();
                    }
    }


    /**
     * Sets the specified row of this matrix4f to the four values provided.
     * @param row
     * @param m0
     * @param m1
     * @param m2
     * @param m3
     */

    public final void setRow(int row, float m0, float m1, float m2, float m3)
    {
        switch(row)
        {
        case 0:
            m00 = m0;
            m01 = m1;
            m02 = m2;
            m03 = m3;
            break;

        case 1:
            m10 = m0;
            m11 = m1;
            m12 = m2;
            m13 = m3;
            break;

        case 2:
            m20 = m0;
            m21 = m1;
            m22 = m2;
            m23 = m3;
            break;

        case 3:
            m30 = m0;
            m31 = m1;
            m32 = m2;
            m33 = m3;
            break;

        default:
            throw new ArrayIndexOutOfBoundsException();
        }
    }


    /**
     * Sets the specified row of this matrix4f to the Vector provided.
     * @param row
     * @param v
     */

    public final void setRow(int row, Vector4f v)
    {
        switch(row)
        {
        case 0:
            m00 = v.x;
            m01 = v.y;
            m02 = v.z;
            m03 = v.w;
            break;

        case 1:
            m10 = v.x;
            m11 = v.y;
            m12 = v.z;
            m13 = v.w;
            break;

        case 2:
            m20 = v.x;
            m21 = v.y;
            m22 = v.z;
            m23 = v.w;
            break;

        case 3:
            m30 = v.x;
            m31 = v.y;
            m32 = v.z;
            m33 = v.w;
            break;

        default:
            throw new ArrayIndexOutOfBoundsException();
        }
    }


    /**
     * Sets the specified row of this matrix4f to the four values provided in the passed array.
     * @param rowloat
     */

    public final void setRow(int row, float [] v)
    {
        switch(row)
        {
        case 0:
            m00 = v[0];
            m01 = v[1];
            m02 = v[2];
            m03 = v[3];
            break;

        case 1:
            m10 = v[0];
            m11 = v[1];
            m12 = v[2];
            m13 = v[3];
            break;

        case 2:
            m20 = v[0];
            m21 = v[1];
            m22 = v[2];
            m23 = v[3];
            break;

        case 3:
            m30 = v[0];
            m31 = v[1];
            m32 = v[2];
            m33 = v[3];
            break;

        default:
            throw new ArrayIndexOutOfBoundsException();
        }
    }


    /**
     * Sets the specified column of this matrix4f to the four values provided.
     * @param column
     * @param m0
     * @param m1
     * @param m2
     * @param m3
     */

    public final void setColumn(int column, float m0, float m1, float m2, float m3)
    {
        switch(column)
        {
        case 0:
            m00 = m0;
            m10 = m1;
            m20 = m2;
            m30 = m3;
            break;

        case 1:
            m01 = m0;
            m11 = m1;
            m21 = m2;
            m31 = m3;
            break;

        case 2:
            m02 = m0;
            m12 = m1;
            m22 = m2;
            m32 = m3;
            break;

        case 3:
            m03 = m0;
            m13 = m1;
            m23 = m2;
            m33 = m3;
            break;

        default:
            throw new ArrayIndexOutOfBoundsException();
        }
    }


    /**
     * Sets the specified column of this matrix4f to the vector provided.
     * @param column
     * @param v
     */

    public final void setColumn(int column, Vector4f v)
    {
        switch(column)
        {
        case 0:
            m00 = v.x;
            m10 = v.y;
            m20 = v.z;
            m30 = v.w;
            break;

        case 1:
            m01 = v.x;
            m11 = v.y;
            m21 = v.z;
            m31 = v.w;
            break;

        case 2:
            m02 = v.x;
            m12 = v.y;
            m22 = v.z;
            m32 = v.w;
            break;

        case 3:
            m03 = v.x;
            m13 = v.y;
            m23 = v.z;
            m33 = v.w;
            break;

        default:
            throw new ArrayIndexOutOfBoundsException();
        }
    }


    /**
     * Sets the specified column of this matrix4f to the four values provided.
     * @param column
     * @param v
     */

    public final void setColumn(int column, float v[])
    {
        switch(column)
        {
        case 0:
            m00 = v[0];
            m10 = v[1];
            m20 = v[2];
            m30 = v[3];
            break;

        case 1:
            m01 = v[0];
            m11 = v[1];
            m21 = v[2];
            m31 = v[3];
            break;

        case 2:
            m02 = v[0];
            m12 = v[1];
            m22 = v[2];
            m32 = v[3];
            break;

        case 3:
            m03 = v[0];
            m13 = v[1];
            m23 = v[2];
            m33 = v[3];
            break;

        default:
            throw new ArrayIndexOutOfBoundsException();
        }
    }


    /**
     * Sets the value of this matrix to a copy of the passed matrix m.
     * @param m
     */

    public final void set(Matrix4f m)
    {
        m00 = m.m00;
        m01 = m.m01;
        m02 = m.m02;
        m03 = m.m03;
        m10 = m.m10;
        m11 = m.m11;
        m12 = m.m12;
        m13 = m.m13;
        m20 = m.m20;
        m21 = m.m21;
        m22 = m.m22;
        m23 = m.m23;
        m30 = m.m30;
        m31 = m.m31;
        m32 = m.m32;
        m33 = m.m33;
    }


    /**
     * Sets this matrix to all zeros.
     */

    public final void setZero()
    {
        m00 = 0.0F;
        m01 = 0.0F;
        m02 = 0.0F;
        m03 = 0.0F;
        m10 = 0.0F;
        m11 = 0.0F;
        m12 = 0.0F;
        m13 = 0.0F;
        m20 = 0.0F;
        m21 = 0.0F;
        m22 = 0.0F;
        m23 = 0.0F;
        m30 = 0.0F;
        m31 = 0.0F;
        m32 = 0.0F;
        m33 = 0.0F;
    }


    /**
     * clone
     */

    public Object clone()
    {
        Matrix4f matrix4f = null;
        try
        {
            matrix4f = (Matrix4f)super.clone();
        }
        catch(CloneNotSupportedException clonenotsupportedexception)
        {
            throw new InternalError();
        }
        return matrix4f;
    }
    
    public Matrix4f mul_right(Matrix4f b)
	{
		Matrix4f answer = new Matrix4f();
		for(int row = 0; row < 4; row++)
		{
			for(int column = 0; column < 4; column++)
			{
				float value = 0;
				for(int c = 0; c < 4; c++)
				{
					value = value + this.getElement(row, c)*b.getElement(c, column);
				}
				answer.setElement(row, column, value);
			}
		}
		return answer;
	}

    /**
     * Warning: since we don't care about the length, no division of determinant !
     * @return
     */
	public Matrix4f invert() 
	{
		Matrix4f answer = new Matrix4f();
		for (int row = 0; row<4;row++)
		{
			for (int col = 0; col<4;col++)
			{
				answer.setElement(row, col, (float) Math.pow(-1, row+col)*det3(cofactor(row,col)));
			}
		}
		return answer.transpose();
	}
	
	private float[] cofactor(int i, int j)
	{
		float[] answer = new float[9];
		int answercount = 0;
		for (int row = 0; row<4;row++)
		{
			for (int col = 0; col<4;col++)
			{
				if((row != i) && (col !=j))
				{
					answer[answercount] = getElement(row, col);
					answercount++;
				}
			}
		}
		return answer;
	}
	
	private float det3(float[] m)
	{
		return (m[0]*m[4]*m[8]) + (m[1]*m[5]*m[6]) + (m[2]*m[3]*m[7]) 
		- (m[6]*m[4]*m[2]) - (m[7]*m[5]*m[0]) - (m[8]*m[3]*m[1]);
	}
	
	public Matrix4f transpose()
	{
		Matrix4f answer = new Matrix4f();
		for (int row = 0; row<4;row++)
		{
			for (int col = 0; col<4;col++)
			{
				answer.setElement(col, row, getElement(row, col));
			}
		}
		return answer;
	}



}
