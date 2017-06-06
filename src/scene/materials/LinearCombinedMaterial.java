package scene.materials;

import support.Color3f;

public class LinearCombinedMaterial extends Material
{
	Material one;
	Material two;
	float w1;
	float w2;

	public LinearCombinedMaterial(Material one, float w1, Material two, float w2, String name) {
		super(null, name);
		this.one = one;
		this.two = two;
		this.w1 = w1;
		this.w2 = w2;
	}
	
	@Override
	public Color3f getColor()
	{
		return one.getColor().scale(w1).add(two.getColor().scale(w2));
	}
}
