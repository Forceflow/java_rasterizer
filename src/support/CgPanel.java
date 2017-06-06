package support;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;


/**
 * Allows the drawing of single pixels with a given color.
 * Typical usage in render loop (assume object is called panel):
 *		panel.clear();
 *		for each pixel to be drawn:
 *			panel.drawPixel(...);
 *		panel.repaint();
 *		panel.flush();
 */
public class CgPanel extends JPanel  implements ComponentListener {

   /**
    * Construct a new CgPanel.
    */
   public CgPanel() {
      addComponentListener(this);
      componentResized(new ComponentEvent(this, ComponentEvent.COMPONENT_RESIZED));
   }

   /**
    * Draw a pixel at window location x,y with color r,g,b.
    * The coordinates x,y are supposed to be in the range [0...getWidth()[
    * and [0...getHeight()[ respectively.
    * The color channels r,g and b are supposed to be in the range [0...1]
    */ 
   public void drawPixel(int x, int y, float r, float g, float b) {
      if (x>=0 && x<getWidth() && y>=0 && y<getHeight()) {
         rgbpixels[x+getWidth()*y] = 255<<24 | (int)(255*r)<<16 | (int)(255*g)<<8 | (int)(255*b);
      }
   }
   
   /**
    * Clear the buffer with a black color.
    */
   public void clear() {
      clear(0,0,0);
   }
   
   /**
    * Clear the buffer with the given color.
    */
   public void clear(float r, float g, float b) {
      int color = 255<<24 | (int)(255*r)<<16 | (int)(255*g)<<8 | (int)(255*b);
      for (int i=0; i<getWidth()*getHeight(); i++) {
         rgbpixels[i] = color;
      }
   }

   /**
    * Force a redraw.
    */
   public void flush() {
      RepaintManager.currentManager(this).paintDirtyRegions();
   }

   /**
    * Save the buffer to a file.  The given file is supposed
    * to have the extension ".png".
    */
   public void saveImage(String file) {
      mis.newPixels();
      try {
         Graphics2D g2;
         BufferedImage buf = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
         g2 = buf.createGraphics();
         g2.drawImage(image,null,null);
         ImageIO.write(buf, "png", new File(file));
         System.out.println("Saving of image to " + file + " succeeded.");
      }
      catch (Exception e) {
         System.out.println("Saving of image to " + file + " failed.");
      }
   }
   
   public void paint(Graphics g) {
       update(g);
   }
   
   public void update(Graphics g) {
      mis.newPixels();
      g.drawImage(image,0,0,this);
   }
   
   
   // --------------------------------------------------------------------------------------------------
   // Implementation of ComponentListener interface
   // --------------------------------------------------------------------------------------------------
   public void componentResized(ComponentEvent e) {
      rgbpixels = new int[getWidth()*getHeight()];
      for (int i=0; i<getWidth()*getHeight(); i++) {
         rgbpixels[i] = 255<<24;
      }
      mis = new MemoryImageSource(getWidth(), getHeight(), rgbpixels, 0, getWidth());
      mis.setAnimated(true);
      image = createImage(mis);
   }

   public void componentHidden(ComponentEvent e) { }
   public void componentMoved(ComponentEvent e) { }
   public void componentShown(ComponentEvent e) { }
   
   
      
   private int[] rgbpixels;
   private Image image;
   private MemoryImageSource mis;
}
