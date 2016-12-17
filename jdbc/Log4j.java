package eg.edu.alexu.csd.oop.jdbc;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;

public class Log4j {

	 Properties preferences = new Properties();
     FileHandler handler;
	public Logger INFO(){
		Logger logger = Logger.getLogger("Log4j");
		  try {
			  handler = new FileHandler("test.txt");  
			  logger.addHandler(handler);
			  SimpleFormatter formatter = new SimpleFormatter();  
			  handler.setFormatter(formatter);  
			  logger.setUseParentHandlers(false);
		  } catch (SecurityException e) {  
	            logger.warning("there is error here");;  
		  } catch (IOException e) {  
	            logger.warning("there is error here");;  
		  }
		return logger;  
	}
	public Logger ERROR(){
		Logger logger = Logger.getLogger("Log4j");
		  try {
			  handler = new FileHandler("test.txt");  
			  logger.addHandler(handler);
			  SimpleFormatter formatter = new SimpleFormatter();  
			  handler.setFormatter(formatter);  
			  logger.setUseParentHandlers(false);
		  } catch (SecurityException e) {  
	            logger.warning("there is error here");;  
		  } catch (IOException e) {  
	            logger.warning("there is error here");;  
		  }
		return logger;  
	}
	
}