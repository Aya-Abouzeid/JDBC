package eg.edu.alexu.csd.oop.VectorDrawing;



import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.event.MouseAdapter;
 
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.net.URLClassLoader;
import java.util.ArrayList;
 
import javax.swing.*;
 
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import com.thoughtworks.xstream.io.xml.DomDriver;
 
import java.awt.color.*;
public class Sketch extends JComponent {
	/**
	   *
	   */
	   protected int counter;
	   /**
	   *
	   */
       private boolean mouseClicked = false;
       /**
	   *
	   */
          private Image image;
          /**
   	   *
   	   */
          protected Graphics2D graphic;
          /**
   	   *
   	   */
          int firstPointX, firstPointY, secondPointX,
          secondPointY, PageIndex = -1, topLeftX,
                  topLeftY, selectX, selectY;
          /**
   	   *
   	   */
          private int FirstX, FirstY, SecondX,
          SecondY, ThirdX, ThirdY;
          /**
   	   *
   	   */
          ArrayList<Shape> ShapeAdded = new ArrayList<Shape>();
          /**
   	   *
   	   */
          ArrayList<ArrayList> pageLookLike = new ArrayList<ArrayList>();
          /**
   	   *
   	   */
    protected  boolean lineClicked = false, freeClicked = true, squareClicked = false,
    rectangleClicked = false, triangleClicked = false, circleClicked = false,
          resizeClicked = false, deleteClicked = false,
          moveClicked = false, fillClicked = false, shapeSelected = false,
 
              ellipseClicked = false, triangleClicked1 = false,
              triangleClicked2 = false, done = false, firstPoint = false,
              secondPoint = false, threePoint = false, clearSelected = false,
              selectClicked = false, inShape = false, undoClicked = false,
              redoClicked;
    /**
	   *
	   */
     LoaderOfClasses clas = new LoaderOfClasses();
     /**
	   *
	   */
        final static float dash1[] = {10.0f};
        /**
 	   *
 	   */
        final static BasicStroke dashed =
                new BasicStroke(2f,
                                BasicStroke.CAP_BUTT,
                                BasicStroke.JOIN_MITER,
                                10.0f, dash1, 0.0f);
        final static BasicStroke defaultStroke = new BasicStroke(2.5f);
          Color filled = new Color(255,255,255,0);
          Color outline = Color.black;
          boolean undoAlreadyClicked = false;
 
