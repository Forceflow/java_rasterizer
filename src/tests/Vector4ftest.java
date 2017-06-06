package tests;

import support.Vector4f;

public class Vector4ftest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Vector4f s = new Vector4f(1,2,3);
		s.scale(2);
		System.out.println(s);
		s.cross(new Vector4f(1,0,3));
		System.out.println(s);
	}

}
