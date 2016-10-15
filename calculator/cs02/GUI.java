
package eg.edu.alexu.csd.oop.calculator.cs02;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
/**
 *
 * @author aya_a_000
 *
 */
public class GUI extends JFrame implements MouseListener {
	/**.
	 *label
	 */
	private JTextField label;
	/**.
	 *buttons
	 */
    private JButton dot, zero, one, two, three, four, five, six, previous, next,
  seven, eight, nine, multiply, divide, minus, plus, equal, current, save, load,
  clear, open, close;
    /**.
     *calculator instance
     */
    private MyCalculator cal = new MyCalculator();
    /**.
	 * Magic number :3
	 */
    private final int fourtwozero = 420;
    /**.
	 * Magic number
	 */
    private final int threesevenzero = 370;
    /**.
	 * Magic number
	 */
    private final int fifty = 50;
    /**.
	 * Magic number
	 */
    private final int  twenty = 20;
    /**.
	 * Magic number
	 */
    private final int thirty = 30;
    /**.
	 * Magic number
	 */
    private final int fourteenhundred = 1400;
    /**.
	 * Magic number
	 */
    private final int twohundred = 200;
    /**.
	 * Magic number
	 */
    private final int foursixty = 460;
    /**.
	 * Magic number
	 */
    private final int sixhundred = 600;
    /**.
	 * Magic number
	 */
    private final int threefifty = 350;
    /**.
	 * Magic number
	 */
    private final int oneten = 110;
    /**.
	 * Magic number
	 */
    private final int fourninety = 490;
    /**.
	 * Magic number
	 */
    private final int twoninety = 290;
    /**.
	 * Magic number
	 */
    private final int ninety = 90;
    /**.
	 * Magic number
	 */
    private final int twoeighty = 280;
    /**.
	 * Magic number
	 */
    private final int twofourty = 240;
    /**.
	 * Magic number
	 */
    private final int twoten = 210;
    /**.
	 * Magic number
	 */
    private final int onethirty = 130;
    /**.
	 * Magic number
	 */
    private final int oneeighty = 180;
    /**.
	 * Magic number
	 */
    private final int oneninety = 190;
    /**.
	 * Magic number
	 */
    private final int seventy = 70;
    /**.
	 * Magic number
	 */
    private final int eighty = 80;
    /**.
	 * Magic number
	 */
    private final int onefourty = 140;
    /**.
     *gui constructor
     */
    	public GUI() {

    		setVisible(true);
    	setTitle("Calculator");
    	setLocation(fourteenhundred, twohundred);
    	setSize(foursixty, sixhundred);
    	setResizable(false);
    	label = new JTextField(null);
    	label.setBackground(Color.white);
    	label.setEditable(false);
    	label.setBounds(twenty, thirty, fourtwozero, ninety);
    	getContentPane().add(label);

    	dot = new JButton();
    	dot.setText(".");
    	dot.setVisible(true);
        dot.setBackground(Color.white);
        dot.setForeground(Color.black);
    	dot.setBounds(twenty, threefifty, ninety, fifty);
    	dot.addMouseListener(this);
    	getContentPane().add(dot);

    	one = new JButton();
    	one.setText("1");
    	one.setVisible(true);
        one.setBackground(Color.white);
        one.setForeground(Color.black);
    	one.addMouseListener(this);
    	one.setBounds(twenty, twoeighty, ninety, fifty);
    	getContentPane().add(one);

    	two = new JButton();
    	two.setText("2");
    	two.setVisible(true);
        two.setBackground(Color.white);
        two.setForeground(Color.black);
    	two.setBounds(onethirty, twoeighty, ninety, fifty);
    	two.addMouseListener(this);
    	getContentPane().add(two);

    	three = new JButton();
    	three.setText("3");
    	three.setVisible(true);
        three.setBackground(Color.white);
    	three.addMouseListener(this);
        three.setForeground(Color.black);
    	three.setBounds(twofourty, twoeighty, ninety, fifty);
    	getContentPane().add(three);

    	four = new JButton();
    	four.setText("4");
    	four.setVisible(true);
        four.setBackground(Color.white);
    	four.addMouseListener(this);
        four.setForeground(Color.black);
    	four.setBounds(twenty, twoten, ninety, fifty);
    	getContentPane().add(four);

    	five = new JButton();
    	five.setVisible(true);
    	five.setText("5");
    	five.addMouseListener(this);
        five.setBackground(Color.white);
        five.setForeground(Color.black);
    	five.setBounds(onethirty, twoten, ninety, fifty);
    	getContentPane().add(five);



    	six = new JButton();
    	six.setVisible(true);
    	six.setText("6");
        six.setBackground(Color.white);
        six.setForeground(Color.black);
    	six.addMouseListener(this);
    	six.setBounds(twofourty, twoten, ninety, fifty);
    	getContentPane().add(six);

    	seven = new JButton();
    	seven.setText("7");
        seven.setBackground(Color.white);
        seven.setForeground(Color.black);
    	seven.setVisible(true);
    	seven.addMouseListener(this);
    	seven.setBounds(twenty, onefourty, ninety, fifty);
    	getContentPane().add(seven);

    	eight = new JButton();
    	eight.setText("8");
        eight.setBackground(Color.white);
        eight.setForeground(Color.black);
    	eight.setBounds(onethirty, onefourty, ninety, fifty);
    	eight.addMouseListener(this);
    	eight.setVisible(true);
    	getContentPane().add(eight);

    	nine = new JButton();
    	nine.setText("9");
    	nine.addMouseListener(this);
        nine.setBackground(Color.white);
        nine.setForeground(Color.black);
    	nine.setBounds(twofourty, onefourty, ninety, fifty);
    	nine.setVisible(true);
    	getContentPane().add(nine);

    	zero = new JButton();
    	zero.setText("0");
        zero.setBackground(Color.white);
    	zero.setVisible(true);
        zero.setForeground(Color.black);
    	zero.setBounds(onethirty, threefifty, ninety, fifty);
    	zero.addMouseListener(this);
    	getContentPane().add(zero);

    	multiply = new JButton();
    	multiply.setText("X");
        multiply.setBackground(Color.white);
    	multiply.setVisible(true);
        multiply.setForeground(Color.black);
    	multiply.addMouseListener(this);
    	multiply.setBounds(threefifty, twoten, ninety, fifty);
    	getContentPane().add(multiply);

    	divide = new JButton();
    	divide.setText("/");
        divide.setBackground(Color.white);
        divide.setForeground(Color.black);
    	divide.addMouseListener(this);
    	divide.setBounds(threefifty, onefourty, ninety, fifty);
    	divide.setVisible(true);
    	getContentPane().add(divide);

    	gui2();
}
/**.
 *gui 2nd class
 */
    public void gui2() {
    	clear = new JButton();
    	clear.setVisible(true);
    	clear.setText("CLEAR");
    	clear.addMouseListener(this);
    	clear.setBackground(Color.white);
    	clear.setForeground(Color.black);
        clear.setBounds(oneninety, fourtwozero, eighty, fifty);
    	getContentPane().add(clear);

    	open = new JButton();
    	open.setVisible(true);
    	open.setText("(");
    	open.addMouseListener(this);
    	open.setBackground(Color.white);
    	open.setForeground(Color.black);
    	open.setBounds(oneten, fourtwozero, seventy, fifty);
    	getContentPane().add(open);

    	close = new JButton();
    	close.setVisible(true);
    	close.setText(")");
    	close.addMouseListener(this);
    	close.setBackground(Color.white);
    	close.setForeground(Color.black);
        close.setBounds(twoeighty, fourtwozero, seventy, fifty);
    	getContentPane().add(close);

        	plus = new JButton();
        	plus.setText("+");
            plus.setBackground(Color.white);
            plus.setForeground(Color.black);
        	plus.addMouseListener(this);
        	plus.setVisible(true);
        	plus.setBounds(threefifty, threefifty, ninety, fifty);
        	getContentPane().add(plus);

        	minus = new JButton();
        	minus.setText("-");
        	minus.addMouseListener(this);
            minus.setBackground(Color.white);
            minus.setForeground(Color.black);
        	minus.setVisible(true);
        	minus.setBounds(threefifty, twoeighty, ninety, fifty);
        	getContentPane().add(minus);

        	equal = new JButton();
        	equal.setText("=");
            equal.setBackground(Color.white);
        	equal.addMouseListener(this);
        	equal.setVisible(true);
            equal.setForeground(Color.black);
        	equal.setBounds(twofourty, threefifty, ninety, fifty);
        	getContentPane().add(equal);

        	save = new JButton();
        	save.setText("SAVE");
            save.setBackground(Color.white);
        	save.setVisible(true);
            save.setForeground(Color.black);
        	save.setBounds(twenty, fourtwozero, seventy, fifty);
        	save.addMouseListener(this);
        	getContentPane().add(save);


        	previous = new JButton();
        	previous.setText("<");
        	previous.setBackground(Color.white);
        	previous.setVisible(true);
        	previous.setForeground(Color.black);
        	previous.setBounds(seventy, fourninety, ninety, fifty);
        	previous.addMouseListener(this);
        	getContentPane().add(previous);

        	current = new JButton();
        	current.setText("Current");
        	current.setBackground(Color.white);
            current.setVisible(true);
        	current.setBounds(oneeighty, fourninety, ninety, fifty);
            current.setForeground(Color.black);
            current.addMouseListener(this);
        	getContentPane().add(current);

        	next = new JButton();
        	next.setText(">");
        	next.setBackground(Color.white);
            next.setVisible(true);
            next.setForeground(Color.black);
            next.setBounds(twoninety, fourninety, ninety, fifty);
            next.addMouseListener(this);
        	getContentPane().add(next);


        	load = new JButton();
        	load.setText("LOAD");
            load.setBackground(Color.white);
        	load.setVisible(true);
            load.setForeground(Color.black);
        	load.setBounds(threesevenzero, fourtwozero, seventy, fifty);
        	load.addMouseListener(this);
        	getContentPane().add(load);

        	getContentPane().setBackground(Color.pink);
        	getContentPane().setLayout(null);

    	}
    	/**.
    	 * empty label boolean
    	 */
    	private boolean noresult = true;
    	/**.
    	 *
    	 */
    	private boolean prev = false;
    	/**.
   	 *
   	 */
    	private boolean nextt = false;

