package eg.edu.alexu.csd.oop.VectorDrawing;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Stroke;

/**
 *
 * @author aya_a_000
 *
 */
public class Rectangle extends Shape {

	/**.
	 * half length or width
	 */
		private final double halfDistance = 0.5;
/**.
 * dashes
 */
	final static float dash1[] = {10.0f};
	/**.
	 * dashes
	 */
    static final BasicStroke Dashed =
            new BasicStroke(2f,
                            BasicStroke.CAP_BUTT,
                            BasicStroke.JOIN_MITER,
                            10.0f, dash1, 0.0f);
    /**.
     * constructor
     */
	public Rectangle() {

    }
/**.
 * indices
 */
	protected int firstPointX, firstPointY, secondPointX, secondPointY;
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
	 *
	 */
    protected Rectangle(final int x1, final int y1, final int x2, final int y2,
    		final int extra1, final int extra2,
    		final Color color, final Color color2, final Stroke s) {

        firstPointX = x1;
        firstPointY = y1;
        secondPointX = x2;
        secondPointY = y2;
        super.firstPointX = x1;
        super.firstPointY = y1;
        super.secondPointX = x2;
        super.secondPointY = y2;
        c = color;
        super.c = color;
        super.c2 = color2;
        super.stroke = s;

    }
/**.
 * @param g graphics
 * fill
 */
    public void fill(final Graphics g) {
    	g.setColor(super.c2);

    	g.fillRect(firstPointX, firstPointY, secondPointX, secondPointY);
    }
    /**.
     * @param c color
     * setcolor
     */
    public void setColor(final Color c) {
    	super.c2 = c;

    }
    /**.
     * draw
     * @param g graphic
     */
    public void draw(final Graphics g) {


    	g.drawRect(firstPointX, firstPointY, secondPointX, secondPointY);


    }


/**
 * @return boolean
 * @param g graphic
 * @param x coordinate
 * @param y coordinate
 */
    public boolean isShape(final Graphics g, final int x, final int y) {


    	if (x >= firstPointX && x <= (firstPointX + secondPointX)
    	&& y >= firstPointY && y <= (firstPointY + secondPointY)) {

    		setStroke(dashed);

        	return true;
    	}
    	return false;
    }
    /**.
	 *resizing rectangle
	 *@param x coordinate
	 *@param y coordinate
	 *@return rectangle
	 */
    public Rectangle resize(final int x, final int y) {

  	  Rectangle rectangle = new Rectangle();

    	 if (x >= super.firstPointX + halfDistance * super.secondPointX
    	&& y <= super.firstPointY + halfDistance * super.secondPointY) {
  		   rectangle = new Rectangle(super.firstPointX, y,
	 x - super.firstPointX, super.secondPointY + (super.firstPointY - y),
	 0, 0, super.c, super.c2, super.stroke);
	  } else if (x <= super.firstPointX + halfDistance * super.secondPointX
  && y <= super.firstPointY + halfDistance * super.secondPointY) {
  		   rectangle = new Rectangle(x, y,
  				   super.secondPointX + (super.firstPointX -x), super.secondPointY + (super.firstPointY-y) ,
  				   0,0,super.c, super.c2, super.stroke);
		         	  }
  	  else if (y >= super.firstPointY+ halfDistance *super.secondPointY && x <=super.firstPointX+ halfDistance *super.secondPointX){
  		   rectangle = new Rectangle(x ,super.firstPointY, super.secondPointX + (super.firstPointX - x),
		                  y - super.firstPointY ,0,0, super.c, super.c2, super.stroke);
		     	  }
  	  else if (x >= super.firstPointX+ halfDistance *super.secondPointX && y >= super.firstPointY+ halfDistance *super.secondPointY){
  		   rectangle = new Rectangle(super.firstPointX ,super.firstPointY,
		                  (x - super.firstPointX), (y - super.firstPointY) ,0,0, super.c, super.c2, super.stroke);
		         	  }

    	return rectangle;
    }
    /**.
     * sets stroke
     * @param s stroke
     */
    public void setStroke(final Stroke s) {

    	super.stroke = s;
    }
	/**.
	 *move rectangle
	 *@param x coordinate
	 *@param y coordinate
	 *@return rectangle
	 */
    public Rectangle move(final int x, final int y) {

    	int newX = super.firstPointX + (x - super.firstPointX);
        int newY = super.firstPointY + (y - super.firstPointY);
        int length = super.secondPointX;
        int width = super.secondPointY;

        Rectangle rectangle = new Rectangle(newX, newY, length, width,
        		0, 0, super.c, super.c2,
        		super.stroke);

    	return rectangle;
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
	 * @return new rectangle
	 */
public Rectangle newShape(final int x1, final int y1, final int x2,
		final int y2, final int extra1, final int extra2,
		final Color color, final Color color2, final Stroke s) {
		Rectangle rectangle = new Rectangle(x1, y1,
				x2, y2, extra1,
				extra2, color, color2, s);

		return rectangle;
}

}
