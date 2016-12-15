package eg.edu.alexu.csd.oop.DBMS;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Assert;
import org.junit.Test;

public class TestDTD {

	@Test
	public void test() throws ParserConfigurationException, IOException {

		Queries Query =new Queries(System.getProperty("user.home"),"xmldb");
	    Query.createDatabase("test");
	    
	    String []fields3={"String name","int grade","int id"};
	    Query.creatTable("test","table1", fields3);
	    
	    Assert.assertEquals(true , Query.validateWithDTDUsingDOM("test", "table1"));

		
		
	}

}
