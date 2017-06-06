package util;

public abstract class Triangle 
{
	// cornerpoints XYZ
	public Point4f a;
	public Point4f b;
	public Point4f c;
	// barycentric functions
	private float f_alpha;
	private float f_beta;
	private float f_gamma;
	
	protected Triangle(Point4f[] corners)
	{
		this.a = corners[0];
		this.b = corners[1];
		this.c = corners[2];
		calculateBaryFunctions();
	}
	
	public Point4f[] getVertices()
	{
		Point4f[] answer = new Point4f[3];
		answer[0] = a;
		answer[1] = b;
		answer[2] = c;
		return answer;
	}
	
	/**
	 * Check whether or not the given point is inside this triangle or not, using barycentric coordinates
	 * @param point
	 * @return
	 */
	public boolean isInTriangle(Point4f point)
	{
		// compute barycentric coordinates
		Point4f bary = getBaryCentric(point);
		float alpha = bary.x;
		float beta = bary.y;
		float gamma = bary.z;
		// test regular points
		if((alpha >= 0) && (beta >= 0) && (gamma >=0))
		{
			// test triangle edges points
			Point4f tester = new Point4f(-1, -1, 0);
			if( ( (alpha > 0) | (f_alpha*Implicitline(b,c,tester) > 0) ) &&
				( (beta > 0) | (f_beta*Implicitline(c,a,tester) > 0) ) &&	
			    ( (gamma > 0) | (f_gamma*Implicitline(a,b,tester) > 0) ) )
			{
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Return the barycentric coordinates of a given point, relative to this Triangle
	 * @param point
	 * @return
	 */
	public Point4f getBaryCentric(Point4f point)
	{
		float beta = Implicitline(c,a,point)/f_beta;
		float gamma = Implicitline(a,b,point)/f_gamma;
		float alpha = 1-beta-gamma;
		return new Point4f(alpha,beta,gamma);
	}
	
	/**
	 * Helper function to compute implicit lines
	 */
	private float Implicitline(Point4f a, Point4f b, Point4f ev)
	{
		return (a.y-b.y)*ev.x + (b.x-a.x)*ev.y + (a.x*b.y) - (b.x*a.y);
	}
	
	/**
	 * Compute baryfunctions once and for all
	 */
	private void calculateBaryFunctions()
	{
		// calculate the f_ functions once
		f_alpha = Implicitline(b,c,a);
		f_beta = Implicitline(c,a,b);
		f_gamma = Implicitline(a,b,c);
	}
	
	/**
	 * Get the 3D boundingbox of this Triangle
	 * @return
	 */
	public int[] getBoundingBox()
	{
		int minx = (int) Math.floor(Math.min(a.x, b.x));
		minx = (int) Math.floor(Math.min(minx, c.x));
	
		int maxx = (int) Math.ceil(Math.max(a.x, b.x));
		maxx = (int) Math.ceil(Math.max(maxx, c.x));
		
		int miny = (int) Math.floor(Math.min(a.y, b.y));
		miny = (int) Math.floor(Math.min(miny, c.y));
		
		int maxy = (int) Math.ceil(Math.max(a.y, b.y));
		maxy = (int) Math.ceil(Math.max(maxy, c.y));
		
		int minz = (int) Math.floor(Math.min(a.z, b.z));
		miny = (int) Math.floor(Math.min(miny, c.z));
		
		int maxz = (int) Math.ceil(Math.max(a.z, b.z));
		maxy = (int) Math.ceil(Math.max(maxy, c.z));
		
		int[] answer = {minx,maxx,miny,maxy,minz,maxz};
		return answer;
	}

}