          public Sketch() {
            setDoubleBuffered(false);
 
            addMouseListener(new MouseAdapter() {
 
                 public void mouseClicked(final MouseEvent e) {
                        selectX = e.getX();
                        selectY = e.getY();
     
                        if (selectClicked && !moveClicked && !resizeClicked && !fillClicked) {
//                          moveClicked = false;
                            isShapeSelected(selectX, selectY);
     
                       if (!shapeSelected)
                                selectClicked = false;
     
                        }
     
                        if (moveClicked) {
                            if(!shapeSelected){
                                booleanSetter(false, false, false, false, false, false, false, false, false);
                                selectClicked = false;}
                            else
                            moveSelected(selectX, selectY);
     
                        }
                        if (resizeClicked) {
                            if(!shapeSelected){
                                booleanSetter(false, false, false, false, false, false, false, false, false);
                             selectClicked = false;  
                            }
                            else
                            resizeSelected(selectX, selectY);
                        }
     
                    }
 
              @Override
              public void mousePressed(final MouseEvent e) {
                firstPointX = e.getX();
                firstPointY = e.getY();
                mouseClicked = true;
 
                if (triangleClicked && !done && !secondPoint && !threePoint
                    && !firstPoint) {
                  FirstX = e.getX();
                  FirstY = e.getY();
                  firstPoint = true;
                  // triangleClicked2 = true ;
                }
         if (triangleClicked2 && firstPoint && secondPoint && triangleClicked) {
                  firstPoint = false;
                  done = false;
                  secondPoint = false;
                  triangleClicked2 = false;
                  ThirdX = e.getX();
                  ThirdY = e.getY();
 
    Triangle triangle = new Triangle(FirstX, FirstY, SecondX, SecondY, ThirdX, ThirdY, outline, filled, defaultStroke);
                  ShapeAdded.add(triangle);
                  pageLookLike.add(new ArrayList<Shape>(ShapeAdded));
                  PageIndex++;
                  graphic.drawLine(SecondX, SecondY, ThirdX, ThirdY);
                  repaint();
                  graphic.drawLine(FirstX, FirstY, ThirdX, ThirdY);
                  repaint();
                }
              }
     
              public void mouseReleased(final MouseEvent e) {
                // TODO Auto-generated method stub
                  freeClicked = false;
                secondPointX = e.getX();
                secondPointY = e.getY();
                mouseClicked = false;
                if (lineClicked) {
                  Line line = new Line(firstPointX, firstPointY, secondPointX, secondPointY,0,0, outline, filled, defaultStroke);
                  line.draw(graphic);
                  ShapeAdded.add(line);
                  pageLookLike.add(new ArrayList<Shape>(ShapeAdded));
                  PageIndex++;
                  repaint();
                }
     
                if (squareClicked) {
     
                  if (firstPointX <= secondPointX) {
                    topLeftX = firstPointX;
                  } else {
                    topLeftX = secondPointX;
                  }
                  if (firstPointY <= secondPointY) {
                    topLeftY = firstPointY;
                  } else {
                    topLeftY = secondPointY;
                  }
                 
     
                  Shape square = clas.getObject(topLeftX, topLeftY,
                      Math.abs(secondPointX - firstPointX), Math.abs(secondPointX - firstPointX),0,0, outline, filled, defaultStroke);
                  square.draw(graphic);
                  ShapeAdded.add(square);
                  pageLookLike.add(new ArrayList<Shape>(ShapeAdded));
                  PageIndex++;
     
                  repaint();
     
                }
                if (rectangleClicked) {
     
                  if (firstPointX <= secondPointX) {
                    topLeftX = firstPointX;
                  } else {
                    topLeftX = secondPointX;
                  }
                  if (firstPointY <= secondPointY) {
                    topLeftY = firstPointY;
                  } else {
                    topLeftY = secondPointY;
                  }
     
                  Rectangle rectangle = new Rectangle(topLeftX, topLeftY,
                      Math.abs(secondPointX - firstPointX), Math.abs(secondPointY - firstPointY),0,0, outline, filled, defaultStroke);
                  rectangle.draw(graphic);
                  ShapeAdded.add(rectangle);
                  pageLookLike.add(new ArrayList<Shape>(ShapeAdded));
                  PageIndex++;
     
                  repaint();
                }
                if (ellipseClicked) {
                  if (firstPointX <= secondPointX) {
                    topLeftX = firstPointX;
                  } else {
                    topLeftX = secondPointX;
                  }
                  if (firstPointY <= secondPointY) {
                    topLeftY = firstPointY;
                  } else {
                    topLeftY = secondPointY;
                  }
                  Ellipse oval = new Ellipse(topLeftX, topLeftY, Math.abs(secondPointX
                      - firstPointX), Math.abs(secondPointY - firstPointY),0,0, outline, filled, defaultStroke);
                  oval.draw(graphic);
                  ShapeAdded.add(oval);
                  pageLookLike.add(new ArrayList<Shape>(ShapeAdded));
                  PageIndex++;
     
                  repaint();
     
                }
                if (circleClicked) {
     
                  if (firstPointX <= secondPointX) {
                    topLeftX = firstPointX;
                  } else {
                    topLeftX = secondPointX;
                  }
                  if (firstPointY <= secondPointY) {
                    topLeftY = firstPointY;
                  } else {
                    topLeftY = secondPointY;
                  }
                  Circle circle = new Circle(topLeftX, topLeftY, Math.abs(secondPointX
                      - firstPointX), Math.abs(secondPointX - firstPointX),0,0, outline, filled, defaultStroke);
                  circle.draw(graphic);
                  ShapeAdded.add(circle);
                  pageLookLike.add(new ArrayList<Shape>(ShapeAdded));
                  PageIndex++;
                  repaint();
     
                }
                if (triangleClicked) {
     
                  if (!done && !secondPoint && !threePoint && firstPoint) {
                    SecondX = e.getX();
                    SecondY = e.getY();
                    if (!((SecondX == FirstX) && (SecondY == FirstY))) {
                      secondPoint = true;
                      triangleClicked2 = true;
                      done = true;
                    }
                    graphic.drawLine(FirstX, FirstY, SecondX, SecondY);
                  }
                  repaint();
     
                }
              }
            });
     
            addMouseMotionListener(new MouseMotionAdapter() {
     
                  @Override
                  public void mouseDragged(final MouseEvent e) {
                    secondPointX = e.getX();
                    secondPointY = e.getY();
                    if(freeClicked){
                       
                       
                       
                    }
                    if (graphic != null && freeClicked && !lineClicked) {
                      graphic.drawLine(firstPointX, firstPointY, secondPointX, secondPointY);
                      repaint();
                      firstPointX = secondPointX;
                      firstPointY = secondPointY;
                    }
                    repaint();
                  }
     
                });
          }
            int shapeIndex = 0;
              ArrayList<Shape> selectedShapes = new ArrayList<Shape>();
            ArrayList<Point> selectedIndices = new ArrayList<Point>();
            Shape selected = new Shape();
 
