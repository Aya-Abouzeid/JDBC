package eg.edu.alexu.csd.oop.VectorDrawing;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Stroke;
/**
 *
 * @author aya_a_000
 *
 */
public class Square extends Rectangle {
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
	public Square(final int x1, final int y1, final int x2, final int y2,
			final int extra1, final int extra2, final Color color,
			final Color color2, final Stroke s) {
		super(x1, y1, x2, x2, extra1, extra2, color, color2, s);
	}
	/**.
	 * half length or width
	 */
		private final double halfDistance = 0.5;
	private static final long serialVersionUID = 1L;
	/**.
	 *resizing square
	 *@param x coordinate
	 *@param y coordinate
	 *@return square
	 */
public Square resize(final int x, final int y) {
	Square square = new Square();

	  if (x >= super.firstPointX + halfDistance * super.secondPointX
	  && y <= super.firstPointY + halfDistance * super.secondPointY) {
		  square = new Square(super.firstPointX, y,
                x - super.firstPointX, super.secondPointY
                + (super.firstPointY - y), 0, 0,
                super.c, super.c2, super.stroke);
} else if (x <= super.firstPointX + halfDistance * super.secondPointX
 && y <= super.firstPointY + halfDistance * super.secondPointY) {
		  square = new Square(x, y,
	                  super.secondPointX + (super.firstPointX - x),
	              super.secondPointY + (super.firstPointY - y),
	                  0, 0, super.c, super.c2, super.stroke);
   	  } else if (y >= super.firstPointY + halfDistance * super.secondPointY
	  && x <= super.firstPointX + halfDistance * super.secondPointX) {
 square = new Square(x, super.firstPointY,
		 super.secondPointX + (super.firstPointX - x),
	 y - super.firstPointY, 0, 0, super.c, super.c2, super.stroke);
} else if (x >= super.firstPointX + halfDistance * super.secondPointX
		&& y >= super.firstPointY + halfDistance * super.secondPointY) {
		  square = new Square(super.firstPointX, super.firstPointY,
	                  (x - super.firstPointX), (y - super.firstPointY),
	                  0, 0, super.c, super.c2, super.stroke);
	         	  }
	return square;
}
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
* @return new square
*/
	public Square newShape(final int x1, final int y1, final int x2,
			final int y2, final int extra1, final int extra2,
			final Color color, final Color color2, final Stroke s) {
			Square square = new Square(x1, y1,
					x2, y2, extra1,
					extra2, color, color2, s);

			return square;
	}
	/**.
	 * constructor
	 */
public Square() {

	}

}
