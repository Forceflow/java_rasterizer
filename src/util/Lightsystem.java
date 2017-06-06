package util;

import java.util.ArrayList;

public class Lightsystem 
{
	private ArrayList<Light> lights;

	public Lightsystem()
	{
		this.lights = new ArrayList<Light>();
	}
	
	public ArrayList<Light> getLights() {
		return lights;
	}
	
	public void addLight(Light l)
	{
		lights.add(l);
	}
	
	public void removeLight(Light l)
	{
		if(! lights.remove(l)){ System.out.println("Warning: trying to remove light that was not present in this lightsystem");};
	}

}