          public void isShapeSelected(int x, int y){
               
             
         for (int i = (pageLookLike.get(PageIndex).size() - 1); i >= 0; i--) {
    if (((Shape) pageLookLike.get(PageIndex).get(i)).isShape(graphic, x, y)) {
          shapeIndex = i;
            selected = (Shape) pageLookLike.get(PageIndex).get(i);
            Point z = new Point(counter, shapeIndex);
            selectedIndices.add(z);
            selectedShapes.add(selected);
                    selected.setStroke(dashed);
                    counter++;
          shapeSelected = true;
         
//          break;
    }
            repaint();
                }
          }
          public void emptySelected(){
             
              selectedShapes.clear();
              selectedIndices.clear();
              sortedSelectedIndices.clear();
          }
          public void setMove(boolean move){
            moveClicked = move;
          }
 
 
          public void resizeSelected (int x , int y) {
if(resizeClicked && shapeSelected){
      if (undoAlreadyClicked) {
          for (int i = pageLookLike.size() - 1; i > PageIndex; i--) {
              pageLookLike.remove(i);
 
          }
          undoAlreadyClicked = false;
      }
              repaint();
               for(int i = 0 ; i <counter ; i++){
 
                 
                  ShapeAdded.remove(selectedIndices.get(i).y);
 
                  Shape selected=  selectedShapes.get(i).newShape(selectedShapes.get(i).firstPointX, selectedShapes.get(i).firstPointY, selectedShapes.get(i).secondPointX,
                            selectedShapes.get(i).secondPointY,selectedShapes.get(i).thirdPointX,selectedShapes.get(i).thirdPointY, selectedShapes.get(i).c, selectedShapes.get(i).c2, selectedShapes.get(i).stroke);
                 
                  selected = selected.resize(x, y);
                  selected.draw(graphic);
    repaint();
    ShapeAdded.add(selectedIndices.get(i).y , selected);
 
 
                     selectedShapes.remove(i);
 
                     selectedShapes.add(i, selected);
                  repaint();
                 
                 
              }
               
               selectClicked = false;
             
             
                 
                      pageLookLike.add(new ArrayList<Shape>(ShapeAdded));
                      PageIndex++;
         
                      repaint();
   
             
              if (image == null) {
                image = createImage(getSize().width, getSize().height);
                graphic = (Graphics2D) image.getGraphics();
                graphic.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
              }
}
 
     
          }
 
