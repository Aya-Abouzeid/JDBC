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

public class TestUpdate {
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
	public void testUpdate() throws SQLException {
		Connection connection = createUseDatabase("School");
		try {
			Statement statement = connection.createStatement();
			statement.execute("CREATE TABLE lecturer(Name varchar, Id int, subject varchar)");
			int count1 = statement.executeUpdate(
					"INSERT INTO lecturer(Name, subject, Id) VALUES ('Salma', 'Software', 1)");
			Assert.assertEquals("Insert returned a number != 1", 1, count1);
			int count2 = statement.executeUpdate(
					"INSERT INTO lecturer(Name, subject, Id) VALUES ('Aya', 'Maths', 2)");
			Assert.assertEquals("Insert returned a number != 1", 1, count2);
			int count3 = statement.executeUpdate(
					"INSERT INTO lecturer(Name, subject, Id) VALUES ('Mona', 'English', 3)");
			Assert.assertEquals("Insert returned a number != 1", 1, count3);
			int count4 = statement.executeUpdate(
					"UPDATE lecturer SET Name='1111111111', Id=2222222, subject='333333333'");
			Assert.assertEquals("Updated returned wrong number", count1 + count2 + count3, count4);
			statement.close();
		} catch (Throwable e) {
			TestRunner.fail("Failed to update table", e);
		}
		connection.close();
	}

	@Test //
	public void testConditionalUpdate() throws SQLException {
		Connection connection = createUseDatabase("Club");
		try {
			Statement statement = connection.createStatement();
			statement.execute(
					"CREATE TABLE Player(Name varchar, Id int, dateOfBirth date, height float)");

			int count1 = statement.executeUpdate(
					"INSERT INTO Player(Name, dateOfBirth, Id, height) VALUES ('Mona', '2011-01-25', 20, 1.8)");
			Assert.assertEquals("Insert returned a number != 1", 1, count1);

			int count2 = statement.executeUpdate(
					"INSERT INTO Player(Name, dateOfBirth, Id, height) VALUES ('Mona', '2011-01-28', 6, 1.01)");
			Assert.assertEquals("Insert returned a number != 1", 1, count2);

			int count3 = statement.executeUpdate(
					"INSERT INTO Player(Name, dateOfBirth, Id, height) VALUES ('Aya', '2011-02-11', 45, 1.3)");
			Assert.assertEquals("Insert returned a number != 1", 1, count3);

			int count4 = statement.executeUpdate(
					"UPDATE Player SET Id=222222, dateOfBirth='1993-10-03' WHERE Name='Mona'");
			Assert.assertEquals("Updated returned wrong number", count1 + count2, count4);
			statement.close();
		} catch (Throwable e) {
			TestRunner.fail("Failed to update table", e);
		}
		connection.close();
	}

	@Test //
	public void testUpdateEmptyOrInvalidTable() throws SQLException {
		Connection connection = createUseDatabase("Club");
		try {
			Statement statement = connection.createStatement();
			statement.execute("CREATE TABLE Player(name varchar,id int, game varchar)");
			int count = statement.executeUpdate(
					"UPDATE Player SET name='Mona', id=15, game='football'");
			Assert.assertEquals("Updated empty table retruned non-zero count!", 0, count);
			statement.close();
		} catch (Throwable e) {
			TestRunner.fail("Failed to update table", e);
		}

		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate(
					"UPDATE Student SET name='Salma', id=15, game='basketball'");
			Assert.fail("Updated empty table retruned non-zero count!");
			statement.close();
		} catch (SQLException e) {
		} catch (Throwable e) {
			TestRunner.fail("Invalid exception was thrown", e);
		}
		connection.close();
	}

	
}
