package eg.edu.alexu.csd.oop.calculator;
/**.
 * interface
 */
public interface Calculator {

	/**.
	 *Take input mathematical expression from user
	 *@param s string
	 **/
	public void input(String s);
	/**. Return the result of the current operations
	 *  or throws a runtime exception
	 *  @return string
	 *  */
	public String getResult();
	/**.
	 *@return string
	 * Return the current formula */
	public String current();
	/** Return the last operation in String format.
	 *  or Null if no more history available */
	public String prev();
	/** Return the next operation in String format.
	 *  or Null if no more history available */
	public String next();
	/**.
	 *  Save the last 5 done Operations in a file*/
	public void save();
	/**.
	 *  Load from file the saved 5 operations */
	public void load();

}
