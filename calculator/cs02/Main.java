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
	public static void main(final String[] args) {
		// TODO Auto-generated method stub

		// GUI mygui = new GUI();
	       MyCalculator test = new MyCalculator();
	       test.input("1+2");
           test.input("2+3");
           test.input("3+4");
           test.input("4+5");
           test.input("5+6");
           test.input("6+7");
           System.out.println(test.getResult());
           System.out.println(test.prev());
           System.out.println(test.prev());
           System.out.println(test.prev());
           System.out.println(test.prev());
           System.out.println(test.prev());
           System.out.println(test.prev());
           System.out.println(test.current());
           test.load();
		     System.out.println(test.current()); 
		     System.out.println(test.next()); 

	       //test.load();
	    


	}
}
