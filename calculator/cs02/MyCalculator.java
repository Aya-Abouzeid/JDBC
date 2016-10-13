
package eg.edu.alexu.csd.oop.calculator.cs02;

import eg.edu.alexu.csd.oop.calculator.Calculator;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.*;
import java.lang.*;
import java.util.*;
import javax.script.ScriptEngine;

public class MyCalculator implements Calculator {
	/**.
	 * Magic number :3
	 */
	final int five = 5;
	/**.
	 * Magic number :3
	 */
	final int four = 4;

	   ScriptEngineManager manager = new ScriptEngineManager();

	    ScriptEngine engine = manager.getEngineByName("JavaScript");

	    int counter = 0;
	    /**.
	     * determine whether to add a new string to the array or not
	     */
	    boolean newinput = true;
	    /**.
	     * moves forward and backward along the array
	     */
	    int bounds = 0;
	    /**.
	     * used array
	     */
	    String a[] = new String[5];
	@Override
	public void input(String s) {
		// TODO Auto-generated method stub

		if (newinput) {
			bounds = 0;
		if (counter == 0) {

			a[counter] = s;
			counter++;

		} else if (counter < five && counter > 0) {

			for (int i = counter; i > 0; i--) {
				a[i] = a[i - 1];
			}
			a[0] = s;
			counter++;

		} else {

			for (int i = four; i > 0; i--) {
				a[i] = a[i - 1];
			}
			a[0] = s;

		}
		}
	}

	@Override
	public String getResult() {
		newinput = true;
		String s = null;
		String str = "error";
		// TODO Auto-generated method stub
		try {

			Object answer = engine.eval(a[bounds]);
			double value = Double.parseDouble(answer.toString());
			s = Double.toString(value);

		} catch (ScriptException e) {
			// TODO Auto-generated catch block
				return str;
		}
		return s;
	}

	@Override
	public String current() {

		// TODO Auto-generated method stub
		if (counter == 0) {
			return null;
		} else {
			outofbounds = false;
			newinput = false;
		return a[bounds];

		}
	}


	@Override

	public String prev() {
		// TODO Auto-generated method stub
	if ((bounds + 1) >= counter || (bounds + 1) > four) {
		outofbounds = true;

			return null;
		} else {
			if (outofbounds) {
				outofbounds = false;
				newinput = false;

				return a[bounds];
			} else {

			newinput = false;
			bounds++;

		return a[bounds];
		}
		}
	}
	/**.
	 * returned a null in a previous operation
	 */
	boolean outofbounds = false;
	@Override
	public String next() {
		// TODO Auto-generated method stub


		if ((bounds - 1) < 0) {
			outofbounds = true;
			return null;
		} else {
			if (outofbounds) {
				newinput = false;

				outofbounds = false;
				return a[bounds];
			} else {
			newinput = false;
			bounds--;

			return a[bounds];
			}
		}
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub

		PrintWriter writer;
		try {
			writer = new PrintWriter("saving.txt", "UTF-8");

		    writer.println(counter);

		    writer.println(bounds);

			for (int i = 0; i < five; i++) {
				if (a[i] != null) {
			    writer.println(a[i]);
				}
			}
			newinput = true;
			writer.close();

	} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			System.out.println("Error saving");
		}


	}

	@Override
	public void load() {
		// TODO Auto-generated method stub

		BufferedReader reader = null;
		try {
	reader = new BufferedReader(new FileReader("saving.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String line = null;
		try {
			newinput = true;
			line = reader.readLine();
			counter = Integer.parseInt(line);
			line = reader.readLine();
			bounds = Integer.parseInt(line);

			System.out.println(bounds);

				for (int i = 0; i < five; i++) {
					line = reader.readLine();

					a[i] = line;
				}
				System.out.println(a[bounds]);
				System.out.println(current());


		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