          public void moveSelected(int x , int y){
             if(shapeSelected){
                  if (undoAlreadyClicked) {
                        for (int i = pageLookLike.size() - 1; i > PageIndex; i--) {
                            pageLookLike.remove(i);
         
                        }
                        undoAlreadyClicked = false;
                    }
               for(int i = 0 ; i <counter ; i++){
                  ShapeAdded.remove(selectedIndices.get(i).y);
                  repaint();
        clear();
                  Shape selected=  selectedShapes.get(i).newShape(selectedShapes.get(i).firstPointX, selectedShapes.get(i).firstPointY, selectedShapes.get(i).secondPointX,
                            selectedShapes.get(i).secondPointY,selectedShapes.get(i).thirdPointX,selectedShapes.get(i).thirdPointY, selectedShapes.get(i).c, selectedShapes.get(i).c2, selectedShapes.get(i).stroke);
                 
                 selected = selected.move(x,y);
                 selected.draw(graphic);
                  ShapeAdded.add(selectedIndices.get(i).y, selected);
 
                      repaint();
                     selectedShapes.remove(i);
 
                     selectedShapes.add(i, selected);
 
           
               }
             }
             selectClicked = false;
             
             pageLookLike.add(new ArrayList<Shape>(ShapeAdded));
             PageIndex++;
 
 
             if (image == null) {
               image = createImage(getSize().width, getSize().height);
               graphic = (Graphics2D) image.getGraphics();
               graphic.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                   RenderingHints.VALUE_ANTIALIAS_ON);
             }
             repaint();
             
          }
         
         
          protected void paintComponent(final Graphics e) {
                if (image == null) {
                  image = createImage(getSize().width, getSize().height);
                  graphic = (Graphics2D) image.getGraphics();
                  graphic.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                      RenderingHints.VALUE_ANTIALIAS_ON);
                  clear();
     
                }
                clear();
 
                if(PageIndex >= 0)
                    for (int i = 0; i < pageLookLike.get(PageIndex).size(); i++) {
                   
                          ((Shape) pageLookLike.get(PageIndex).get(i)).fill(graphic);
                          if(shapeSelected){
 
                           graphic.setStroke(((Shape) pageLookLike.get(PageIndex).get(i)).stroke);
                    }
                          else {
                              selected.setStroke(defaultStroke);
                      Shape x = (Shape) pageLookLike.get(PageIndex).get(i);
                      x.setStroke(defaultStroke);
                              graphic.setStroke(defaultStroke); //byraga3o tany default law e5trt ay shape
                          }
                         
                        graphic.setPaint(((Shape) pageLookLike.get(PageIndex).get(i)).c);
                      ((Shape) pageLookLike.get(PageIndex).get(i)).draw(graphic);
 
                    }
                if(mouseClicked && triangleClicked){
                    if (undoAlreadyClicked) {
                        for (int i = pageLookLike.size() - 1; i > PageIndex; i--) {
                            pageLookLike.remove(i);
         
                        }
                        undoAlreadyClicked = false;
                    }
                }
//              if(fillClicked && undoClicked){
//             
//                      for (int i = pageLookLike.size() - 1; i > PageIndex; i--) {
//                          pageLookLike.remove(i);
//       
//                      }
//                      undoAlreadyClicked = false;
//                 
//              }
               
                if (mouseClicked && lineClicked) {
                new Line(firstPointX, firstPointY, secondPointX, secondPointY,0,0, outline, filled, defaultStroke).draw(graphic);
               
                if (undoAlreadyClicked) {
                    for (int i = pageLookLike.size() - 1; i > PageIndex; i--) {
                        pageLookLike.remove(i);
     
                    }
                    undoAlreadyClicked = false;
                }
              }
                if (mouseClicked && rectangleClicked) {
              new Rectangle(firstPointX, firstPointY, secondPointX - firstPointX, secondPointY - firstPointY,0,0, outline, filled, defaultStroke)
                  .draw(graphic);
              if (undoAlreadyClicked) {
                    for (int i = pageLookLike.size() - 1; i > PageIndex; i--) {
                        pageLookLike.remove(i);
     
                    }
                    undoAlreadyClicked = false;
                }
            }
                if (mouseClicked && squareClicked) {
                     clas.getObject(firstPointX, firstPointY, secondPointX - firstPointX, secondPointY - firstPointY,0,0, outline, filled, defaultStroke)
                          .draw(graphic);
                      if (undoAlreadyClicked) {
                          for (int i = pageLookLike.size() - 1; i > PageIndex; i--) {
                              pageLookLike.remove(i);
           
                          }
                          undoAlreadyClicked = false;
                      }
                     
                    }
                if (mouseClicked && ellipseClicked) {
                new Ellipse(firstPointX, firstPointY, secondPointX - firstPointX, secondPointY - firstPointY,0,0, outline, filled, defaultStroke)
                    .draw(graphic);
                if (undoAlreadyClicked) {
                    for (int i = pageLookLike.size() - 1; i > PageIndex; i--) {
                        pageLookLike.remove(i);
     
                    }
                    undoAlreadyClicked = false;
                }
              }
                if (mouseClicked && circleClicked) {
                new Circle(firstPointX, firstPointY, secondPointX - firstPointX, secondPointX - firstPointX,0,0, outline, filled, defaultStroke)
                    .draw(graphic);
                if (undoAlreadyClicked) {
                    for (int i = pageLookLike.size() - 1; i > PageIndex; i--) {
                        pageLookLike.remove(i);
     
                    }
                    undoAlreadyClicked = false;
                }
              }
                e.drawImage(image, 0, 0, null);
              }
          public void clear() {
            if (clearSelected) {
              ShapeAdded = new ArrayList<Shape>();
              pageLookLike.add(new ArrayList<Shape>(ShapeAdded));
              PageIndex++;
              clearSelected = false;
            }
 
            graphic.setPaint(Color.white);
            graphic.fillRect(0, 0, getSize().width, getSize().height);
            graphic.setPaint(outline);
         repaint();
 
          }
 
