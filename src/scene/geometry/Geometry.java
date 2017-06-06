package scene.geometry;

import java.util.ArrayList;
import util.WorldTriangle;

public class Geometry 
{
	String name;
	// basic geometry at origin
	ArrayList<WorldTriangle> triangles;
	
	public Geometry(String name) {
		super();
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	public ArrayList<WorldTriangle> getTriangles() {
		return triangles;
	}
}
