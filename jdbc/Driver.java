package eg.edu.alexu.csd.oop.jdbc;


import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.util.Enumeration;

import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;
import java.util.logging.Logger;
import eg.edu.alexu.csd.oop.DBMS.*;
public class Driver implements java.sql.Driver {
	Properties info = new Properties();
	String path;
	String WriterType;
	Log4j logger = new Log4j();
	@Override
	public boolean acceptsURL(String url) throws SQLException {
		if(url==null){
			logger.INFO().warning("Wrong URL Entered !" +"\n");
         return false;
		}
		Validate b=new Validate();
		url=b.TrimCommand(url);
		url=b.Trim_end(url);
        if(url.equals("jdbc:xmldb://localhost")){
			logger.INFO().info("Using XML backend writer");

         this.WriterType="xmldb";
	     return true;
          }	
         if(url.equals("jdbc:altdb://localhost")){
 			logger.INFO().info("Using JSON backend writer");

        this.WriterType="altdb";
	    return true;
            }

		return false;
	}
protected String getpath(){
	return path;
}
	@Override
	public Connection connect(String url, Properties info) throws SQLException {
		if(acceptsURL(url)){

				path =  info.get("path").toString();
		return new Connection(this , WriterType); 
		}
		else{
 			logger.INFO().warning("Invalid URL entered !");

			throw new  SQLException();
	
		}
	}
 
	@Override
	public int getMajorVersion() {
		// TODO Auto-generated method stub
		logger.INFO().warning("Unsupported Operation !");

		throw new java.lang.UnsupportedOperationException();
	}
 
	@Override
	public int getMinorVersion() {
		// TODO Auto-generated method stub
		logger.INFO().warning("Unsupported Operation !");

		throw new java.lang.UnsupportedOperationException();
	}
 
	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		// TODO Auto-generated method stub
		logger.INFO().warning("Unsupported Operation !");

		throw new java.lang.UnsupportedOperationException();
	}
 
	@Override
	public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) throws SQLException {
		// TODO Auto-generated method stub
 
		//
//		String username = info.getProperty("username");
//		String password = info.getProperty("password");
 
 
 
		return null;
	}
 
	@Override
	public boolean jdbcCompliant() {
		// TODO Auto-generated method stub
			logger.INFO().warning("Unsupported Operation !");

		throw new java.lang.UnsupportedOperationException();
	}
 
}