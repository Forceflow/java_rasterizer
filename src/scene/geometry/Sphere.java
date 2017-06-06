package scene.geometry;

import support.FileReader;
import support.ObjParser;

public class Sphere extends Geometry 
{

	private float radius;

	public Sphere(float radius, String name)
	{
		super(name);
		this.radius = radius;
		// TODO: scale sphere
		// load sphere from object file
		FileReader reader = new FileReader("src/objects/"+"sphere"+".obj");
		ObjParser parser = new ObjParser(reader);
		this.triangles = parser.getTriangles();
	}
	
	public float getRadius() {
		return radius;
	}
}
