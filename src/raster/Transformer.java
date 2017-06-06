package raster;

import support.Matrix4f;
import util.Camerasystem;
import util.Point4f;
import util.ScreenTriangle;
import util.WorldTriangle;

public class Transformer 
{
	static boolean DEBUG = false;
	Camerasystem c;
	Matrix4f ortho;
	Matrix4f view;
	Matrix4f persp;
	Matrix4f total;
	
	public Transformer(Camerasystem c)
	{
		// set camerasystem and compute matrices once and for all
		this.c = c;
		ortho = OrthograpicMatrix();
			if(DEBUG){System.out.println("ortho \n" + ortho);}
		view = ViewMatrix();
			if(DEBUG){System.out.println("view \n" + view);}
		persp = PerspectiveMatrix();
			if(DEBUG){System.out.println("persp \n" + persp);}
		total = ortho.mul_right(persp.mul_right(view));
			if(DEBUG){System.out.println("total \n" + total);}
	}
	
	/**
	 * Transform a given point (using the center-of-window approach) and return a copy
	 * @param source
	 * @param screenx
	 * @param screeny
	 * @param camera
	 * @param gaze
	 * @param viewup
	 * @param fov_theta
	 * @param n
	 * @return
	 */
	public Point4f transform(Point4f source)
	{
		Point4f trans = source.left_mul(total);
		return trans.scale(1/trans.w);
	}
	
	public ScreenTriangle transform (WorldTriangle d)
	{
		Point4f[] vertices = (Point4f[]) d.getVertices();
		Point4f a = transform(vertices[0]);
			if(DEBUG){System.out.println("transformed vertex 1:  " + a);}
		Point4f b = transform(vertices[1]);
			if(DEBUG){System.out.println("transformed vertex 2:  " + b);}
		Point4f c = transform(vertices[2]);
			if(DEBUG){System.out.println("transformed vertex 3:  " + c);}
		Point4f[] corners = {a,b,c};
		return new ScreenTriangle(corners,d);
	}
	
	public Matrix4f getOrthoMatrix() {
		return ortho;
	}

	public Matrix4f getViewMatrix() {
		return view;
	}

	public Matrix4f getPerspMatrix() {
		return persp;
	}

	public Matrix4f getTotalMatrix() {
		return total;
	}
	
	private Matrix4f OrthograpicMatrix()
	{
		// first matrix
		Matrix4f mat1 = new Matrix4f();
		mat1.m00 = c.getScreenx()/2;
		mat1.m03 = (c.getScreenx() -1) / 2;
		mat1.m11 = c.getScreeny()/2;
		mat1.m13 = (c.getScreeny()-1f) / 2;
		mat1.m22 = 1;
		mat1.m33 = 1;
			if(DEBUG){System.out.println("Ortho1 " + mat1);}
		// second matrix
		Matrix4f mat2 = new Matrix4f();
		mat2.m00 = (2 / (c.getR()-c.getL()));
		mat2.m11 = (2 / (c.getT()-c.getB()));
		mat2.m22 = (2 / (c.getN()-c.getF()));
		mat2.m33 = 1;
			if(DEBUG){System.out.println("Ortho2 " + mat2);}
		// third matrix
		Matrix4f mat3 = new Matrix4f();
		mat3.setIdentity();
		mat3.m03 = -((c.getL()+c.getR())/2);
		mat3.m13 = -((c.getB()+c.getT())/2);
		mat3.m23 = -((c.getN()+c.getF())/2);
			if(DEBUG){System.out.println("Ortho3 " + mat3);}
		// right multiplication
		return mat1.mul_right(mat2.mul_right(mat3));
	}
	
	private Matrix4f ViewMatrix ()
	{
		// build matrix 1
		Matrix4f mat1 = new Matrix4f();
		mat1.m00 = c.getU().x;
		mat1.m10 = c.getV().x;
		mat1.m20 = c.getW().x;
		mat1.m01 = c.getU().y;
		mat1.m11 = c.getV().y;
		mat1.m21 = c.getW().y;
		mat1.m02 = c.getU().z;
		mat1.m12 = c.getV().z;
		mat1.m22 = c.getW().z;
		mat1.m33 = 1;
			if(DEBUG){System.out.println("View1 " + mat1);}
		
		// build matrix 2
		Matrix4f mat2 = new Matrix4f();
		mat2.setIdentity();
		mat2.m03 = -c.getCamera().x;
		mat2.m13 = -c.getCamera().y;
		mat2.m23 = -c.getCamera().z;
			if(DEBUG){System.out.println("View2 " + mat2);}
		
		// multiply and return matrix
		return mat1.mul_right(mat2);
	}
	
	private Matrix4f PerspectiveMatrix()
	{
		Matrix4f mat = new Matrix4f();
		mat.m00 = c.getN();
		mat.m11 = c.getN();
		mat.m22 = c.getN()+c.getF();
		mat.m32 = 1;
		mat.m23 = -c.getF()*c.getN();
		return mat;
	}
	
}
