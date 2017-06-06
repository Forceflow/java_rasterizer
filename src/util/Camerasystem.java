package util;

import support.Vector4f;

public class Camerasystem 
{
	public int screenx;
	public int screeny;
	public float n;
	public float f;
	public float t;
	public float r;
	public float l;
	public float b;
	private Point4f camera;
	public Vector4f gaze;
	public Vector4f viewup;
	public float fov_theta;
	public Vector4f u;
	public Vector4f v;
	public Vector4f w;
	
	public Camerasystem(int screenx, int screeny, Point4f camera, Vector4f gaze, Vector4f viewup, float fov_theta, float n, float f)
	{
		this.screenx = screenx;
		this.screeny = screeny;
		this.camera = camera;
		this.n = n;
		this.f = f;
		this.gaze = gaze;
		this.viewup = viewup;
		this.fov_theta = fov_theta;
		// compute center of window coordinates 
		double s = Math.tan(fov_theta/2);
		t = (float) s * Math.abs(n);
		r = t * (screenx/screeny);
		l = -r;
		b = -t;
		r = -r;
		l =	-l;
		// compute uvw
		computeUVW(gaze, viewup);
	}
	
	private void computeUVW(Vector4f gaze, Vector4f viewup)
	{
		w = gaze.normalize().scale(-1f);
		u = viewup.cross(w).normalize();
		v = w.cross(u);
	}
	
	public int getScreenx() {
		return screenx;
	}

	public int getScreeny() {
		return screeny;
	}

	public Point4f getCamera() {
		return camera;
	}

	public float getN() {
		return n;
	}

	public float getF() {
		return f;
	}

	public float getT() {
		return t;
	}

	public float getR() {
		return r;
	}

	public float getL() {
		return l;
	}

	public float getB() {
		return b;
	}

	public Vector4f getU() {
		return u;
	}

	public Vector4f getV() {
		return v;
	}

	public Vector4f getW() {
		return w;
	}
	
	

}
