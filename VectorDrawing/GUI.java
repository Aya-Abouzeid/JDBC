package eg.edu.alexu.csd.oop.VectorDrawing;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.*;
import java.awt.ActiveEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.nio.channels.SelectableChannel;

import javax.swing.*;
import javax.imageio.ImageIO;
import javax.sound.sampled.Line;
import javax.swing.filechooser.FileNameExtensionFilter;

public class GUI extends JFrame implements MouseListener, MouseMotionListener {



	 /**
	   *
	   */
	 private static final long serialVersionUID = 1L;
	 JButton clearBtn,lineBtn, save, load;
	static JButton square;
	JButton undo;
	JButton redo;
	JButton select;
	JButton move;
	JButton resize;
	JButton fill;
	JButton delete;
	JButton rectangle;
	JButton triangle;
	JButton ellipse;
	JButton circle;
	JButton colors;
	 JFrame paint = new JFrame("Paint");
	 JPanel controls = new JPanel();
	 JPanel controls2 = new JPanel();
	 JFileChooser fileChooser = new JFileChooser();

	 
	 private int OldX, OldY, CurrentX, CurrentY;

	 MouseEvent mouse;
	 Sketch sketch = new Sketch();
	 Container content = paint.getContentPane();
	 ActionListener action = new ActionListener() {

	   public void actionPerformed(ActionEvent e) {
	     // TODO Auto-generated method stub

		   if (e.getSource() == clearBtn) {
		          sketch.booleanSetter2(false, false, false, false);
		 sketch.emptySelected();
		         sketch.clear();
		         sketch.clearSelected = true;
		         sketch.setshapeSelected(false);
		       } else if (e.getSource() == colors) {
		           
		           sketch.booleanSetter2(false, false, false, false);
		           sketch.changecolor();
		      	 sketch.emptySelected();
		           sketch.setshapeSelected(false);
		 
		 
		       } else if (e.getSource() == select) {
		           sketch.setshapeSelected(false);
		           sketch.booleanSetter2(false, false, false, false);
		           sketch.emptySelected();
		  sketch.booleanSetter(false, false, false, false, false, false, false, false, false);
		  sketch.setCounter(0);
		 sketch.setselectSelected(true);
		     }
		       else if (e.getSource() == resize) {
		             sketch.booleanSetter(false, false, false, false, false, false, false, false, false);
		 
		           sketch.booleanSetter2(false, false, false, true);
		 
		              }
		       
		       else if (e.getSource() == move) {
		   
		           sketch.booleanSetter2(true, false, false, false);
		         sketch.booleanSetter(false, false, false, false, false, false, false, false, false);
		 
		           
		           sketch.setMove(true);
		   } else if (e.getSource() == lineBtn) {
		    		 sketch.emptySelected();

		           sketch.setshapeSelected(false);
		           sketch.booleanSetter2(false, false, false, false);
		  	  	 sketch.setselectSelected(false);

		           sketch.booleanSetter(false, true, false, false, false, false, false, false, false);
		 
		       } else if (e.getSource() == triangle) {
		    		 sketch.emptySelected();

		           sketch.setshapeSelected(false);
		           sketch.booleanSetter2(false, false, false, false);
		  	  	 sketch.setselectSelected(false);

		           sketch.booleanSetter(false, false, true, false, false, false, false,false, false);
		 
		    } else if (e.getSource() == square) {
		   	 sketch.emptySelected();

		        sketch.setshapeSelected(false);
		        sketch.booleanSetter2(false, false, false, false);
			  	 sketch.setselectSelected(false);

		        sketch.booleanSetter(false, false, false, true, false, false, false, false, false);
		 
		 
		    } else if (e.getSource() == rectangle) {
		   	 sketch.emptySelected();

		        sketch.setshapeSelected(false);
		        sketch.booleanSetter2(false, false, false, false);
			  	 sketch.setselectSelected(false);

		        sketch.booleanSetter(false, false, false, false, true, false, false,false, false);
		 
		     } else if (e.getSource() == circle) {
		    	 sketch.emptySelected();

		         sketch.setshapeSelected(false);
		         sketch.booleanSetter2(false, false, false, false);
		 
			  	 sketch.setselectSelected(false);

		         sketch.booleanSetter(false, false, false, false, false, true, false, false, false);
		 
		     } else if (e.getSource() == ellipse) {
		    	 sketch.emptySelected();

		         sketch.setshapeSelected(false);
		         sketch.booleanSetter2(false, false, false, false);
			  	 sketch.setselectSelected(false);

		         sketch.booleanSetter(false, false, false, false, false, false, true, false, false);
		 
		          }
		     else if (e.getSource() == delete) {
		         sketch.booleanSetter(false, false, false, false, false, false, false, false, false);
		 
		         sketch.booleanSetter2(false, false, true, false);
		 
		 
		sketch.deleteSelected();
		          }else if (e.getSource() == fill) {
		             sketch.booleanSetter(false, false, false, false, false, false, false,false, false);
		 
		              sketch.booleanSetter2(false, true, false, false);
		 
		sketch.fillColor();
		              } else if (e.getSource() == undo) {
		            		 sketch.emptySelected();

		                     sketch.setshapeSelected(false);
		 
		                       sketch.booleanSetter2(false, false, false, false);
		 
		 
		                     sketch.booleanSetter(false, false, false, false, false, false, false, true, false);
		            	  	 sketch.setselectSelected(false);

		 
		              sketch.undo();
		 
		         } else if (e.getSource() == redo) {
		        	 sketch.emptySelected();

		             sketch.setshapeSelected(false);
		 
		             sketch.booleanSetter2(false, false, false, false);
		 
		    	  	 sketch.setselectSelected(false);

		             sketch.booleanSetter(false, false, false, false, false, false, false, false, true);
		 
		             sketch.redo();
		             }
		         else if (e.getSource() == save) {
		             FileNameExtensionFilter filter = new FileNameExtensionFilter(".json & .xml files", "json", "xml");
		             fileChooser.setFileFilter(filter);

		             if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {

		                 File file = fileChooser.getSelectedFile();
		                 String name = file.getPath();
		                 int index = name.indexOf('.');
		                 try {
		                     if (index == -1) {
		                    	 JOptionPane.showMessageDialog(null , "Error",
		                                 "Wrong Format",
		                                 JOptionPane.ERROR_MESSAGE);	 
		                     
		                     } else if (name.substring(index + 1).equals("xml")) {
		                         sketch.saveXml(sketch.ShapeAdded, file.getPath());
		                     } else if (name.substring(index + 1).equals("json")) {
		                         sketch.saveJson(sketch.ShapeAdded, file.getPath());
		                     } else {
		                         JOptionPane.showMessageDialog(null , "Error",
		                                 "Wrong Format",
		                                 JOptionPane.ERROR_MESSAGE);
		                     }
		                 } catch (Exception e1) {
		                     JOptionPane.showMessageDialog(null , "Error",
		                             "File Error, please recheck.",
		                             JOptionPane.ERROR_MESSAGE);
		                 }

		             }

		         }

		         else if (e.getSource() == load) {
		             FileNameExtensionFilter filter = new FileNameExtensionFilter(".json & .xml & .jar files", "json", "xml" , "jar");
		             fileChooser.setFileFilter(filter);

		             if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {

		                 File file = fileChooser.getSelectedFile();
		                 if (file.getName().lastIndexOf(".") == -1){
		                     JOptionPane.showMessageDialog(null , "Error",
		                             "Wrong Format",
		                             JOptionPane.ERROR_MESSAGE);
		                	 
		                 }
		                 else{
		                 String extension = file.getName().substring(file.getName().lastIndexOf("."),
		                         file.getName().length() );
		                 System.out.println(extension);
		                 try {
		                     if (extension.equals(".xml")) {
		                         sketch.load(0, file.getPath());
		                     } else if(extension.equals(".json")){
		                         sketch.load(1, file.getPath());
		                     }
		                     else if(extension.equals(".jar")){
		                         LoaderOfClasses.load(file.getPath());
		                     }
		                     else{
		                         JOptionPane.showMessageDialog(null , "Error",
		                                 "Wrong Format",
		                                 JOptionPane.ERROR_MESSAGE);
		                     }
		                 } catch (Exception e1) {
		                     // TODO Auto-generated catch block
		                     e1.printStackTrace();
		                 }

		             }
		           }
		         }

	   }
	 };

	 
	 public static void main(String[] args) {

	  new GUI().show();

	 }
	private void makeBtn(JButton btn){
		
		btn.setVisible(true);
		btn.addActionListener(action);
		   btn.setBackground(Color.white);
		   btn.setForeground(Color.black);
		   getContentPane().add(btn);
		   if (btn == undo || btn == redo || btn == select
				   || btn == delete || btn == resize || btn == move
				   || btn == fill)
			   controls2.add(btn);
		   else
		   controls.add(btn);


	}
	 public void show() {
	   paint.setLayout(new BorderLayout());
	   content.add(sketch, BorderLayout.SOUTH);
	   content.setLayout(new BorderLayout());
	   content.add(sketch, BorderLayout.CENTER);
	   JButton BlackBtn = new JButton();
	   BlackBtn.setBackground(Color.black);
	   BlackBtn.addActionListener(action);

	clearBtn = new JButton(new ImageIcon(getClass().getResource("CLEAR.png")));
		   makeBtn(clearBtn);
		 save = new JButton(new ImageIcon(getClass().getResource("save.png")));
		   makeBtn(save);
		load = new JButton(new ImageIcon(getClass().getResource("load.png")));
		   makeBtn(load);
	square = new JButton(new ImageIcon(getClass().getResource("square2.png")));
		   makeBtn(square);
		   square.setVisible(false);
	rectangle = new JButton(new ImageIcon(getClass().getResource("rectangle.png")));
		   makeBtn(rectangle);
	ellipse = new JButton(new ImageIcon(getClass().getResource("ellipse.png")));
		   makeBtn(ellipse);
	triangle = new JButton(new ImageIcon(getClass().getResource("triangle.png")));
		   makeBtn(triangle);
	 lineBtn = new JButton(new ImageIcon(getClass().getResource("line.png")));
		   makeBtn(lineBtn);
	 circle = new JButton(new ImageIcon(getClass().getResource("circle.png")));
		   makeBtn(circle);
	colors = new JButton(new ImageIcon(getClass().getResource("colors.png")));
		   makeBtn(colors);
		undo = new JButton(new ImageIcon(getClass().getResource("undo.png")));
		   makeBtn(undo);
		   
		   delete = new JButton(new ImageIcon(getClass().getResource("delete.png")));
		   makeBtn(delete);
		   resize = new JButton(new ImageIcon(getClass().getResource("resize.png")));
		   makeBtn(resize);
		   select = new JButton(new ImageIcon(getClass().getResource("select.png")));
		   makeBtn(select);
		   move = new JButton(new ImageIcon(getClass().getResource("move.png")));
		   makeBtn(move);
		   fill = new JButton(new ImageIcon(getClass().getResource("fill.png")));
		   makeBtn(fill);
		   redo = new JButton(new ImageIcon(getClass().getResource("redo.png")));
		   makeBtn(redo);
	 
		  




	controls.setBackground(Color.black);
	   content.add(controls, BorderLayout.NORTH);
	   controls2.setBackground(Color.black);
	   content.add(controls2, BorderLayout.SOUTH);
	   paint.setResizable(true);
	   paint.setSize(1300, 900);
	   paint.setVisible(true);
	 }

	 public static void clasLoaded (boolean classLoaded){
		 square.setVisible(true);
	 }
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	}

