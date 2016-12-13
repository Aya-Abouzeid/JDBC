package eg.edu.alexu.csd.oop.jdbc;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			Class.forName("eg.edu.alexu.csd.oop.JDBC.Driver");
		} catch (java.lang.ClassNotFoundException e) {
			System.err.print("ClassNotFoundException: ");
			System.err.println(e.getMessage());
			System.exit(1);
		} // Contains Database name.
		String url = "jdbc:mySubprotocol:myDataSource";
		Connection con;
		Statement stmt;
		String createString = "create table COFFEES " + "(COF_NAME VARCHAR(32), " + "SUP_ID INTEGER, " + "PRICE FLOAT, "
				+ "SALES INTEGER, " + "TOTAL INTEGER)";
		try {
			con = DriverManager.getConnection(url, "myLogin", "myPassword");
			stmt = con.createStatement();
			stmt.executeUpdate(createString); // Execute SQL queries.
			stmt.close();
			con.close();
		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
		}
	}
}
