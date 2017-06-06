package scene.graph;

import support.Matrix4f;
import support.Vector3f;

public class TranslationNode extends TransformationNode{

	Vector3f transvector;
	public TranslationNode(Node parent, Vector3f vector) 
	{
		super(parent);
		transvector = vector;
		transformation = generateTranslationMatrix();
	}

	private Matrix4f generateTranslationMatrix() {
		Matrix4f answer = new Matrix4f();
		answer.setIdentity();
		answer.m03 = transvector.x;
		answer.m13 = transvector.y;
		answer.m23 = transvector.z;
		return answer;
	}
	
	public String toString()
	{
		return "Translation Node : " + transvector;
	}

}