public void addNewShape(Shape shape){
    ShapeAdded.remove(selectedIndices.get(variableForIndex).y);
 
     ShapeAdded.add(selectedIndices.get(variableForIndex).y, shape);
     selectedShapes.remove(variableForIndex);
 
     selectedShapes.add(variableForIndex, shape);
     repaint();
}
 
    int variableForIndex;
          public void fillColor(){
              if (shapeSelected && fillClicked) {
                 
                  if (undoAlreadyClicked) {
                        for (int i = pageLookLike.size() - 1; i > PageIndex; i--) {
                            pageLookLike.remove(i);
         
                        }
                        undoAlreadyClicked = false;
                    }

                    JColorChooser colors = new JColorChooser();
           filled = colors.showDialog(null, "SELECT A COLOR", Color.red);
           if(filled == null){
               filled = new Color(255,255,255,0);

           }

           fillClicked = false;
           selectClicked = false;
           for(int i = 0 ; i <counter ; i++){

               variableForIndex = i;

             Shape selected=  selectedShapes.get(i).newShape(selectedShapes.get(i).firstPointX, selectedShapes.get(i).firstPointY, selectedShapes.get(i).secondPointX,
                        selectedShapes.get(i).secondPointY,selectedShapes.get(i).thirdPointX,selectedShapes.get(i).thirdPointY, selectedShapes.get(i).c, filled, selectedShapes.get(i).stroke);
             selected.fill(graphic);
             selected.draw(graphic);
             addNewShape(selected);
}
           }

               pageLookLike.add(new ArrayList<Shape>(ShapeAdded));
                 PageIndex++;
 
                 repaint();
                repaint();
           
                  if (image == null) {
                    image = createImage(getSize().width, getSize().height);
                    graphic = (Graphics2D) image.getGraphics();
                    graphic.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
                  }
                 filled = new Color(255,255,255,0);
 
              }
         
 
          int debug = selectedShapes.size();
          public void undo() {
              if ((PageIndex >= 0)) {
                  PageIndex--;
                  undoAlreadyClicked = true;
                  ShapeAdded = new ArrayList<Shape>();
                  for (int i = 0; PageIndex >= 0 && i < (pageLookLike.get(PageIndex)).size(); i++) {
                      ShapeAdded.add(((Shape) pageLookLike.get(PageIndex).get(i)));
       
                  }
                  if (image == null) {
                      image = createImage(getSize().width, getSize().height);
                      graphic = (Graphics2D) image.getGraphics();
                      graphic.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                  }
                  repaint();
              }
       
          }
       
          public void redo() {
       
              if ((pageLookLike.size() > PageIndex + 1)) {
                  PageIndex++;
                  ShapeAdded = new ArrayList<Shape>();
                  for (int i = 0; i < (pageLookLike.get(PageIndex)).size(); i++) {
                      ((Shape) pageLookLike.get(PageIndex).get(i)).draw(graphic);
       
                  }
                  for (int i = 0; i < (pageLookLike.get(PageIndex)).size(); i++) {
                      ShapeAdded.add(((Shape) pageLookLike.get(PageIndex).get(i)));
       
                  }
       
                  if (image == null) {
                      image = createImage(getSize().width, getSize().height);
                      graphic = (Graphics2D) image.getGraphics();
                      graphic.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                  }
                  repaint();
       
              }
       
          }
         
            ArrayList<Point> sortedSelectedIndices = new ArrayList<Point>();
 
          public void sortSelected(){
              for(int i =0 ; i<counter ; i++){
//                boolean maximum = true;
                  int max =i;
                  for(int j = i+1 ; j<counter -1 ;j++){
                     
                      if(selectedIndices.get(j).y > selectedIndices.get(max).y)
                          max = j;
                  }
 
                  sortedSelectedIndices.add(selectedIndices.get(max));
                  Point index = selectedIndices.get(i);
                  Point index2 = selectedIndices.get(max);
                  selectedIndices.remove(i);
                  selectedIndices.add(i, index2);
                  selectedIndices.remove(max);
                  selectedIndices.add(max, index);
//                    Point temp = selectedIndices.get(max);
//                    Pont temp
//                    selectedIndices.remove(i);
//                    selectedIndices.add(i, temp);
//                    selectedIndices.remove(index)
//                   
                  }
                 
                 
              }
             
             
         
          public void deleteSelected(){
              sortSelected();
              if(shapeSelected){
                  if (undoAlreadyClicked) {
                        for (int i = pageLookLike.size() - 1; i > PageIndex; i--) {
                            pageLookLike.remove(i);
         
                        }
                        undoAlreadyClicked = false;
                    }
                  for(int i =0 ; i<counter ; i++){
                     
                      selectedShapes.get(i).setStroke(defaultStroke);
                      ShapeAdded.remove(sortedSelectedIndices.get(i).y);
                   
                     
                  }
                 
              repaint();
             
             
    clear();
   
    selectClicked =false;
    pageLookLike.add(new ArrayList<Shape>(ShapeAdded));
    PageIndex++;
   
   
                  if (image == null) {
                    image = createImage(getSize().width, getSize().height);
                    graphic = (Graphics2D) image.getGraphics();
                    graphic.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
                  }
                  repaint();
                  }
              deleteClicked = false;
              shapeSelected = false;
              selectClicked = false;
 
          }
          public static void saveXml(ArrayList<Shape> shapes, String filepath) throws Exception {
              XStream xStream = new XStream(new DomDriver());
              String xmlData = xStream.toXML(shapes);
              PrintWriter writer = new PrintWriter(filepath, "UTF-8");
              writer.print(xmlData);
              writer.close();
          }

         
          public  ArrayList loadXml(  String filepath) throws Exception {
             
              XStream xStream = new XStream(new DomDriver());
 
              ArrayList<Shape> Shapes;
              File xmlFile = new File(filepath);
             
              try{
              Shapes = new ArrayList<Shape>();
              Shapes = (ArrayList<Shape>) xStream.fromXML(xmlFile);
             
          }
          catch (Exception e1) {
              if (clas.urls == null){
                JOptionPane.showMessageDialog(null, "this may contain a square");
              return ShapeAdded;
 
            }
            else {
              ClassLoader myClassLoader = URLClassLoader.newInstance(clas.urls);
              xStream.setClassLoader(myClassLoader);
 
              Shapes = new ArrayList<Shape>();
 
              Shapes = (ArrayList<Shape>) xStream.fromXML(xmlFile);
              return Shapes;
 
            }
 
          }
              return Shapes;
          }      
