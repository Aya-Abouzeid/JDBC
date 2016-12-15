package eg.edu.alexu.csd.oop.jdbc;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Log4j {
	
	 Logger logger = Logger.getLogger("Log4j");
	 Properties preferences = new Properties();
     FileHandler handler;  
	public void INFO(String information){
		  try {  
			  handler = new FileHandler("test.txt");  
			  logger.addHandler(handler);
			  SimpleFormatter formatter = new SimpleFormatter();  
			  handler.setFormatter(formatter);  
			  logger.setUseParentHandlers(false);
			  logger.info(information);  
		  } catch (SecurityException e) {  
	            logger.warning("there is error here");;  
		  } catch (IOException e) {  
	            logger.warning("there is error here");;  
		  }  
	}
	public void ERROR(String information){
		  try {  
			  handler = new FileHandler("test.txt");  
			  logger.addHandler(handler);
			  SimpleFormatter formatter = new SimpleFormatter();  
			  handler.setFormatter(formatter);  
			  logger.setUseParentHandlers(false);
			  logger.warning( information);  
		  } catch (SecurityException e) {  
	            logger.warning("there is error here");;  
		  } catch (IOException e) {  
	            logger.warning("there is error here");;  
		  }  
	}
}
