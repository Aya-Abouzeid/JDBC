package eg.edu.alexu.csd.oop.calculator.cs02;

import eg.edu.alexu.csd.oop.calculator.Calculator;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.*;
import javax.script.ScriptEngine;

public class MyCalculator implements Calculator {

	/* Take input mathematical expression from user */
	   ScriptEngineManager manager = new ScriptEngineManager();
	    ScriptEngine engine = manager.getEngineByName("JavaScript");
	    String exp;
	    String current;
	    int counter = 0;
//	    node head = new node (null);
//	    node tail = new node (null);
//	    node order = new node(null);
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
//			node node = new node(saved);
//			node.next = null;
//			node.prev = null;
			A[counter] = saved;
			counter++;
//			head = node;
//			tail = head;
//			order = node;
		} else if (counter < 5) {
//			node node = new node(saved);
//			node.next = head;
//			node.prev = null;
//			head.prev = node;
//			head.next = null;
//			tail = head;
//			head = node;
			A[counter]=saved;
			counter++;
//			order = node;
		} else {
//			tail.prev.next = null;
//			tail = tail.prev;
//			node node = new node(saved);
//			node.next = head;
//			node.prev = null;
//			head.prev = node;
//			head.next = null;
//			head = node;
//			order = node;
			for(int i =4 ; i>0 ; i--){
				A[i] = A[i-1];
				
			}
			A[0]=saved;

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
		return A[0];
		}
	}
	/* Return the last operation in String format,
	 * or Null if no more history available */

	@Override
	
	public String prev() {
		// TODO Auto-generated method stub
		if (counter == 0 || order.equals(A[4])) {
			return null;
		} else {
		order = A[bounds+1];
		return order;
		}
	}
	/* Return the next operation in String format,
	 *  or Null if no more history available */

	@Override
	public String next() {
		// TODO Auto-generated method stub
		order = A[bounds-1];

		if (counter == 0 ) {
			return null;
		} else if(!order.equals(A[0])){
		return order;
		}
		else{
			return order;
		}
	}
	/* Save the last 5 done Operations in a file*/

	@Override
	public void save() {
		// TODO Auto-generated method stub
//		try
//		 {
//		    File file = new File( save );
//
//		    // if file doesnt exists, then create it 
//		    if ( ! file.exists( ) )
//		    {
//		        file.createNewFile( );
//		    }
//
//		    FileWriter fw = new FileWriter( file.getAbsoluteFile( ) );
//		    BufferedWriter bw = new BufferedWriter( fw );
//		    bw.write( text );
//		    bw.close( );
//		    //System.out.println("Done writing to " + fileName); //For testing 
//		 }
//		 catch( IOException e )
//		 {
//		 System.out.println("Error: " + e);
//		 e.printStackTrace( );
//		 }

	}
	/* Load from file the saved 5 operations */

	@Override
	public void load() {
		// TODO Auto-generated method stub

	}

}
