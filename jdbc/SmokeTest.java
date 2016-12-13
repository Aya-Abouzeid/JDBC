package eg.edu.alexu.csd.oop.jdbc;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import org.junit.Assert;
import org.junit.Test;

import eg.edu.alexu.csd.TestRunner;

/**
 * Notes: 
 * 
 * 1- Any test with "xmldb" can be replaced with "altdb". 
 * 
 * 2- You can manipulate this file as much as you can, add more tests, change these tests ... etc. 
 * 
 * 3- This file does not cover all the required test cases but that
 * does not mean that these test cases will be forgotten, the SanityTest will
 * have many of them too.
 **/
public class SmokeTest {

	private String protocol = "altdb";
	private String tmp = System.getProperty("java.io.tmpdir");

	public static Class<?> getSpecifications() {
		return Driver.class;
	}

	private Connection createUseDatabase(String databaseName) throws SQLException {
		Driver driver = (Driver) TestRunner.getImplementationInstance();
		Properties info = new Properties();
		File dbDir = new File(tmp + "/jdbc/" + Math.round((((float) Math.random()) * 100000)));
		info.put("path", dbDir.getAbsoluteFile());

		Connection connection = driver.connect("jdbc:" + protocol + "://localhost", info); // Establish
																							// connection
																							// (really,
																							// just
																							// make
																							// sure
		// that the dbDir exists, and create it if it
		// doesn't), and just record the protocol.

		Statement statement = connection.createStatement(); // create a
															// statement object
															// to execute next
															// statements.
		
		statement.execute("CREATE DATABASE " + databaseName); // you should now
																// create a
																// folder for
																// that database
																// within dbDir.
		
		statement.execute("USE " + databaseName); // Set the state of your
													// connection to use
													// "databaseName", all next
													// created statements
		// (like selects and inserts) should be applied to this database.
		statement.close();
		return connection;
	}
	@Test //
	public void testCreateTable() throws SQLException {
		Connection connection = createUseDatabase("TestDB_Create");
		try {
			Statement statement = connection.createStatement();
			statement.execute("CREATE TABLE table_name1(column_name1 varchar, column_name2 int, column_name3 date)");
			statement.close();
		} catch (Throwable e) {
			TestRunner.fail("Failed to create table", e);
		}
		try {
			Statement statement = connection.createStatement();
			statement.execute("CREATE TABLE table_name1(column_name1 varchar, column_name2 int, column_name3 date)");
			Assert.fail("Created existing table successfully!");
		} catch (SQLException e) {

		} catch (Throwable e) {
			TestRunner.fail("Invalid Exception thrown", e);
		}

		try {
			Statement statement = connection.createStatement();
			statement.execute("CREATE TABLE incomplete_table_name1");
			Assert.fail("Create invalid table succeed");
		} catch (SQLException e) {
		} catch (Throwable e) {
			TestRunner.fail("Invalid Exception thrown", e);
		}
		connection.close();
	}
	
}