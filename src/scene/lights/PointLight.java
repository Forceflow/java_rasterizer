package scene.lights;

import support.Color3f;
import support.Point3f;

public class PointLight extends Light 
{
	Point3f position;
	float intensity = 1;
	Color3f color = new Color3f(1,1,1);
	String name;
	public Point3f getPosition() {
		return position;
	}
	public float getIntensity() {
		return intensity;
	}
	public Color3f getColor() {
		return color;
	}
	public String getName() {
		return name;
	}
	public PointLight(Point3f position, float intensity, Color3f color,
			String name) {
		super();
		this.position = position;
		if(intensity != 0){this.intensity = intensity;}
		if(color != null){this.color = color;}
		this.name = name;
	}

}
