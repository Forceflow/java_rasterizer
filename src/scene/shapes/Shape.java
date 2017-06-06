package scene.shapes;

import scene.geometry.Geometry;
import scene.materials.Material;

public class Shape 
{
	public Geometry getGeo() 
	{
		return geo;
	}
	public Material getMat() 
	{
		return mat;
	}
	public Shape(Geometry geo, Material mat) 
	{
		super();
		this.geo = geo;
		this.mat = mat;
	}
	private Geometry geo;
	private Material mat;

}
