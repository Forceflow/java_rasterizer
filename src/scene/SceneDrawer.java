package scene;

import java.util.ArrayList;

import raster.Rasterizer;
import raster.ZBuffer;
import scene.graph.Node;
import scene.graph.SceneNode;
import scene.graph.ShapeNode;
import scene.graph.TransformationNode;
import support.CgPanel;
import support.Matrix4f;
import util.Constants;
import util.WorldTriangle;

public class SceneDrawer 
{
	private Scene scene;
	private CgPanel panel;

	public SceneDrawer(Scene myscene, CgPanel panel)
	{
		this.scene = myscene;
		this.panel = panel;
	}
	
	public void draw()
	{
		// create a Rasterizer to process the triangles pulled from the Graph
		Rasterizer r = new Rasterizer(scene.getCamera(),scene.getLights(),panel);
		// create ZBuffer
		ZBuffer z = new ZBuffer(scene.getCamera().getScreenx(),scene.getCamera().getScreeny());
		// search root for things to draw
		SceneNode root = scene.getGraph();
		drawShapes(root,r,z,new ArrayList<Node>());
	}

	// recursive power!
	@SuppressWarnings("unchecked")
	private void drawShapes(Node node, Rasterizer r, ZBuffer z, ArrayList<Node> path) 
	{
		if(Constants.DEBUG_SCENEGRAPH){System.out.println("Visiting ..." + node);}
		// clone the path
		ArrayList<Node> path2 = (ArrayList<Node>) path.clone();
		// add this node to path
		path2.add(node);
		// if this node is a ShapeNode, it must be drawn now
		if(node instanceof ShapeNode){drawShape((ShapeNode)node,r,z,path2);}
		for(Node n : node.getKids()) 
		{ 
			drawShapes(n,r,z,path2);
		}
		if(Constants.DEBUG_SCENEGRAPH){System.out.println("Exiting ..." + node);}
	}

	private void drawShape(ShapeNode node, Rasterizer r, ZBuffer z,ArrayList<Node> path)
	{
		if(Constants.DEBUG_SCENEGRAPH){System.out.println("Drawing " + node);}
		
		// find transformation nodes in the path
		ArrayList<TransformationNode> transfos = new ArrayList<TransformationNode>();
		for(Node n : path){if(n instanceof TransformationNode){transfos.add((TransformationNode) n);}}
		
		// iterate back to front to compute total M
		Matrix4f M = new Matrix4f();
		M.setIdentity();
		for(int i = transfos.size()-1; i>=0; i--)
		{
			TransformationNode current = transfos.get(i);
			M = current.getMatrix().mul_right(M);
		}
		
		// transform triangles and draw them
		ArrayList<WorldTriangle> ntriangles = new ArrayList<WorldTriangle>();
		for(WorldTriangle t : node.triangles)
		{
			r.RasterizeTriangle(t.transform(M),z);
		}
	}

}
