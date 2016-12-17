package eg.edu.alexu.csd.oop.jdbc;

import java.sql.Statement;
import java.util.Properties;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub

		java.sql.Driver driver = new Driver();
		String url = "jdbc:xmldb://localhost";
		Connection con;
		Statement stmt;
		String createString = "create table ";
		Properties prop = new Properties();
		prop.setProperty("path", System.getProperty("user.home") + "//databases");
		con = driver.connect(url, prop);
		stmt = con.createStatement();
		stmt.executeUpdate(createString); // Execute SQL queries.
		stmt.close();
		con.close();

	}
}
