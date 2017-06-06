package raster;

public class ZBuffer 
{
	private float[][] buffer;
	
	public ZBuffer(int x, int y)
	{
		buffer = new float[x][y];
		for(int i = 0; i<x; i++)
		{
			for(int j = 0; j<y; j++)
			{
				buffer[i][j] = Float.MAX_VALUE;
			}
		}
	}
	
	public float get(int x, int y)
	{
		return buffer[x][y];
	}
	
	public void set(int x, int y, float zvalue)
	{
		buffer[x][y] = zvalue;
	}

}
