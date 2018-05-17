package eg.edu.alexu.csd.oop.DBMS;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class TestInsert {

	@Test
	public void testinsertRow() {

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
	    
	    String[][] selected =  {{"name", "grade"},{"aya fouad", "10"}, {"salma mohamed", "20"},
	   		{"aya aly", "30"} , {"mona alaa", "40"}};
	    String[] SelectedColumns = {"name","grade"};
	   

	    Assert.assertArrayEquals(selected , Query.selectColumns("test","table1",SelectedColumns));	
	    
	}
	
	public void testinsertSub() {
		
		Queries Query =new Queries(System.getProperty("user.home"),"xmldb");
	    Query.createDatabase("test");
	    
	    String []fields3={"String name","int grade","int id"};
	    Query.creatTable("test","table1", fields3);
	    
		String []FinalValues = new String[3];
		FinalValues[0]="aya fouad";
	    FinalValues[1]="10";
	    FinalValues[2]="1";
	    String[] Columns ={"name","grade","id"};
	    Query.insertSub("test", "table1", Columns, FinalValues);
	    
	    FinalValues[0]="salma mohamed";
	    FinalValues[1]="20";
	    FinalValues[2]="2";
	    Query.insertSub("test", "table1", Columns, FinalValues);
	    
	    FinalValues[0]="aya aly";
	    FinalValues[1]="30";
	    FinalValues[2]="3";
	    Query.insertSub("test", "table1", Columns, FinalValues);
	    
	    FinalValues[0]="mona alaa";
	    FinalValues[1]="40";
	    FinalValues[2]="4";
	    Query.insertSub("test", "table1", Columns, FinalValues);
	    
	    
	    String[][] selected =  {{"name", "grade"},{"aya fouad", "10"}, {"salma mohamed", "20"},
	   		{"aya aly", "30"} , {"mona alaa", "40"}};
	    String[] SelectedColumns = {"name","grade"};
	   

	    Assert.assertArrayEquals(selected , Query.selectColumns("test","table1",SelectedColumns));	
	    
	}
}
