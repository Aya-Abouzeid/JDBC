package eg.edu.alexu.csd.oop.VectorDrawing;

import java.awt.Color;
import java.awt.Stroke;

import eg.edu.alexu.csd.oop.VectorDrawing.Ellipse;;
/**
 *
 * @author aya_a_000
 *
 */
public class Circle extends Ellipse {
/**
 *
 * @param x1 top left x
 * @param y1 top left y
 * @param x2 length
 * @param y2 width
 * @param extra1 extra
 * @param extra2 extra
 * @param color outline
 * @param color2 filled
 * @param s stroke
 */
	public Circle(final int x1, final int y1, final int x2, final int y2,
			final int extra1, final int extra2, final Color color,
			final Color color2, final Stroke s) {
		super(x1, y1, x2, x2, extra1, extra2, color, color2, s);
	}
/**.
 * half length or width
 */
	private final double halfdistance = 0.5;

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 *
	 * @param x1 top left x
	 * @param y1 top left y
	 * @param x2 length
	 * @param y2 width
	 * @param extra1 extra
	 * @param extra2 extra
	 * @param color outline
	 * @param color2 filled
	 * @param s stroke
	 * @return new circle
	 */
	public Circle newShape(final int x1, final int y1, final int x2,
			final int y2, final int extra1, final int extra2,
			final Color color, final Color color2, final Stroke s) {
			Circle circle = new Circle(x1, y1,
					x2, y2, extra1,
					extra2, color, color2, s);

			return circle;
	}
	/**.
	 *resizing circle
	 *@param x coordinate
	 *@param y coordinate
	 *@return circle
	 */
    public Circle resize(final int x, final int y) {

    	  Circle circle = new Circle();

    	  if (x >= super.firstPointX + halfdistance * super.secondPointX
    		&& y <= super.firstPointY + halfdistance * super.secondPointY) {
    		  circle = new Circle(super.firstPointX, y,
	 x - super.firstPointX, super.secondPointY + (super.firstPointY - y),
	     0, 0, super.c, super.c2, super.stroke);

    	  } else if (x <= super.firstPointX + halfdistance * super.secondPointX
    	  && y <= super.firstPointY + halfdistance * super.secondPointY) {
    		  circle = new Circle(x, y,
		                  super.secondPointX + (super.firstPointX - x),
		                  super.secondPointY + (super.firstPointY - y),
		                  0, 0, super.c, super.c2, super.stroke);
  } else if (y >= super.firstPointY + halfdistance * super.secondPointY
	  && x <= super.firstPointX + halfdistance * super.secondPointX) {
   circle = new Circle(x, super.firstPointY, super.secondPointX
		   + (super.firstPointX - x),
        y - super.firstPointY, 0, 0, super.c, super.c2, super.stroke);
	} else if (x >= super.firstPointX + halfdistance * super.secondPointX
	 && y >= super.firstPointY + halfdistance * super.secondPointY) {
    		  circle = new Circle(super.firstPointX, super.firstPointY,
(x - super.firstPointX), (y - super.firstPointY),
0, 0, super.c, super.c2, super.stroke);
		         	  }
    	return circle;
    }

/**.
 * constructor
 */
	public Circle() {

	}
}