//        public  ArrayList loadXml(  String filepath) throws Exception {
//            XStream xStream = new XStream(new DomDriver());
//           
//              if (clas.urls == null){
//                  JOptionPane.showMessageDialog(null, "Please load the class first");
//                return ShapeAdded;
//
//              }
//              else{
//            ClassLoader myClassLoader = URLClassLoader.newInstance(clas.urls);
//            xStream.setClassLoader(myClassLoader);}
//
//            ArrayList<Shape> Shapes;
//            File xmlFile = new File(filepath);
//            Shapes = (ArrayList<Shape>) xStream.fromXML(xmlFile);
//           
//            return Shapes;
//        }
       
          public void load(int index, String filepath) throws Exception {
              PageIndex = -1;
              ShapeAdded = new ArrayList<Shape>();
              if(index == 0)
                  ShapeAdded = loadXml(filepath);
              else
                  ShapeAdded = loadJson( filepath);
             
              pageLookLike = new ArrayList<ArrayList>();
              pageLookLike.add(ShapeAdded);
              PageIndex++;
       
              for (int i = 0; i < (pageLookLike.get(PageIndex)).size(); i++) {
                  ((Shape) pageLookLike.get(PageIndex).get(i)).draw(graphic);
              }
              if (image == null) {
                  image = createImage(getSize().width, getSize().height);
                  graphic = (Graphics2D) image.getGraphics();
                  graphic.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
              }
              repaint();
       
          }
         
