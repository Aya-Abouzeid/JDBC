	package eg.edu.alexu.csd.oop.VectorDrawing;

import java.awt.Graphics;
import java.awt.Stroke;


import java.awt.BasicStroke;
import java.awt.Color;
/**
 *
 * @author aya_a_000
 *
 */
public class Line extends Shape {
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
	 * @return new line
	 */
    public Line newShape(final int x1, final int y1, final int x2,
    		final int y2, final int extra1, final int extra2,
    		final Color color, final Color color2, final Stroke s) {
    		Line line = new Line(x1, y1,
    				x2, y2, extra1,
    				extra2, color, color2, s);

    		return line;
    }
    /**.
	 *move line
	 *@param x coordinate
	 *@param y coordinate
	 *@return line
	 */
    public Line move(final int x, final int y) {

    	 int newX = super.firstPointX + (x - super.firstPointX);
	      int newY = super.firstPointY + (y - super.firstPointY);
	      int length = super.secondPointX + (x - super.firstPointX);
	      int width = super.secondPointY + (y - super.firstPointY);

	  	Line line = new Line(newX, newY, length, width,
	  			0, 0, super.c, super.c2, super.stroke);


    	return line;
    }
/**.
 * for selection
 */
    protected final int epsilon = 12;

    /**.
	 *resizing line
	 *@param x coordinate
	 *@param y coordinate
	 *@return line
	 */
    public Line resize(final int x, final int y) {

    	Line line = new Line();
    	/**
    	 * determine the most left point
    	 */
  	  boolean old = true; //old point is the left one
  	  if (super.firstPointX < super.secondPointX) {

  		  old = true;
  	  } else {

  		  old = false;
  	  }

  	  double k = (super.secondPointY - super.firstPointY);
  	  double l = (super.secondPointX - super.firstPointX);

//  	  double m = k / l;
//  	  int z = (int)(m * x);
  	  if (old && x <= (super.secondPointX + super.firstPointX) / 2) {
//  		  line = new Line(x, ((int)Y*x)+
//  (selected.firstPointY-((int)Y*selected.firstPointX )),
//	                  selected.secondPointX , selected.secondPointY,
//  		  selected.c, selected.c2, selected.stroke);
  		  line = new Line(x, y,
  				  super.secondPointX, super.secondPointY,
  				  0, 0,  super.c, super.c2, super.stroke);

  	  }
  	  if (old && x > (super.secondPointX + super.firstPointX) / 2) {
//  		  line = new Line(selected.firstPointX, selected.firstPointY ,
// x , ((int)Y*x)+(selected.secondPointY-((int)Y*selected.secondPointX )),
//  		  selected.c, selected.c2, selected.stroke);
  		  line = new Line(super.firstPointX, super.firstPointY,
	                  x, y, 0, 0, super.c, super.c2, super.stroke);
 } else if (!old && x <= (super.secondPointX + super.firstPointX) / 2) {
//  		  line = new Line(x, ((int)Y*x)+
// (selected.secondPointY-((int)Y*selected.secondPointX )),
//  selected.firstPointX , selected.firstPointY, selected.c,
//	 selected.c2, selected.stroke);
  		  line = new Line(x, y,
  		 super.firstPointX, super.firstPointY, 0, 0, super.c,
  				  super.c2, super.stroke);
  	  } else if (!old && x > (super.secondPointX + super.firstPointX) / 2) {
//  		  line = new Line(selected.secondPointX, selected.secondPointY ,
//	   x , ((int)Y*x)+(selected.firstPointY-((int)Y*selected.firstPointX ))
//  		  , selected.c, selected.c2, selected.stroke);
//
  		  line = new Line(super.secondPointX, super.secondPointY,
	                  x, y, 0, 0, super.c, super.c2, super.stroke);
  	  }
  	  return line;

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
    */
    public Line(final int x1, final int y1, final int x2,
    		final int y2, final int extra1,
 final int extra2, final Color color, final Color color2, final Stroke s) {

        firstPointX = x1;
        firstPointY = y1;
        secondPointX = x2;
        secondPointY = y2;
        super.firstPointX = x1;
        super.firstPointY = y1;
        super.secondPointX = x2;
        super.secondPointY = y2;
        super.c = color;
        super.c2 = color2;
        super.stroke = s;


    }
    /**.
     * set stroke
     */
public void setStroke(final Stroke s) {

    	super.stroke = s;
    }
/**.
 * constructor
 */
public Line() {

    }
/**.
 * draw line
 * @param g graphic
 */
    public void draw(final Graphics g) {

        g.drawLine(firstPointX, firstPointY, secondPointX, secondPointY);


    }

    /**
     * @return boolean
     * @param g graphic
     * @param x coordinate
     * @param y coordinate
     */

    public boolean isShape(final Graphics g, final int x, final int y) {

    	double numberator = Math.abs((secondPointY - firstPointY) * x
    			- (secondPointX - firstPointX) * y + secondPointX
    			* firstPointY - secondPointY * firstPointX);
    	double denomenator = Math.sqrt((secondPointY - firstPointY)
		* (secondPointY - firstPointY) + (secondPointX - firstPointX)
    			* (secondPointX - firstPointX));
    	double distance = numberator / denomenator;

    	if (distance <= epsilon) {

    		setStroke(dashed);

        	return true;
    	}
    	return false;
    }
}
