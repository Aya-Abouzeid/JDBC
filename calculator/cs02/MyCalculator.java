
package eg.edu.alexu.csd.oop.calculator.cs02;

import eg.edu.alexu.csd.oop.calculator.Calculator;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import javax.script.ScriptEngine;
/**.
 * calculator class
 */
public class MyCalculator implements Calculator {
	/**.
	 * Magic number :3
	 */
	private final int five = 5;
	/**.
	 * Magic number :3
	 */
	private final int four = 4;
	/**.
	 * engine
	 */
	 private ScriptEngineManager manager = new ScriptEngineManager();
/**.
 * engine
 */
	   private ScriptEngine engine = manager.getEngineByName("JavaScript");
	    /**.
	     * counter
	     */
	   private int counter = 0;

	    /**.
	     * moves forward and backward along the array
	     */
	  private int index = 0;
	    /**.
	     * used array
	     */
	  private String[] a = new String[five];
	@Override
	public void input(final String s) {
		// TODO Auto-generated method stub


			index = 0;
		if (counter < five) {

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

	@Override
	public String getResult() {
		String s = null;
		String str = "error";
		// TODO Auto-generated method stub
		try {
			Object answer = engine.eval(a[index]);
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
			outofindex = false;
		return a[index];

		}
	}


	@Override

	public String prev() {
		// TODO Auto-generated method stub
	if ((index + 1) >= counter) {
			return null;
		} else {
			index++;
			return a[index];
		}
	}
	/**.
	 * returned a null in a previous operation
	 */
	private boolean outofindex = false;
	@Override
	public String next() {
		// TODO Auto-generated method stub


		if ((index - 1) < 0) {
			return null;
		} else {
			index--;
			return a[index];
		}
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub

		PrintWriter writer;
		try {
			writer = new PrintWriter("saving.txt", "UTF-8");

		    writer.println(counter);

		    writer.println(index);

			for (int i = 0; i < counter; i++) {
			    writer.println(a[i]);
			}
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

			line = reader.readLine();
			counter = Integer.parseInt(line);

			line = reader.readLine();
			index = Integer.parseInt(line);

//			index = 0;
				for (int i = 0; i < counter; i++) {
					line = reader.readLine();
					a[i] = line;
				}
				reader.close();
				//current(); msh 3rfa leha lzma wala la2
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
