/**.
 * calculator package
 */
package eg.edu.alexu.csd.oop.calculator.cs02;

import eg.edu.alexu.csd.oop.calculator.Calculator;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.*;
import java.lang.*;
import java.util.*;
import javax.script.ScriptEngine;

/**.
 * class for calculator
 */
public class MyCalculator implements Calculator {

	final int five = 5;

	final int four = 4;

	   ScriptEngineManager manager = new ScriptEngineManager();

	    ScriptEngine engine = manager.getEngineByName("JavaScript");

	    String exp;

	    int counter = 0;

	    String order;

	    int bounds = 0;
	    String A[] = new String[5];
	@Override
	public void input(String s) {
		// TODO Auto-generated method stub
		exp = s;
		if (counter == 0) {

			A[counter] = s;
			counter++;

		} else if (counter < five && counter > 0) {

			for (int i = counter; i > 0; i--) {
				A[i] = A[i - 1];
			}
			A[0] = s;
			counter++;

		} else {

			for (int i = four; i > 0; i--) {
				A[i] = A[i - 1];
			}
			A[0] = s;

		}

	}

	@Override
	public String getResult() {
		
		String S = null;
		String str = "error";
		// TODO Auto-generated method stub
		try {
		System.out.println("current bounds in equal= " + bounds);
			int tryy = bounds;
			while(tryy>=0){
			System.out.println(A[tryy]);
			tryy--;
			}Object answer = engine.eval(A[bounds]);
			double value = Double.parseDouble(answer.toString());
			S = Double.toString(value);
			System.out.println(S);

		} catch (ScriptException e) {
			// TODO Auto-generated catch block
				return str;
		}
		return S;
	}

	@Override
	public String current() {

		// TODO Auto-generated method stub
		if (counter == 0) {
			return null;
		} else {
			System.out.println("current bounds in current= " + bounds);
			System.out.println(A[bounds]);

		return A[bounds];

		}
	}


	@Override

	public String prev() {
		// TODO Auto-generated method stub
	if ((bounds + 1) >= counter || (bounds + 1) > four) {
			return null;
		} else {
			bounds++;
			System.out.println("counter = " + counter);

			System.out.println("bounds = " + bounds);

		order = A[bounds];
		return order;
		}
	}

	@Override
	public String next() {
		// TODO Auto-generated method stub


		if ((bounds - 1) < 0) {
			return null;
		} else {
			bounds--;
			order = A[bounds];
			return order;
		}
	}
	private Formatter x;

	@Override
	public void save() {
		// TODO Auto-generated method stub

		PrintWriter writer;
		try {
			writer = new PrintWriter("saving.txt", "UTF-8");

		    writer.println(bounds);

			for (int i = 0; i < five; i++) {
				if (A[i] != null) {
			    writer.println(A[i]);
				}
			}
			bounds = 0;
			writer.close();

	} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			System.out.println("Error saving");
		}


	}

	private Scanner y;
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
			line = reader.readLine();
			bounds = Integer.parseInt(line);

				for (int i = 0; i < five; i++) {
					line = reader.readLine();

					A[i] = line;
				}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
