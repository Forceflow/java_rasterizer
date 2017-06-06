package scene.graph;

import java.util.ArrayList;

public abstract class Node 
{
	private Node parent;
	private ArrayList<Node> kids = new ArrayList<Node>();
	
	public Node(Node parent, ArrayList<Node> kids)
	{
		this.parent = parent;
		this.kids = kids;
	}
	
	public Node(Node parent)
	{
		this.parent = parent;
	}
	
	public void addKid(Node kid)
	{
		kids.add(kid);
	}
	
	public ArrayList<Node> getKids()
	{
		return kids;
	}
	
	public Node getParent() {
		return parent;
	}

}
