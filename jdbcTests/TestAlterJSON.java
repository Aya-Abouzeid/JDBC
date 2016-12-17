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

public class TestAlterJSON {
	private static String protocol = "altdb";
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
	@Test //
	public void testAlterTable() throws SQLException {
		Connection connection = createUseDatabase("Company");
		try {
			Statement statement = connection.createStatement();
			statement
					.execute("CREATE TABLE Employee(name varchar, id int, department varchar)");
			int count1 = statement.executeUpdate(
					"INSERT INTO Employee(name, department, id) VALUES ('salma', 'computer', 1)");
			Assert.assertEquals("Insert returned a number != 1", 1, count1);
			boolean result1 = statement.execute(
					"INSERT INTO Employee(name, id, department) VALUES ('ahmed', 4, 'software')");
			Assert.assertFalse("Wrong return for insert record", result1);
			int count2 = statement.executeUpdate(
					"INSERT INTO Employee(name, department, id) VALUES ('aya', 'algorithem', 5)");
			Assert.assertEquals("Insert returned a number != 1", 1, count2);
			int count3 = statement.executeUpdate(
					"INSERT INTO Employee(name, department, id) VALUES ('mona', 'report', 6)");
			Assert.assertEquals("Insert returned a number != 1", 1, count3);

			boolean result2 = statement.execute("ALTER TABLE Employee ADD assignment date");
			Assert.assertFalse("Wrong return for ALTER TABLE", result2);

			boolean result3 = statement.execute("SELECT assignment FROM Employee WHERE id = 5");
			Assert.assertTrue("Wrong return for select existing records", result3);
			ResultSet res2 = statement.getResultSet();
			int rows2 = 0;
			while (res2.next())
				rows2++;
			Assert.assertEquals("Wrong number of rows", 1, rows2);
			while (res2.previous())
				;
			res2.next();

			Assert.assertNull("Retrieved date is not null", res2.getDate("assignment"));

			statement.close();
		} catch (Throwable e) {
			TestRunner.fail("Failed to test ALTER TABLE from table", e);
		}
		connection.close();
	}

}
