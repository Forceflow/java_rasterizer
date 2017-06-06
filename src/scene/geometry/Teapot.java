package scene.geometry;

import support.FileReader;
import support.ObjParser;

public class Teapot extends Geometry 
{
	
	private float size;
	
	public Teapot(float size, String name) {
		super(name);
		this.size = size;
		//TODO: scale teapot
		//read triangles from file
		FileReader reader = new FileReader("src/objects/"+"teapot"+".obj");
		ObjParser parser = new ObjParser(reader);
		this.triangles = parser.getTriangles();
	}
	
	public float getSize() {
		return size;
	}




}
