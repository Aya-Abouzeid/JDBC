package eg.edu.alexu.csd.oop.jdbc;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.junit.Assert;
import org.junit.Test;

public class SmokeTestDeleteJSON {
	private static String protocol = "altdb";
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
	public void testConditionalDelete() throws SQLException {
		Connection connection = createUseDatabase("School");
		try {
			Statement statement = connection.createStatement();
			statement.execute("CREATE TABLE Student(Name varchar, Id int, DateOfBirth DATE)");
			int count1 = statement.executeUpdate(
					"INSERT INTO Student(Name, DateOfBirth, Id) VALUES ('Salma', '2011-01-25', 4)");
			Assert.assertEquals("Insert returned a number != 1", 1, count1);
			int count2 = statement.executeUpdate(
					"INSERT INTO Student(Name, DateOfBirth, Id) VALUES ('Mona', '2013-06-30', 7)");
			Assert.assertEquals("Insert returned a number != 1", 1, count2);
			int count3 = statement.executeUpdate(
					"INSERT INTO Student(Name, DateOfBirth, Id) VALUES ('Esraa', '2013-07-03', 5)");
			Assert.assertEquals("Insert returned a number != 1", 1, count3);
			int count4 = statement.executeUpdate("DELETE From Student  WHERE DateOfBirth>'2011-01-25'");
			Assert.assertEquals("Delete returned wrong number", 2, count4);
			statement.close();
		} catch (Throwable e) {
			TestRunner.fail("Failed to delete from table", e);
		}
		connection.close();
	}
	@Test //
	public void testDelete() throws SQLException {
		Connection connection = createUseDatabase("School");
		try {
			Statement statement = connection.createStatement();
			statement.execute("CREATE TABLE Student(Name varchar, Id int, DateOfBirth date)");
			int count1 = statement.executeUpdate(
					"INSERT INTO Student(Name, DateOfBirth, Id) VALUES ('Salma', '2011-01-25', 4)");
			Assert.assertEquals("Insert returned a number != 1", 1, count1);
			int count2 = statement.executeUpdate(
					"INSERT INTO Student(Name, DateOfBirth, Id) VALUES ('Mona', '2011-01-28', 4)");
			Assert.assertEquals("Insert returned a number != 1", 1, count2);
			int count3 = statement.executeUpdate(
					"INSERT INTO Student(Name, DateOfBirth, Id) VALUES ('Aya', '2011-02-11', 5)");
			Assert.assertEquals("Insert returned a number != 1", 1, count3);
			int count4 = statement.executeUpdate("DELETE From Student");
			Assert.assertEquals("Delete returned wrong number", 3, count4);
			statement.close();
		} catch (Throwable e) {
			TestRunner.fail("Failed to delete from table", e);
		}
		connection.close();
	}

}
