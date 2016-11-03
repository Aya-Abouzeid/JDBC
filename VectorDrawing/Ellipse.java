package eg.edu.alexu.csd.oop.VectorDrawing;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Stroke;

public class Ellipse extends Shape {
	final static float dash1[] = {10.0f};
    final static BasicStroke dashed =
            new BasicStroke(2f,
                            BasicStroke.CAP_BUTT,
                            BasicStroke.JOIN_MITER,
                            10.0f, dash1, 0.0f);
public Ellipse(){
    	
    	
    }
	 
protected int firstPointX, firstPointY, secondPointX, secondPointY;

    public Ellipse newShape(int x1, int y1, int x2
    		, int y2, int extra1,int extra2,Color color, Color color2, Stroke s) {
    		Ellipse ellipse = new Ellipse(x1,y1,
    				x2,y2,extra1,
    				extra2,color,color2,s);
    		
    		
    		return ellipse;
    }
    public Ellipse move(int x , int y){
    	
    	int newX = super.firstPointX + (x - super.firstPointX);
        int newY = super.firstPointY + (y - super.firstPointY);
        int length = super.secondPointX;
        int width = super.secondPointY;

        		Ellipse ellipse = new Ellipse(newX, newY, length, width, 0,0,super.c, super.c2,
        				super.stroke);

    	return ellipse;
    }
    /**.
	 *resizing ellipse
	 *@param x coordinate
	 *@param y coordinate
	 *@return ellipse
	 */
    public Ellipse resize(int x, int y){

  	  Ellipse ellipse = new Ellipse();

	  if (x >= super.firstPointX+0.5*super.secondPointX && y <=super.firstPointY+0.5*super.secondPointY){
		   ellipse = new Ellipse(super.firstPointX ,y,
                  x-super.firstPointX , super.secondPointY + (super.firstPointY - y),
                  0,0,super.c, super.c2, super.stroke);
 } 
//	  
	  else if (x <= super.firstPointX+0.5*super.secondPointX && y <=super.firstPointY+0.5*super.secondPointY){
		  ellipse = new Ellipse(x ,y,
	                  super.secondPointX + (super.firstPointX -x),
	                  super.secondPointY + (super.firstPointY-y) ,0,0, super.c, super.c2, super.stroke);
	         	  }
	  else if (y >= super.firstPointY+0.5*super.secondPointY && x <=super.firstPointX+0.5*super.secondPointX){
		  ellipse = new Ellipse(x ,super.firstPointY, super.secondPointX + (super.firstPointX - x),
	                  y - super.firstPointY ,0,0, super.c, super.c2, super.stroke);
	     	  }
	  else if (x >= super.firstPointX+0.5*super.secondPointX && y >= super.firstPointY+0.5*super.secondPointY){
		  ellipse = new Ellipse(super.firstPointX ,super.firstPointY,
	                  (x - super.firstPointX), (y - super.firstPointY) ,0,0, super.c, super.c2, super.stroke);
	         	  }
	  return ellipse;
    	
    }
    public Ellipse(int x1, int y1, int x2, int y2,int extra1, int extra2, Color color, Color color2, Stroke s){
 
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
public void setStroke(Stroke s){
    	
    	super.stroke = s;
    }


public void fill(Graphics g){
	g.setColor(super.c2);

	g.fillOval(firstPointX, firstPointY, secondPointX, secondPointY);
}
public void setColor(Color c){
	super.c2 = c;

}
    public void draw(Graphics g){
 
    	g.drawOval(firstPointX, firstPointY, secondPointX , secondPointY );
       
        centerX = firstPointX + (secondPointX/2);
        centerY = firstPointY + (secondPointY/2);
 
    }
    int centerX;
    int centerY;

    /**
     * @return boolean
     * @param g graphic
     * @param x coordinate
     * @param y coordinate
     */
public boolean isShape(Graphics g, int x, int y){
    	
	int centerX = firstPointX + (secondPointX/2);
	int centerY = firstPointY + (secondPointY/2);
	int a = ((x - centerX)*(x - centerX))/((secondPointX/2)*(secondPointX/2));
	int b = ((y - centerY)*(y - centerY))/((secondPointY/2)*(secondPointY/2));

    	if( a+ b < 1){

//    		if(x>=(centerX - secondPointX/2) && x<=(centerX + secondPointX/2) &&
//        			y>=(centerY - secondPointY/2)&& y<=(centerY + secondPointY/2)){
//        		
    		setStroke(dashed);
        	return true;

    	}
    	return false;
    }

}

