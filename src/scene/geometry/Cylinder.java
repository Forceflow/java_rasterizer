package scene.geometry;

import support.FileReader;
import support.ObjParser;

public class Cylinder extends Geometry 
{
	
	private float radius;
	private float height;
	private boolean capped;

	public Cylinder(float radius, float height, boolean capped,String name)
	{
		super(name);
		this.radius = radius;
		this.height = height;
		this.capped = capped;
		// TODO: scale cylinder
		// load sphere from object file
		FileReader reader = new FileReader("src/objects/"+"cylinder"+".obj");
		ObjParser parser = new ObjParser(reader);
		this.triangles = parser.getTriangles();
	}
	
	public float getRadius() {
		return radius;
	}

	public float getHeight() {
		return height;
	}

	public boolean isCapped() {
		return capped;
	}


}
