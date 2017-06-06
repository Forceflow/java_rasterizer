package scene.graph;

import support.Matrix4f;

public class TransformationNode extends Node 
{
	
	protected Matrix4f transformation;
	
	public TransformationNode(Node parent)
	{
		super(parent);
	}
	
	public Matrix4f getMatrix() {
		return transformation;
	}

}
