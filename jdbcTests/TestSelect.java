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

public class TestSelect {
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
	@Test //
	public void testConditionalSelectAll() throws SQLException {
		Connection connection = createUseDatabase("Club");
		try {
			Statement statement = connection.createStatement();
			statement
					.execute("CREATE TABLE Player(name varchar, age int, Game varchar)");
			int count1 = statement.executeUpdate(
					"INSERT INTO Player(name, Game, age) VALUES ('Mona', 'football', 14)");
			Assert.assertEquals("Insert returned a number != 1", 1, count1);
			int count2 = statement.executeUpdate(
					"INSERT INTO Player(name, age, Game) VALUES ('Ahmed', 14, 'basketball')");
			Assert.assertEquals("Insert returned a number != 1", 1, count2);
			int count3 = statement.executeUpdate(
					"INSERT INTO Player(name, Game, age) VALUES ('Omer', 'handball', 15)");
			Assert.assertEquals("Insert returned a number != 1", 1, count3);
			int count4 = statement.executeUpdate(
					"INSERT INTO Player(name, Game, age) VALUES ('Ali', 'handball', 16)");
			Assert.assertEquals("Insert returned a number != 1", 1, count4);
			ResultSet result = statement.executeQuery("SELECT * FROM Player WHERE age < 15");
			int rows = 0;
			while (result.next())
				rows++;
			Assert.assertNotNull("Null result retruned", result);
			Assert.assertEquals("Wrong number of rows", 2, rows);
			Assert.assertEquals("Wrong number of columns", 3, result.getMetaData().getColumnCount());
			statement.close();
		} catch (Throwable e) {
			TestRunner.fail("Failed to select from table", e);
		}
		connection.close();
	}
	@Test //
	public void testSelectColumnsWithCondition() throws SQLException {
		Connection connection = createUseDatabase("School");
		try {
			Statement statement = connection.createStatement();
			statement.execute("CREATE TABLE Student(subject varchar, Totalgrade int, lecturer varchar)");
			int count1 = statement.executeUpdate(
					"INSERT INTO Student(subject, lecturer, Totalgrade) VALUES ('Ahmed', 'Mohamed', 40)");
			Assert.assertEquals("Insert returned a number != 1", 1, count1);
			boolean result1 = statement.execute(
					"INSERT INTO Student(subject, Totalgrade ,lecturer) VALUES ('Mona', 80, 'Mohamed')");
			Assert.assertFalse("Wrong return from 'execute' for insert record", result1);
			int count3 = statement.executeUpdate(
					"INSERT INTO Student(subject, lecturer, Totalgrade) VALUES ('Esraa', 'Soheir', 50)");
			Assert.assertEquals("Insert returned a number != 1", 1, count3);
			int count4 = statement.executeUpdate(
					"INSERT INTO Student(subject, lecturer, Totalgrade) VALUES ('Mohamed', 'sahar', 60)");
			Assert.assertEquals("Insert returned a number != 1", 1, count4);

			boolean result2 = statement.execute("SELECT subject FROM Student WHERE Totalgrade = 80");
			Assert.assertTrue("Wrong return for select existing records", result2);

			boolean result3 = statement.execute("SELECT subject FROM Student WHERE Totalgrade > 100");
			Assert.assertFalse("Wrong return for select non existing records", result3);

			statement.close();
		} catch (Throwable e) {
			TestRunner.fail("Failed to select from table", e);
		}
		connection.close();
	}
	@Test //
	public void testSelectColumnsWithOutCondition() throws SQLException {
		Connection connection = createUseDatabase("School");
		try {
			Statement statement = connection.createStatement();
			statement.execute("CREATE TABLE Student(subject varchar, Totalgrade int, lecturer varchar)");
			int count1 = statement.executeUpdate(
					"INSERT INTO Student(subject, lecturer, Totalgrade) VALUES ('Ahmed', 'Mohamed', 40)");
			Assert.assertEquals("Insert returned a number != 1", 1, count1);
			boolean result1 = statement.execute(
					"INSERT INTO Student(subject, Totalgrade ,lecturer) VALUES ('Mona', 80, 'Mohamed')");
			Assert.assertFalse("Wrong return from 'execute' for insert record", result1);
			int count3 = statement.executeUpdate(
					"INSERT INTO Student(subject, lecturer, Totalgrade) VALUES ('Esraa', 'Soheir', 50)");
			Assert.assertEquals("Insert returned a number != 1", 1, count3);
			int count4 = statement.executeUpdate(
					"INSERT INTO Student(subject, lecturer, Totalgrade) VALUES ('Mohamed', 'sahar', 60)");
			Assert.assertEquals("Insert returned a number != 1", 1, count4);

			boolean result2 = statement.execute("SELECT subject FROM Student");
			Assert.assertTrue("Wrong return for select existing records", result2);

			boolean result3 = statement.execute("SELECT name FROM Student");
			Assert.assertFalse("Wrong return for select non existing records", result3);

			statement.close();
		} catch (Throwable e) {
			TestRunner.fail("Failed to select from table", e);
		}
		connection.close();
	}
	@Test //
	public void testSelectAll() throws SQLException {
		Connection connection = createUseDatabase("School");
		try {
			Statement statement = connection.createStatement();
			statement.execute("CREATE TABLE Student(subject varchar, Totalgrade int, lecturer varchar)");
			int count1 = statement.executeUpdate(
					"INSERT INTO Student(subject, lecturer, Totalgrade) VALUES ('Ahmed', 'Mohamed', 4)");
			Assert.assertEquals("Insert returned a number != 1", 1, count1);
			int count2 = statement.executeUpdate(
					"INSERT INTO Student(subject, lecturer, Totalgrade) VALUES ('Mona', 'Mohamed', 4)");
			Assert.assertEquals("Insert returned a number != 1", 1, count2);
			int count3 = statement.executeUpdate(
					"INSERT INTO Student(subject, lecturer, Totalgrade) VALUES ('Salma', 'Soheir', 5)");
			Assert.assertEquals("Insert returned a number != 1", 1, count3);
			int count4 = statement.executeUpdate(
					"INSERT INTO Student(subject, lecturer, Totalgrade) VALUES ('Aya', 'Basem', 6)");
			Assert.assertEquals("Insert returned a number != 1", 1, count4);
			ResultSet result = statement.executeQuery("SELECT * From Student");
			int rows = 0;
			while (result.next())
				rows++;
			Assert.assertNotNull("Null result retruned", result);
			Assert.assertEquals("Wrong number of rows", 4, rows);
			Assert.assertEquals("Wrong number of columns", 3, result.getMetaData().getColumnCount());
			statement.close();
		} catch (Throwable e) {
			TestRunner.fail("Failed to select from table", e);
		}
		connection.close();
	}
}
