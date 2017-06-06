package demo;

import java.util.ArrayList;

import javax.swing.JFrame;
import raster.BSPNode;
import raster.Rasterizer;
import raster.ZBuffer;
import support.CgPanel;
import support.Color3f;
import support.FileReader;
import support.ObjParser;
import support.Vector4f;
import util.Camerasystem;
import util.Light;
import util.Lightsystem;
import util.Point4f;
import util.WorldTriangle;

public class BSPTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String object = "torus";
		FileReader reader = new FileReader("src/objects/"+object+".obj");
		ObjParser parser = new ObjParser(reader);
		System.out.println("reading triangles from file");
		ArrayList<WorldTriangle> triangles = parser.getTriangles();
		System.out.println("Building BSP tree ...");
		BSPNode root = new BSPNode(triangles.get(0));
		for (int i = 1; i<triangles.size(); i++)
		{
			root.add(triangles.get(i));
		}
		//System.out.println(root);
		
		//	camera enzo
		Point4f camera = new Point4f(5,5,5);
		Vector4f gaze = new Vector4f(-5,-5,-5);
		Vector4f viewup = new Vector4f(0,1,0);
		float fov_theta = (float) Math.toRadians(45);
		float n = 0.1f;
		float f = -999;
		Camerasystem cams = new Camerasystem(512,512,camera, gaze, viewup,fov_theta,n,f);
		Lightsystem ll = new Lightsystem();
		ll.addLight(new Light(new Point4f(10,10,0), new Color3f(1,0,0)));
		ll.addLight(new Light(new Point4f(0,10,10), new Color3f(0,0,1)));

		// create panel
		CgPanel panel = new CgPanel();
		JFrame frame = new JFrame();
		frame.setSize(512,512);
		frame.getContentPane().add(panel);
		frame.setVisible(true);
		// bugfix: wait is necessary to let panel initialize
		try {
			System.out.println("waiting");
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		panel.clear();
		Rasterizer r = new Rasterizer(cams,ll,panel);
		long start = System.currentTimeMillis();
		r.RasterizeTree(root);
		long stop = System.currentTimeMillis();
		System.out.println("BSP drawing took " + (stop-start) + " milliseconds");
//		panel.clear();
//		ZBuffer z = new ZBuffer(512,512);
//		start = System.currentTimeMillis();
//		r.RasterizeTriangles(triangles, z);
//		stop = System.currentTimeMillis();
//		System.out.println("ZBuffer drawing took " + (stop-start) + " milliseconds");
		
	}
}