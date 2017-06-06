package demo;

import java.io.FileNotFoundException;

import javax.swing.JFrame;


import scene.Scene;
import scene.SceneBuilder;
import scene.SceneDrawer;
import support.CgPanel;

public class SceneDrawTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		// load scene
		Scene scene = null;
		try {	
		SceneBuilder sceneBuilder = new SceneBuilder();
		scene = sceneBuilder.loadScene("src/support/XML/test.sdl");
		} catch (FileNotFoundException e){ e.printStackTrace();}
		
		// create panel
		CgPanel panel = new CgPanel();
		JFrame frame = new JFrame();
		frame.setSize(512,512);
		frame.getContentPane().add(panel);
		frame.setVisible(true);
		// bugfix: wait is necessary to let panel initialize
		try {System.out.println("Waiting for CgPanel launch");Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
		panel.clear();
		
		SceneDrawer sd = new SceneDrawer(scene,panel);
		sd.draw();
	}

}
