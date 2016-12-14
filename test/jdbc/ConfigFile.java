package eg.edu.alexu.csd.oop.test.jdbc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class ConfigFile {
	public static void main(String[] arg0){
		ConfigFile mona = new ConfigFile();
	}
	public void configurationFile(String databaseName,String tableName ,String value, String UserName , String Password){
		try {
			File configFile = new File("mona");
			Properties mainObject = new Properties();
			mainObject.setProperty("database", databaseName);
			mainObject.setProperty("table", tableName);
			mainObject.setProperty("WritingFormat", value);
			mainObject.setProperty("username", UserName);
			mainObject.setProperty("password", Password);
			FileWriter write = new FileWriter(configFile);
			mainObject.store(write, "xml file");
			write.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void loadFile(String databaseName,String tableName){
		Properties prop = new Properties();
		InputStream input = null;
		try {
			input = new FileInputStream("config.properties");
			// load a properties file
			prop.load(input);
			// get the property value and print it out
			System.out.println(prop.getProperty("database"));
			System.out.println(prop.getProperty("username"));
			System.out.println(prop.getProperty("password"));
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
