package eg.edu.alexu.csd.oop.jdbcTests;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.junit.Assert;
import org.junit.Test;

import eg.edu.alexu.csd.oop.jdbc.Driver;
import eg.edu.alexu.csd.oop.jdbc.TestRunner;

public class TestStatement {
	private static String protocol = "xmldb";
	private static String temp = System.getProperty("java.io.tmpdir"); 


	private Connection createUseDatabase(String databaseName) throws SQLException {
		Driver driver = new Driver();
		Properties info = new Properties();
		File dbDir = new File(temp + "/jdbc/" + Math.round((((float) Math.random()) * 100000)));
		info.put("path", dbDir.getAbsoluteFile());

		Connection connection = driver.connect("jdbc:" + protocol + "://localhost", info);
		Statement statement = connection.createStatement(); 	
		statement.execute("CREATE DATABASE " + databaseName); 
		statement.execute("USE " + databaseName); 
		statement.close();
		return connection;
	}
	 @Test 
		public void testStatement() throws SQLException {
			Connection connection = createUseDatabase("Home");
			try {
				Statement statement = connection.createStatement();
				statement.close();
				statement.execute("CREATE TABLE Family(name varchar, age int, weight float)");
				int count = statement.executeUpdate("INSERT INTO Family VALUES ('Ahmed', 10, 1.4)");
			} catch (SQLException e) {
			}
			connection.close();
		}
}
