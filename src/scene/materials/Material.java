package scene.materials;

import support.Color3f;

public abstract class Material {

	public Color3f getColor() {
		return color;
	}

	public String getName() {
		return name;
	}

	Color3f color;
	String name; 
	
	public Material(Color3f color, String name) {
		this.color = color;
		this.name = name;
	}

}
