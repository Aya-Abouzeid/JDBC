package eg.edu.alexu.csd.oop.VectorDrawing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Stroke;
/**
 *
 * @author aya_a_000
 *
 */
public interface VectorDrawingInterface {
	/**
	 *
	 * @param s for stroke
	 */
public void setStroke(Stroke s);
/**
 *
 * @param g for filling
 */
public void fill(Graphics g);
/**
 *
 * @param c for outline color
 */
public void setColor(Color c);
/**
 *
 * @param g for drawing
 */
    public void draw(Graphics g);
/**
 *
 * @param g to determine if it is a shape
 * @param x coordinate
 * @param y coordinate
 * @return boolean
 */
public boolean isShape(Graphics g, int x, int y);
/**
 *
 * @param x coordinate
 * @param y coordinate
 * @return shape moved
 */
public Shape move(int x, int y);
/**
*
* @param x coordinate
* @param y coordinate
* @return shape resized
*/
public Shape resize(int x, int y);
/**
 *
 * @param x1 coordinate
 * @param y1 coordinate
 * @param x2 coordinate
 * @param y2 y2 coordinate
 * @param extra1 3rd point x
 * @param extra2 3rd point y
 * @param color outline
 * @param color2 filling
 * @param s stroke
 * @return new shape
 */
public Shape newShape(int x1, int y1, int x2, int y2, int extra1,
		int extra2, Color color, Color color2, Stroke s);


}
