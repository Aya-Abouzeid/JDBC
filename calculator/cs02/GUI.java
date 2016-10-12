package eg.edu.alexu.csd.oop.calculator.cs02;
import javax.jws.soap.SOAPBinding.Style;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame implements MouseListener {
	private JTextField label;
    private JButton dot, zero, one, two, three, four, five, six, previous, next,
    seven, eight, nine, multiply, divide, minus, plus, equal, current,save, load;
    MyCalculator cal = new MyCalculator();
    	public GUI() {

    		setVisible(true);
    	setTitle("Calculator");
    	setLocation(1400, 200);
    	setSize(460, 520);
    	setResizable(false);
    	label = new JTextField(null);
    	label.setBackground(Color.white);
    	label.setEditable(false);
    	label.setBounds(20, 30, 420, 90);
    	getContentPane().add(label);

    	dot = new JButton();
    	dot.setText(".");
    	dot.setVisible(true);
        dot.setBackground(Color.white);
        dot.setForeground(Color.black);
    	dot.setBounds(20, 350, 90, 50);
    	dot.addMouseListener(this);
    	getContentPane().add(dot);

    	one = new JButton();
    	one.setText("1");
    	one.setVisible(true);
        one.setBackground(Color.white);
        one.setForeground(Color.black);
    	one.addMouseListener(this);
    	one.setBounds(20, 280, 90, 50);
    	getContentPane().add(one);

    	two = new JButton();
    	two.setText("2");
    	two.setVisible(true);
        two.setBackground(Color.white);
        two.setForeground(Color.black);
    	two.setBounds(130, 280, 90, 50);
    	two.addMouseListener(this);
    	getContentPane().add(two);

    	three = new JButton();
    	three.setText("3");
    	three.setVisible(true);
        three.setBackground(Color.white);
    	three.addMouseListener(this);
        three.setForeground(Color.black);
    	three.setBounds(240, 280, 90, 50);
    	getContentPane().add(three);

    	four = new JButton();
    	four.setText("4");
    	four.setVisible(true);
        four.setBackground(Color.white);
    	four.addMouseListener(this);
        four.setForeground(Color.black);
    	four.setBounds(20, 210, 90, 50);
    	getContentPane().add(four);

    	five = new JButton();
    	five.setVisible(true);
    	five.setText("5");
    	five.addMouseListener(this);
        five.setBackground(Color.white);
        five.setForeground(Color.black);
    	five.setBounds(130, 210, 90, 50);
    	getContentPane().add(five);

    	six = new JButton();
    	six.setVisible(true);
    	six.setText("6");
        six.setBackground(Color.white);
        six.setForeground(Color.black);
    	six.addMouseListener(this);
    	six.setBounds(240, 210, 90, 50);
    	getContentPane().add(six);

    	seven = new JButton();
    	seven.setText("7");
        seven.setBackground(Color.white);
        seven.setForeground(Color.black);
    	seven.setVisible(true);
    	seven.addMouseListener(this);
    	seven.setBounds(20, 140, 90, 50);
    	getContentPane().add(seven);

    	eight = new JButton();
    	eight.setText("8");
        eight.setBackground(Color.white);
        eight.setForeground(Color.black);
    	eight.setBounds(130, 140, 90, 50);
    	eight.addMouseListener(this);
    	eight.setVisible(true);
    	getContentPane().add(eight);

    	nine = new JButton();
    	nine.setText("9");
    	nine.addMouseListener(this);
        nine.setBackground(Color.white);
        nine.setForeground(Color.black);
    	nine.setBounds(240, 140, 90, 50);
    	nine.setVisible(true);
    	getContentPane().add(nine);

    	zero = new JButton();
    	zero.setText("0");
        zero.setBackground(Color.white);
    	zero.setVisible(true);
        zero.setForeground(Color.black);
    	zero.setBounds(130, 350, 90, 50);
    	zero.addMouseListener(this);
    	getContentPane().add(zero);

    	multiply = new JButton();
    	multiply.setText("X");
        multiply.setBackground(Color.white);
    	multiply.setVisible(true);
        multiply.setForeground(Color.black);
    	multiply.addMouseListener(this);
    	multiply.setBounds(350, 210, 90, 50);
    	getContentPane().add(multiply);

    	divide = new JButton();
    	divide.setText("/");
        divide.setBackground(Color.white);
        divide.setForeground(Color.black);
    	divide.addMouseListener(this);
    	divide.setBounds(350, 140, 90, 50);
    	divide.setVisible(true);
    	getContentPane().add(divide);

    	plus = new JButton();
    	plus.setText("+");
        plus.setBackground(Color.white);
        plus.setForeground(Color.black);
    	plus.addMouseListener(this);
    	plus.setVisible(true);
    	plus.setBounds(350, 350, 90, 50);
    	getContentPane().add(plus);

    	minus = new JButton();
    	minus.setText("-");
    	minus.addMouseListener(this);
        minus.setBackground(Color.white);
        minus.setForeground(Color.black);
    	minus.setVisible(true);
    	minus.setBounds(350, 280, 90, 50);
    	getContentPane().add(minus);

    	equal = new JButton();
    	equal.setText("=");
        equal.setBackground(Color.white);
    	equal.addMouseListener(this);
    	equal.setVisible(true);
        equal.setForeground(Color.black);
    	equal.setBounds(240, 350, 90, 50);
    	getContentPane().add(equal);

    	save = new JButton();
    	save.setText("SAVE");
        save.setBackground(Color.white);
    	save.setVisible(true);
        save.setForeground(Color.black);
    	save.setBounds(20, 420, 70, 50);
    	save.addMouseListener(this);
    	getContentPane().add(save);


    	previous = new JButton();
    	previous.setText("<");
    	previous.setBackground(Color.white);
    	previous.setVisible(true);
    	previous.setForeground(Color.black);
    	previous.setBounds(110, 420, 70, 50);
    	previous.addMouseListener(this);
    	getContentPane().add(previous);

    	current = new JButton();
    	current.setText("Current");
    	current.setBackground(Color.white);
        current.setVisible(true);
        current.setForeground(Color.black);
        current.setBounds(190, 420, 80, 50);
        current.addMouseListener(this);
    	getContentPane().add(current);

    	next = new JButton();
    	next.setText(">");
    	next.setBackground(Color.white);
        next.setVisible(true);
        next.setForeground(Color.black);
        next.setBounds(280, 420, 70, 50);
        next.addMouseListener(this);
    	getContentPane().add(next);


    	load = new JButton();
    	load.setText("LOAD");
        load.setBackground(Color.white);
    	load.setVisible(true);
        load.setForeground(Color.black);
    	load.setBounds(370, 420, 70, 50);
    	load.addMouseListener(this);
    	getContentPane().add(load);

    	getContentPane().setBackground(Color.pink);
    	getContentPane().setLayout(null);
}
    	boolean noresult = true;

		@Override
		public void mouseClicked(MouseEvent e) {
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
			if (e.getSource() == multiply) {
				if (noresult) {
					text = text + " * ";
				label.setText(text);
				} else {
					label.setText(" * ");
					noresult = true;
}
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
				if (noresult) {

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
				cal.save();
			}
			if (e.getSource() == current) {

				String result = cal.current();
				label.setText(result);
				noresult = false;

			}
			if (e.getSource() == load) {
				cal.load();
			}
			if (e.getSource() == previous) {
				String result = cal.prev();
				label.setText(result);

			}
			if (e.getSource() == next) {
				String result = cal.next();
				label.setText(result);

			}

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