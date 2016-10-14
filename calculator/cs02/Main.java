/**
 *
 */
package eg.edu.alexu.csd.oop.calculator.cs02;
/**
 *
 * @author aya_a_000
 *
 */
public class Main {
	/**
	 *
	 *@param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		 GUI mygui = new GUI();
	       MyCalculator cvc = new MyCalculator();

	       cvc.input("1+2");
	       cvc.input("2+3");
	       cvc.input("3+4");
	       cvc.input("4+5");
	       cvc.input("5+6");
	       cvc.input("6+7");
	       System.out.println(cvc.getResult());
	       cvc.save();
	       System.out.println(cvc.current());
	       System.out.println(cvc.prev());
	       System.out.println(cvc.next());
	       System.out.println(cvc.current());
	       System.out.println(cvc.prev());
	       System.out.println(cvc.prev());
	       System.out.println(cvc.prev());
	       System.out.println(cvc.prev());
	       System.out.println(cvc.current());
	       cvc.input("7+8");
	       System.out.println(cvc.current());
	}
}
