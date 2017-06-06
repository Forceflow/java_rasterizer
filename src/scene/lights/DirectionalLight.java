package scene.lights;

import support.Color3f;
import support.Vector3f;

public class DirectionalLight extends Light {
	
	public Vector3f getDirection() {
		return direction;
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
	Vector3f direction;
	float intensity=1;
	Color3f color=new Color3f(1,1,1);
	String name;
	public DirectionalLight(Vector3f direction, float intensity, Color3f color,
			String name) {
		super();
		this.direction = direction;
		if(intensity != 0){this.intensity = intensity;}
		if(color != null){this.color = color;}
		this.name = name;
	}
}
