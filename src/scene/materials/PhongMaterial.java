package scene.materials;

import support.Color3f;

public class PhongMaterial extends Material
{

	public float getShininess() {
		return shininess;
	}
	public PhongMaterial(Color3f color, float shininess, String name) {
		super(color,name);
		this.shininess = shininess;
	}
	private float shininess;

}
