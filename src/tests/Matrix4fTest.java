package tests;

import support.Matrix4f;

public class Matrix4fTest {
	
	public static void main(String[] args) 
	{
		Matrix4f test = new Matrix4f();
		test.m00 = 1;
		test.m01 = 2;
		test.m02 = 3;
		test.m03 = 4;
		test.m10 = 4;
		test.m11 = 3;
		test.m12 = 2;
		test.m13 = 1;
		test.m20 = 5;
		test.m21 = 6;
		test.m22 = 7;
		test.m23 = 8;
		test.m30 = 8;
		test.m31 = 7;
		test.m32 = 6;
		test.m33 = 5;
		Matrix4f test2 = new Matrix4f();
		test2.m00 = 4;
		test2.m01 = 5;
		test2.m02 = 6;
		test2.m03 = 7;
		test2.m10 = 7;
		test2.m11 = 6;
		test2.m12 = 5;
		test2.m13 = 4;
		test2.m20 = 0;
		test2.m21 = 1;
		test2.m22 = 2;
		test2.m23 = 3;
		test2.m30 = 3;
		test2.m31 = 2;
		test2.m32 = 1;
		test2.m33 = 0;
		System.out.println(test2.mul_right(test.mul_right(test2)));
		Matrix4f invtest = new Matrix4f();
		invtest.setIdentity();
		invtest.m01 = 2;
		invtest.m03 = 3;
		invtest.m12 = 4;
		invtest.m30 = 6;
		System.out.println(invtest.invert());
		System.out.println(invtest.invert().transpose());
	}

}
