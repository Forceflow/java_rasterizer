package scene.graph;

public class SceneNode extends Node {
	public SceneNode() 
	{
		super(null);
	}

	@Override
	public Node getParent()
	{
		return this;
	}
	
}
