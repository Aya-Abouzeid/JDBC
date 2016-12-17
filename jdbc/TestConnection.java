package eg.edu.alexu.csd.oop.jdbc;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.junit.Test;

import eg.edu.alexu.csd.oop.jdbc.Driver;

public class TestConnection {
	private static String protocol = "xmldb";
	private static String temp = System.getProperty("java.io.tmpdir"); 

	public static Class<?> getSpecifications() {
		return Driver.class;
	}
	private Connection createUseDatabase(String databaseName) throws SQLException {
		Driver driver = (Driver) TestRunner.getImplementationInstance();
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
		public void testConnection() throws SQLException {
		 Connection connection = createUseDatabase("Home");
			try {
				connection.close();
				Statement statement = connection.createStatement();
				statement.close();
			} catch (SQLException e) {
			}
		}
}
