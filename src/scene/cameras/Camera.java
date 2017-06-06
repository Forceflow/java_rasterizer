package scene.cameras;

import support.Point3f;
import support.Vector3f;

public class Camera 
{
	public Point3f getPosition() {
		return position;
	}

	public Vector3f getDirection() {
		return direction;
	}

	public Vector3f getUp() {
		return up;
	}

	public float getFovy() {
		return fovy;
	}

	public String getName() {
		return name;
	}

	Point3f position;
	Vector3f direction;
	Vector3f up;
	float fovy;
	String name;
	
	public Camera(Point3f position, Vector3f direction, Vector3f up,
			float fovy, String name) {
		super();
		this.position = position;
		this.direction = direction;
		this.up = up;
		this.fovy = fovy;
		this.name = name;
	}
}
