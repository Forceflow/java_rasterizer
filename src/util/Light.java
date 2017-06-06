package util;

import support.Color3f;

public class Light 
{
	private Point4f position;
	private Color3f color;
	
	public Light(Point4f position, Color3f color)
	{
		this.position = position;
		this.color = color;
	}
	
	public Point4f getPosition() {
		return position;
	}

	public Color3f getColor() {
		return color;
	}


}