		@Override
		public void mouseClicked(final MouseEvent e) {
			// TODO Auto-generated method stub
			String text = label.getText();
			if (e.getSource() == zero) {
				if (noresult) {
					text = text + "0";
				label.setText(text);
				} else {
					noresult = true;
					label.setText("0");
					}
			}
			if (e.getSource() == one) {
				if (noresult) {
					text = text + "1";
				label.setText(text);
				} else {
					label.setText("1");
					noresult = true;

				}
			}
			if (e.getSource() == two) {
				if (noresult) {
					text = text + "2";
				label.setText(text);
				} else {
					label.setText("2");
					noresult = true;

				}
			}
			if (e.getSource() == three) {
				if (noresult) {
					text = text + "3";
				label.setText(text);
				} else {
					label.setText("3");
					noresult = true;

				}
			}
			if (e.getSource() == four) {
				if (noresult) {
					text = text + "4";
				label.setText(text);
				} else {
					label.setText("4");
					noresult = true;

				}
			}
			if (e.getSource() == five) {
				if (noresult) {
					text = text + "5";
				label.setText(text);
				} else {
					label.setText("5");
					noresult = true;

				}
			}
			if (e.getSource() == six) {
				if (noresult) {
					text = text + "6";
				label.setText(text);
				} else {
					label.setText("6");
					noresult = true;

				}
			}
			if (e.getSource() == seven) {
				if (noresult) {
					text = text + "7";
				label.setText(text);
				} else {
					label.setText("7");
					noresult = true;

				}
			}
			if (e.getSource() == eight) {
				if (noresult) {
					text = text + "8";
				label.setText(text);
				} else {
					label.setText("8");
					noresult = true;
}
			}
			if (e.getSource() == nine) {
				if (noresult) {
					text = text + "9";
				label.setText(text);
				} else {
					label.setText("9");
					noresult = true;
}
			}
			if (e.getSource() == open) {
				if (noresult) {
					text = text + "(";
				label.setText(text);
				} else {
					label.setText("(");
					noresult = true;
}
			}
			if (e.getSource() == close) {
				if (noresult) {
					text = text + ")";
				label.setText(text);
				} else {
					label.setText(")");
					noresult = true;
}
			}
			if (e.getSource() == nine) {
				if (noresult) {
					text = text + "9";
				label.setText(text);
				} else {
					label.setText("9");
					noresult = true;
}
			}
			if (e.getSource() == multiply) {
				if (noresult) {
					text = text + " * ";
				label.setText(text);
				} else {
					label.setText(" * ");
					noresult = true;
}
			}
			if (e.getSource() == clear) {

				label.setText("");

			}
			if (e.getSource() == divide) {
				if (noresult) {
					text = text + " / ";
				label.setText(text);
				} else {
					label.setText(" / ");
					noresult = true;
}
			}
			if (e.getSource() == minus) {
				if (noresult) {
					text = text + " - ";
				label.setText(text);
				} else {
					label.setText(" - ");
					noresult = true;

				}
			}
			if (e.getSource() == plus) {
				if (noresult) {
					text = text + " + ";
				label.setText(text);
				} else {
					label.setText(" + ");
					noresult = true;
}
			}
			if (e.getSource() == equal) {
				if (noresult || prev || nextt) {
				noresult = false;
				cal.input(text);
				String result = cal.getResult();
				text = text + " = ";
				text = text + result;
				label.setText(text);
				}
			}
			if (e.getSource() == dot) {
				if (noresult) {
					text = text + ".";
				label.setText(text);
				} else {
					label.setText(".");
					noresult = true;

				}
			}
			if (e.getSource() == save) {
				noresult = false;

				cal.save();
			}
			if (e.getSource() == current) {

				String result = cal.current();
				label.setText(result);
				noresult = true;

			}
			if (e.getSource() == load) {
				noresult = true;
				cal.load();
				String currentequ = cal.current();
				label.setText(currentequ);
			}
			if (e.getSource() == previous) {
				String result = cal.prev();
				label.setText(result);
				prev = true;

			}
			if (e.getSource() == next) {
				String result = cal.next();
				label.setText(result);
				nextt = true;

			}

		}

		@Override
		public void mouseEntered(final MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(final MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(final MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(final MouseEvent e) {
			// TODO Auto-generated method stub

		}

}
