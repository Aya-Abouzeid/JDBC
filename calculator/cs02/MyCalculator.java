package eg.edu.alexu.csd.oop.calculator.cs02;

import eg.edu.alexu.csd.oop.calculator.Calculator;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.*;
import java.lang.*;
import java.util.*;
import javax.script.ScriptEngine;

public class MyCalculator implements Calculator {


	/* Take input mathematical expression from user */
	   ScriptEngineManager manager = new ScriptEngineManager();
	    ScriptEngine engine = manager.getEngineByName("JavaScript");
	    String exp;
	    String current;
	    int counter = 0;

	    String saved = null;
	    String order;
	    int bounds=0;
	    String A[] = new String[5];
	@Override
	public void input(String s) {
		// TODO Auto-generated method stub
		exp =s;
		saved = s + " = " + getResult();
		if (counter == 0) {

			A[counter] = saved;
			System.out.println(counter + "  "+ A[0]);
			counter++;

		} else if (counter < 5 && counter > 0) {

			for(int i =counter ; i>0 ; i--)
			{
				A[i] = A[i-1];
			}
			A[0] = saved;
			System.out.println(counter + "  "+ A[0]);
			counter++;

		} else {

			for (int i = 4; i > 0; i--) {
				A[i] = A[i-1];
			}
			A[0] = saved;

		}

	}
	/* Return the result of the current operations
	 * or throws a runtime exception */

	@Override
	public String getResult() {
		String S = null;
		String str = "error";
		// TODO Auto-generated method stub
		try {
			Object answer = engine.eval(exp);
			double value = Double.parseDouble(answer.toString());
			S = Double.toString(value);
		} catch (ScriptException e) {
			// TODO Auto-generated catch block
				return str;
		}
		return S;
	}
	/* Return the current formula */

	@Override
	public String current() {

		// TODO Auto-generated method stub
		if (counter == 0) {
			return null;
		} else {
		return A[bounds];
		}
	}
	/* Return the last operation in String format,
	 * or Null if no more history available */

	@Override

	public String prev() {
		// TODO Auto-generated method stub
	if ((bounds + 1) > counter || (bounds + 1) > 4) {
			return null;
		} else {
			bounds++;
		order = A[bounds];
		return order;
		}
	}
	/* Return the next operation in String format,
	 *  or Null if no more history available */

	@Override
	public String next() {
		// TODO Auto-generated method stub


		if ((bounds - 1) < 0 ) {
			return null;
		} else {
			bounds--;
			order = A[bounds];
			return order;
		}
	}
	/* Save the last 5 done Operations in a file*/
	private Formatter x;

	@Override
	public void save() {
		// TODO Auto-generated method stub

		try {
			x = new Formatter("save.txt");

			for (int i = 0; i < 5; i++) {

				x.format(A[i]);
			}
		} catch (Exception e) {
			System.out.println("Error");
		}
      x.close();

	}

	/* Load from file the saved 5 operations */

	@Override
	public void load() {
		// TODO Auto-generated method stub

	}

}
