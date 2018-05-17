package eg.edu.alexu.csd.oop.DBMS;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class TestSelect {

	@Test
	public void testselectColumns() {


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
	@Test
	public void testselectColumnsWithCondition() {


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
	    
	    String[] SelectedColumns = {"name","grade"};

	    String[][] test =  {{"name", "grade"},{"salma mohamed", "20"}};
	    String []condition={"id","=","2"};
	    Assert.assertArrayEquals(test , Query.selectColumnsWithCondition("test", "table1", SelectedColumns,
				condition));
	}

	@Test
	public void testselectAllColumns() {


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
	    
	    String[][] selected =  {{"name", "grade","id"},{"aya fouad", "10","1"},
	    		{"salma mohamed", "20","2"},
	   		{"aya aly", "30","3"} , {"mona alaa", "40","4"}};
	   

	    Assert.assertArrayEquals(selected , Query.selectAllColumns("test","table1"));	
	    
	}
	
	@Test
	public void testselectAllWithCondition() {


		Queries Query =new Queries(System.getProperty("user.home"),"xmldb");
	    Query.createDatabase("test");
	    
	    String []fields3={"String name","int grade","int id"};
	    Query.creatTable("test","table1", fields3);
	    

	   
	    String[] SelectedColumns = {"name","grade"};

	    
	    String []condition7 ={"name",">","aya aly"};


	    Assert.assertArrayEquals(null , Query.selectAllWithCondition("test", "table1", condition7));
	    
	}
}
