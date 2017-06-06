package scene.graph;

import support.Matrix4f;
import support.Vector3f;
import support.Vector4f;

public class RotationNode extends TransformationNode 
{
	
	Vector3f axis;
	float angle;

	public RotationNode(Node parent, Vector3f axis, float angle) 
	{
		super(parent);
		this.axis = axis;
		this.angle = angle;
		transformation = generateRotationMatrix();
	}

	private Matrix4f generateRotationMatrix() {
		
		// first vector of orthonormal
		Vector4f w = axis.get4f().normalize();
		
		// compute second vector of orthonormal
		Vector4f t = new Vector4f(w.x,w.y,w.z);
		float m = Math.min(t.x, t.y);
		float s = Math.min(m, t.z);
		if(w.x == s){t.x=1;}
		else if(t.y == s){t.y=1;}
		else if(t.z == s){t.z=1;}
		Vector4f u = t.cross(w).normalize();
		Vector4f v = w.cross(u);
		
		// first matrix (coordinate system change)
		Matrix4f mat1 = new Matrix4f();
		mat1.m00 = u.x;
		mat1.m01 = v.x;
		mat1.m02 = w.x;
		mat1.m10 = u.y;
		mat1.m11 = v.y;
		mat1.m12 = w.y;
		mat1.m20 = u.z;
		mat1.m21 = v.z;
		mat1.m22 = w.z;
		mat1.m33 = 1;
		
		// second matrix (actual rotation)
		Matrix4f mat2 = new Matrix4f();
		double ang = Math.toRadians(angle);
		mat2.m00 = (float) Math.cos(ang);
		mat2.m10 = (float) Math.sin(ang);
		mat2.m01 = (float) -Math.sin(ang);
		mat2.m11 = (float) Math.cos(ang);
		mat2.m22 = 1;
		mat2.m33 = 1;
		
		// third matrix (change coordsystem back)
		Matrix4f mat3 = new Matrix4f();
		mat3.m00 = u.x;
		mat3.m01 = u.y;
		mat3.m02 = u.z;
		mat3.m10 = v.x;
		mat3.m11 = v.y;
		mat3.m12 = v.z;
		mat3.m20 = w.x;
		mat3.m21 = w.y;
		mat3.m22 = w.z;
		mat3.m33 = 1;
		
		return mat1.mul_right(mat2.mul_right(mat3));
	}
	
	public String toString()
	{
		return "Rotation Node : " + axis;
	}

}
