package eg.edu.alexu.csd.oop.jdbc;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.junit.Assert;
import org.junit.Test;

public class TestDistinct {
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
	@Test //
	public void testDistinct() throws SQLException {
		Connection connection = createUseDatabase("Club");
		try {
			Statement statement = connection.createStatement();
			statement.execute("CREATE TABLE Player(name varchar, age int, Game varchar)");
			int count1 = statement.executeUpdate(
					"INSERT INTO Player(name, Game, age) VALUES ('Mohab', 'football', 14)");
			Assert.assertEquals("Insert returned a number != 1", 1, count1);
			boolean result1 = statement.execute(
					"INSERT INTO Player(name, age, Game) VALUES ('saad', 14, 'football')");
			Assert.assertFalse("Wrong return for insert record", result1);
			int count3 = statement.executeUpdate(
					"INSERT INTO Player(name, Game, age) VALUES ('khaled', 'football', 15)");
			Assert.assertEquals("Insert returned a number != 1", 1, count3);
			int count4 = statement.executeUpdate(
					"INSERT INTO Player(name, Game, age) VALUES ('mohamed', 'football', 14)");
			Assert.assertEquals("Insert returned a number != 1", 1, count4);

			boolean result2 = statement.execute("SELECT DISTINCT age FROM Player");
			Assert.assertTrue("Wrong return for select existing records", result2);
			ResultSet res1 = statement.getResultSet();

			int rows = 0;
			while (res1.next())
				rows++;
			Assert.assertEquals("Wrong number of rows", 2, rows);

			boolean result3 = statement.execute("SELECT DISTINCT age, Game FROM Player WHERE age < 15");
			Assert.assertTrue("Wrong return for select existing records", result3);
			ResultSet res2 = statement.getResultSet();

			int rows2 = 0;
			while (res2.next())
				rows2++;
			Assert.assertEquals("Wrong number of rows", 3, rows2);

			statement.close();
		} catch (Throwable e) {
			TestRunner.fail("Failed to select distinct from table", e);
		}
		connection.close();
	}
}
