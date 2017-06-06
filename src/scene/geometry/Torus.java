package scene.geometry;

import support.FileReader;
import support.ObjParser;


public class Torus extends Geometry 
{
	private float innerRadius;
	private float outerRadius;
	
	public Torus(float innerRadius, float outerRadius, String name)
	{
		super(name);
		this.innerRadius = innerRadius;
		this.outerRadius = outerRadius;
		// TODO: scale torus
		// load sphere from object file
		FileReader reader = new FileReader("src/objects/"+"torus"+".obj");
		ObjParser parser = new ObjParser(reader);
		this.triangles = parser.getTriangles();
	}
	
	public float getInnerRadius() {
		return innerRadius;
	}

	public float getOuterRadius() {
		return outerRadius;
	}

}
