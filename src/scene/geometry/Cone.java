package scene.geometry;

import support.FileReader;
import support.ObjParser;

public class Cone extends Geometry
{
	private float radius;
	private float height;
	private boolean capped;
	
	public Cone(String name, float radius, float height, boolean capped) {
		
		super(name);
		this.radius = radius;
		this.height = height;
		this.capped = capped;
		//TODO: Scale cone
		FileReader reader = new FileReader("src/objects/"+"cone"+".obj");
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
