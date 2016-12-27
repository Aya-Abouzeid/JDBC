package eg.edu.alexu.csd.oop.VectorDrawing;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.*;
 import java.awt.color.*;
import javax.naming.AuthenticationNotSupportedException;
import javax.swing.JComponent;
/**
 *
 * @author aya_a_000
 *
 */
public class Shape implements VectorDrawingInterface {
/**
 *
 * @return image
 */
	protected Image getImage() {
		return image;
	}
	/**
	 *
	 * @param image setimage
	 *
	 */
	protected void setImage(final Image image) {
		this.image = image;
	}
	/**
	 *
	 * @return old x
	 */
	protected int getfirstPointX() {
		return firstPointX;
	}
	/**
	 *
	 * @param firstPointX x coordinate of first point
	 */
	protected void setfirstPointX(final int firstPointX) {
		this.firstPointX = firstPointX;
	}
	/**
	 *
	 * @return y coordinate of first point
	 */
	protected int getfirstPointY() {
		return firstPointY;
	}
	/**
	 *
	 * @param firstPointY set y coordinate of first point
	 */
	protected void setfirstPointY(final int firstPointY) {
		this.firstPointY = firstPointY;
	}
	/**
	 *
	 * @return x coordinate of new point
	 */
	protected int getsecondPointX() {
		return secondPointX;
	}
	/**
	 *
	 * @param secondPointX x coordinate of new point
	 */
	protected void setsecondPointX(final int secondPointX) {
		this.secondPointX = secondPointX;
	}
	/**
	 *
	 * @return y coordinate of new point
	 */
	protected int getsecondPointY() {
		return secondPointY;
	}
	/**
	 *
	 * @param secondPointY y coordinate of new point
	 */
	protected void setsecondPointY(final int secondPointY) {
		this.secondPointY = secondPointY;
	}
	/**
	 *
	 * @return 3rd x
	 */
	protected int getthirdPointX() {
		return thirdPointX;
	}
	/**
	 *
	 * @param thirdPointX 3rd x
	 */
	protected void setthirdPointX(final int thirdPointX) {
		this.thirdPointX = thirdPointX;
	}
	/**
	 *
	 * @return 3rd y
	 */
	protected int getthirdPointY() {
		return thirdPointY;
	}
	/**
	 *
	 * @param thirdPointY 3rd y
	 */
	protected void setthirdPointY(final int thirdPointY) {
		this.thirdPointY = thirdPointY;
	}
	/**
	 *.
	 * @return outline
	 */
	protected Color getC() {
		return c;
	}
	/**
	 *
	 * @param c outline
	 */
	protected void setC(final Color c) {
		this.c = c;
	}
	/**
	 *
	 * @return filled
	 */
	protected Color getC2() {
		return c2;
	}
	/**
	 *
	 * @param c2 filled
	 */
	protected void setC2(final Color c2) {
		this.c2 = c2;
	}
/**
 *
 * @return dash
 */
	protected static float[] getDash1() {
		return dash1;
	}
	/**.
	 * dash line
	 * @return dash line
	 */
	protected static BasicStroke getDashed() {
		return dashed;
	}
	/**
	 *
	 * @return STROKE
	 */
	protected Stroke getStroke() {
		return stroke;
	}
	/**.
	 * dashed line
	 */
	final static float dash1[] = {10.0f};
	/**.
	 * dashed line
	 */
    final static BasicStroke dashed =
            new BasicStroke(2f,
                            BasicStroke.CAP_BUTT,
                            BasicStroke.JOIN_MITER,
                            10.0f, dash1, 0.0f);
    /**.
     * image
     */
   protected Image image;
   /**.
    * x first point
    */
   protected int firstPointX;
   /**.
    * y first point
    */
   protected int firstPointY;
   /**.
    * x second point
    */
   protected int secondPointX;
   /**.
    * y second point
    */
   protected int secondPointY;
   /**.
    * 3rd point x
    */
   protected int thirdPointX;
   /**.
    * 3rd point y
    */
   protected int thirdPointY;
   /**.
    * outline color
    */
   protected Color c;
   /**.
    * fill color
    */
   protected Color c2;
   /**.
    * stroke color
    */
	 protected Stroke stroke;

	 /**.
	     *@param x1 first x
	     *@param x2 second x
	     *@param y1 first y
	     *@param y2 second y
	     *@return x
	     *@param thirdPointX 3rd x
	     *@param extra2 3rd y
	     *@param color outline
	     *@param color2 fill
	     *@param s stroke color
	     *
	     */
	 public Shape newShape(final int x1, final int y1, final int x2,
			 final int y2, final int thirdPointX,
			 final int extra2, final Color color,
			 final Color color2, final Stroke s) {
			Shape x = new Shape();
			return x;
		}
	 /**
	  *@param s for stroke
	  */
	 public void setStroke(final Stroke s) {

	 }
	 /**.
	  * draw shape
	  * @param g for graphics
	  */
    public void draw(final Graphics g) {


    	  }
    /**.
     * move shape
     * *@param x x
 *@param y y
 *@return shape
     */
public Shape move(final int x, final int y) {
	Shape shape = new Shape();
	return shape;
}
/**.
 *resize shape
 *@param x x
 *@param y y
 *@return shape
 */
public Shape resize(final int x, final int y) {
	Shape shape = new Shape();
	return shape;
}
/**.
 * set filling color
 * @param c color
 */
public void setColor(final Color c) {

}
/**.
 * fills shape with color
 */
    public void fill( Graphics g){

    }
    /**
     *@param g for Graphics
     *@param x coordinate
     *@param y coordinate
     *@return boolean isshape in this area
     */
    public boolean isShape(final Graphics g, final int x, final int y) {

    	return false;
    }

}