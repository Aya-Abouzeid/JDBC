package eg.edu.alexu.csd.oop.DBMS;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class TestUpdate {

	@Test
	public void testupdate() {
		
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
	    
	    FinalValues[0]="mona alaa";
	    FinalValues[1]="40";
	    FinalValues[2]="4";
	    Query.insertRow("test","table1", FinalValues);
	

	    String []condition3 ={"id","=","4"};
	    String []changes ={"name","=","Another Name"};

	    Query.update("test", "table1", condition3, changes);
	    String[][] test4 =  {{"name", "grade","id"},{"aya fouad", "10","1"}, {"salma mohamed", "20","2"},
				{"Another Name", "40","4"}};
	    
	    Assert.assertArrayEquals(test4 , Query.selectAllColumns("test", "table1"));
	    
	}

	@Test
	public void testupdateWhitoutWhere() {
		
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
	    
	    FinalValues[0]="mona alaa";
	    FinalValues[1]="40";
	    FinalValues[2]="4";
	    Query.insertRow("test","table1", FinalValues);
	}

}
