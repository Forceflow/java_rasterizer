package util;

public class ScreenTriangle extends Triangle
{
	WorldTriangle parent; 

	public ScreenTriangle(Point4f[] corners, WorldTriangle parent) 
	{
		super(corners);
		this.parent = parent;
	}
	
	public WorldTriangle getParent()
	{
		return parent;
	}

}