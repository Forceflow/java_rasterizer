package scene.lights;

import support.Color3f;
import support.Point3f;
import support.Vector3f;

public class SpotLight extends Light 
{
	
	Point3f position;
	Vector3f direction;
	float angle;
	float intensity=1;
	Color3f color=new Color3f(1,1,1);
	String name;
	public SpotLight(Point3f position, Vector3f direction, float angle,
			float intensity, Color3f color, String name) {
		super();
		this.position = position;
		this.direction = direction;
		this.angle = angle;
		if(intensity != 0){this.intensity = intensity;}
		if(color != null){this.color = color;}
		this.name = name;
	}

}
