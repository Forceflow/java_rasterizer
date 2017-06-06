package scene.graph;

import support.Matrix4f;
import support.Vector3f;

public class ScaleNode extends TransformationNode 
{
	Vector3f scale;

	public ScaleNode(Node parent, Vector3f scale) {
		super(parent);
		this.scale = scale;
		transformation = generateScaleMatrix();
	}

	private Matrix4f generateScaleMatrix() {
		Matrix4f answer = new Matrix4f();
		answer.m00 = scale.x;
		answer.m11 = scale.y;
		answer.m22 = scale.z;
		answer.m33 = 1;
		return answer;
	}
	
	public String toString()
	{
		return "Scale Node : " + scale;
	}

}
