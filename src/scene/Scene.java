package scene;

import scene.graph.SceneNode;
import util.Camerasystem;
import util.Lightsystem;

public class Scene 
{
	String name;
	Camerasystem camera;
	Lightsystem lights;
	SceneNode graph;
	
	public Scene(String name, Camerasystem camera, Lightsystem lights,
			SceneNode graph) {
		super();
		this.name = name;
		this.camera = camera;
		this.lights = lights;
		this.graph = graph;
	}
	
	public String getName() {
		return name;
	}

	public Camerasystem getCamera() {
		return camera;
	}

	public Lightsystem getLights() {
		return lights;
	}

	public SceneNode getGraph() {
		return graph;
	}

}