//        public ArrayList<Shape> loadJson(String filepath) throws Exception {
//          XStream xstream = new XStream(new JettisonMappedXmlDriver());
//          ArrayList<ArrayList<Shape>> Shapes;
//          File xmlFile = new File(filepath);
//          try{
//          Shapes = new ArrayList<ArrayList<Shape>>();
//          Shapes = (ArrayList<ArrayList<Shape>>) xstream.fromXML(xmlFile);
//        
//      }
//      catch (Exception e1) {
//        if (clas.urls == null){
//            JOptionPane.showMessageDialog(null, "this may contain a square");
//          return ShapeAdded;
//
//        }
//        else {
//          ClassLoader myClassLoader = URLClassLoader.newInstance(clas.urls);
//          xstream.setClassLoader(myClassLoader);
//
//          Shapes = new ArrayList<ArrayList<Shape>>();
//
//          Shapes = (ArrayList<ArrayList<Shape>>) xstream.fromXML(new File(filepath));
//
//          return Shapes.get(0);
//
//        }
//
//      }        
//          return Shapes.get(0);
//        }
       
          public ArrayList<Shape> loadJson(String filepath) throws Exception {
              XStream xstream = new XStream(new JettisonMappedXmlDriver());
              ArrayList<ArrayList<Shape>> Shapes;
              try {
                  Shapes = (ArrayList<ArrayList<Shape>>) xstream.fromXML(new File(filepath) );
 
              }
              catch (Exception e1){
             
              if (clas.urls == null){
                    JOptionPane.showMessageDialog(null, "Please load the class first");
                    return ShapeAdded;
                  }
                  else{
                  ClassLoader myClassLoader = URLClassLoader.newInstance(clas.urls);
                  xstream.setClassLoader(myClassLoader);
              Shapes = (ArrayList<ArrayList<Shape>>) xstream.fromXML(new File(filepath) );
                  }
              return Shapes.get(0);
          }
            return Shapes.get(0);
          }
       
          public void saveJson(ArrayList<Shape> shapes, String filepath) throws Exception {
              XStream xstream = new XStream(new JettisonMappedXmlDriver());
              xstream.setMode(XStream.NO_REFERENCES);
              PrintWriter wr = new PrintWriter(filepath);
           
              xstream.toXML(shapes, wr);
             
              wr.close();
          }
           
          public void booleanSetter (boolean free, boolean line, boolean triangle,
                     boolean square, boolean rectangle, boolean circle,
                     boolean ellipse,
                      boolean undo, boolean redo) {
                 freeClicked = free;
                 lineClicked = line;
                 triangleClicked = triangle;
                 squareClicked = square;
                 rectangleClicked = rectangle;
                 circleClicked = circle;
                 ellipseClicked = ellipse;
             
                 undoClicked = undo;
                 redoClicked = redo;
     
             } public void booleanSetter2 (boolean move, boolean fill, boolean deleted, boolean resize){
                 
                 moveClicked = move;
                 fillClicked = fill;
                 deleteClicked= deleted;
                 resizeClicked = resize;
             }
             /**
             *
              * @param s
              */
          public void setmoveClicked(boolean s){
                  moveClicked = s;
              } /**
          *
          * @param s
          */
          public void setfillClicked(boolean s){
                  fillClicked = s;
              }
              /**
              *
              * @param s
              */
              public void setdeleteClicked(boolean s){
                  deleteClicked = s;
              }
              /**.
              *
               * @param s
               */
              public void setresizeClicked(boolean s){
                  resizeClicked = s;
              }
              /**
              *
              * @param s
              */
          public void setshapeSelected(boolean s){
              shapeSelected = s;
          }
          /**
           *
           * @param s
           */
          public void setselectSelected(boolean s){
              selectClicked = s;
          } /**
          *
          * @param s
          */
          public void setCounter(int s){
              counter = s;
          }
          public void changecolor() {
              JColorChooser colors = new JColorChooser();
               outline = colors.showDialog(null, "SELECT A COLOR", Color.red);

            graphic.setPaint(outline);
            repaint();
          }
     
        }