package eg.edu.alexu.csd.oop.VectorDrawing;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Stroke;
/**
 *
 * @author aya_a_000
 *
 */
public class Triangle extends Shape {
	/**.
	 * dashes
	 */
	final static float dash1[] = {10.0f};
	/**.
	 * dashes
	 */
    final static BasicStroke dashed =
            new BasicStroke(2f,
                            BasicStroke.CAP_BUTT,
                            BasicStroke.JOIN_MITER,
                            10.0f, dash1, 0.0f);

	int[] x = new int[4];
	int[] y = new int[4];
	Polygon p;
/**.
 * for polygon
 */
	private int X1, Y1, X2, Y2, X3, Y3;
	/**.
	 * constructor
	 */
public Triangle() {

    }
/**.
 *move triangle
 *@param x coordinate
 *@param y coordinate
 *@return triangle
 */
public Triangle move(final int x, final int y) {

	Triangle triangle = new Triangle(super.firstPointX
			+ (x - super.firstPointX),
			super.firstPointY + (y - super.firstPointY),
			super.secondPointX + (x - super.firstPointX),
			super.secondPointY + (y - super.firstPointY),
			super.thirdPointX + (x - super.firstPointX),
			super.thirdPointY + (y - super.firstPointY),
			super.c, super.c2, super.stroke);

	return triangle;
}
/**.
 * of rectangle
 */
protected final int threePoints = 3;
/**.
 * to get least distance
 */
protected final int largeNumber = 10000;
/**.
 *resizing recangle
 *@param x coordinate
 *@param y coordinate
 *@return triangle
 */
public Triangle resize(final int x, final int y) {

	Triangle triangle = new Triangle();
  double leastDistance = largeNumber;
	  int point = -1;
			  for (int i = 0; i < threePoints; i++) {
				  if (i == 0) {
 double distance = Math.sqrt((super.firstPointX - x)
		 * (super.firstPointX - x) + (super.firstPointY - y)
		 * (super.firstPointY - y));
				     if (distance <= leastDistance) {
				    	 leastDistance = distance;
				    	 point = i;
				     }
				     }
				  if (i == 1) {
			  double distance = Math.sqrt((super.secondPointX - x)
		  * (super.secondPointX - x) + (super.secondPointY - y)
  * (super.secondPointY - y));
  				     if (distance <= leastDistance) {
  				    	 leastDistance = distance;
  				    	 point = i;
  				     }
				  }
				  if (i == 2) {
		 double distance = Math.sqrt((super.thirdPointX - x)
		 * (super.thirdPointX - x) + (super.thirdPointY - y)
				 * (super.thirdPointY - y));
  				     if (distance <= leastDistance) {
  				    	 leastDistance = distance;
  				    	 point = i;
}
				  }
			  }

			  if (point == 0) {
	  triangle = new Triangle(x, y, super.secondPointX, super.secondPointY,
  super.thirdPointX, super.thirdPointY, super.c, super.c2, super.stroke);
			  } else if (point == 1) {
 triangle = new Triangle(super.firstPointX, super.firstPointY, x, y,
  super.thirdPointX, super.thirdPointY, super.c, super.c2, super.stroke);
			  } else if (point == 2) {
triangle = new Triangle(super.firstPointX, super.firstPointY,
		super.secondPointX, super.secondPointY,
	  x, y, super.c, super.c2, super.stroke);
			  }
	return triangle;
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
* @return new triangle
*/
public Triangle newShape(final int x1, final int y1, final int x2,
		final int y2, final int extra1, final int extra2,
		final Color color, final Color color2, final Stroke s) {
		Triangle triangle = new Triangle(x1, y1,
				x2, y2, extra1,
				extra2, color, color2, s);

		return triangle;
}
/**.
 * sets stroke
 * @param s stroke
 */
public void setStroke(final Stroke s) {
	super.stroke = s;
}


/**
*
* @param x1 top left x
* @param y1 top left y
* @param x2 length
* @param y2 width
* @param x3 3rd x
* @param y3 3rd y
* @param color outline
* @param color2 filled
* @param s stroke
*/
    public Triangle(final int x1, final int y1, final int x2,
    		final int y2, final int x3, final int y3, final Color color,
    		final Color color2, final Stroke s) {

        X1 = x1;
        X2 = x2;
        X3 = x3;
        Y1 = y1;
        Y2 = y2;
        Y3 = y3;
        x[0] = x1;
        x[1] = x2;
        x[2] = x3;
        y[0] = y1;
        y[1] = y2;
        y[2] = y3;
        super.stroke = s;

        super.firstPointX = x1;
        super.firstPointY = y1;
        super.secondPointX = x2;
        super.secondPointY = y2;
        super.thirdPointX = x3;
        super.thirdPointY = y3;
        super.c = color;
        super.c2 = color2;


   }
    /**.
     * number of vertices
     */
protected final int numberOfVertives = 3;
/**.
 * fill color
 * @param g graphic
 */
public void fill(final Graphics g) {

	g.setColor(super.c2);
	g.fillPolygon(x, y, numberOfVertives);

}
/**.
 * set color
 * @param c color
 */
public void setColor(final Color c) {
	super.c2 = c;

}
/**.
 * draw shape
 * @param g graphic
 */
    public void draw(final Graphics g) {

        g.drawLine(X1, Y1, X2, Y2);
        g.drawLine(X2, Y2, X3, Y3);
        g.drawLine(X1, Y1, X3, Y3);

    }
    /**
     * @return boolean
     * @param g graphic
     * @param x coordinate
     * @param y coordinate
     */
public boolean isShape(final Graphics g, final int x, final int y) {

    double line1 = Math.sqrt((X1 - x) * (X1 - x) + (Y1 - y) * (Y1 - y));
    double line2 = Math.sqrt((X2 - x) * (X2 - x) + (Y2 - y) * (Y2 - y));
    double line3 = Math.sqrt((X3 - x) * (X3 - x) + (Y3 - y) * (Y3 - y));
    double side1 = Math.sqrt((X1 - X2) * (X1 - X2) + (Y1 - Y2) * (Y1 - Y2));
    double side2 = Math.sqrt((X1 - X3) * (X1 - X3) + (Y1 - Y3) * (Y1 - Y3));
    double side3 = Math.sqrt((X3 - X2) * (X3 - X2) + (Y3 - Y2) * (Y3 - Y2));

    if (!(line1 > side1 || line1 > side2 || line1 > side3
    		|| line2 > side1 || line2 > side2 || line2 > side3
    		|| line3 > side1 || line3 > side2 || line3 > side3)) {

		setStroke(dashed);
    	return true;

    }
    return false;
    }

}
