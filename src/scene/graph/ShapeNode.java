package scene.graph;

import java.util.ArrayList;
import scene.geometry.Geometry;
import scene.materials.DiffuseMaterial;
import scene.materials.LinearCombinedMaterial;
import scene.materials.Material;
import scene.materials.PhongMaterial;
import util.WorldTriangle;

public class ShapeNode extends Node
{
	public ArrayList<WorldTriangle> triangles = new ArrayList<WorldTriangle>();
	Material mat;
	Geometry geo;

	public ShapeNode(Node parent, Material mat, Geometry geo) 
	{
		super(parent);
		this.mat = mat;
		this.geo = geo;
		generateTriangles();
	}

	/**
	 * Dirty copying of triangles, but it's the only way
	 * @param mat
	 * @param geo
	 */
	private void generateTriangles() 
	{
		ArrayList<WorldTriangle> original = geo.getTriangles();
		for(WorldTriangle t : original)
		{
			WorldTriangle s = t.clone();
			if(mat instanceof DiffuseMaterial){s.diffuse_color = mat.getColor();}
			if(mat instanceof PhongMaterial){s.phong_color = mat.getColor();
			s.shininess = ((PhongMaterial) mat).getShininess();}
			//FIXME: Niet correct
			if(mat instanceof LinearCombinedMaterial){s.diffuse_color = mat.getColor();}
			triangles.add(s);
		}
	}
	
	public String toString()
	{
		return "ShapeNode " + mat.getName() + " | " + geo.getName();
	}
	

}
