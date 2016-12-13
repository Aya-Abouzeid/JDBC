package eg.edu.alexu.csd.oop.DBMS;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class TestDelete {

	@Test
	public void testDeleteSubTable() {

		Queries Query =new Queries(System.getProperty("user.home"),"xmldb");
	    Query.createDatabase("test");
	    
	    String []fields3={"String name","int grade","int id"};
	    Query.creatTable("test","table1", fields3);
	    

	    String []FinalValues={"aya fouad","10","1"};
	    Query.insertRow("test","table1", FinalValues);
	    
	    FinalValues[0]="salma mohamed";
	    FinalValues[1]="20";
	    FinalValues[2]="2";
	    Query.insertRow("test","table1", FinalValues);
	    
	    FinalValues[0]="aya aly";
	    FinalValues[1]="30";
	    FinalValues[2]="3";
	    Query.insertRow("test","table1", FinalValues);
	    
	    FinalValues[0]="mona alaa";
	    FinalValues[1]="40";
	    FinalValues[2]="4";
	    Query.insertRow("test","table1", FinalValues);
	    
	    
	
	}

	@Test
	public void testdeleteTable() {

		Queries Query =new Queries(System.getProperty("user.home"),"xmldb");
	    Query.createDatabase("test");
	    
	    String []fields3={"String name","int grade","int id"};
	    Query.creatTable("test","table1", fields3);
	    

	    String []FinalValues={"aya fouad","10","1"};
	    Query.insertRow("test","table1", FinalValues);
	    
	    FinalValues[0]="salma mohamed";
	    FinalValues[1]="20";
	    FinalValues[2]="2";
	    Query.insertRow("test","table1", FinalValues);
	    
	    FinalValues[0]="aya aly";
	    FinalValues[1]="30";
	    FinalValues[2]="3";
	    Query.insertRow("test","table1", FinalValues);
	    
	    FinalValues[0]="mona alaa";
	    FinalValues[1]="40";
	    FinalValues[2]="4";
	    Query.insertRow("test","table1", FinalValues);
	    
	    Query.deleteTable("test", "table1");
	    String[][] test2 =  {{"name", "grade","id"}};
	    Assert.assertArrayEquals(test2 , Query.selectAllColumns("test","table1"));	
	    
	    
	    
	
	}
}
