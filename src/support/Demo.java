package support;
import javax.swing.*;

import scene.Scene;
import scene.SceneBuilder;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

public class Demo implements MouseListener {

public static void main(String[] args) {
	new Demo();
}

private int x;
private int y;
private JFrame frame;
private CgPanel panel;

public Demo() {
	try {
		SceneBuilder sceneBuilder = new SceneBuilder();
		Scene scene = sceneBuilder.loadScene("src/support/XML/example.sdl");
	}
	catch (Exception e) {
		e.printStackTrace();
	}


	panel = new CgPanel();
	panel.addMouseListener(this);
	frame = new JFrame();
	frame.setSize(640,480);
	frame.getContentPane().add(panel);
	frame.setVisible(true);
}

public void mousePressed(MouseEvent e) { }
public void mouseClicked(MouseEvent e) { timePerformance(); }
public void mouseEntered(MouseEvent e) { }
public void mouseExited(MouseEvent e) { }
public void mouseReleased(MouseEvent e) { }

public void drawPixels() {
	for (int row=10; row<panel.getHeight()-10; row++) {
		for (int column=10; column<panel.getWidth()-10; column++) {
			if (column>x && column < x+100) {
			panel.drawPixel(column, row, 1,0,0);
			}
			else if (row>y && row < y+100) {
			panel.drawPixel(column, row, 1, 1,0);
			}
			else {
			panel.drawPixel(column, row, 0.5f,0.5f,0.5f);
			}
		}
	}
	x = (x+1)%(panel.getWidth()+1);
	y = (y+1)%(panel.getHeight()+1);
}


public void timePerformance() {
	long starttime = System.currentTimeMillis();
	for (int i=0; i<100; i++) {
		panel.clear(0,0,1);
		drawPixels();
		panel.repaint();
		panel.flush();
	}
	long endtime = System.currentTimeMillis();
	double fps = 1000.0/((double)(endtime-starttime)/100.0);
	System.out.println("Fps: " + fps);
	panel.saveImage("image.png");
}
}
