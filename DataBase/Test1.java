package eg.edu.alexu.csd.oop.DataBase;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Assert;
import org.junit.Test;

public class Test1 {

	
	@Test
	public void select() throws ParserConfigurationException, IOException {
	Queries Query =new Queries();
    Query.createDatabase("test");
    
    String []fields3={"String name","int grade","int id"};
    Query.creatTable("test","table1", fields3);
    
    Assert.assertEquals(true , Query.validateWithDTDUsingDOM("test", "table1"));

    
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
    
    String[][] test =  {{"name", "grade"},{"salma mohamed", "20"}};
    String []condition={"id","=","2"};
    Assert.assertArrayEquals(test , Query.selectColumnsWithCondition("test", "table1", SelectedColumns,
			condition));	
    
    Query.deleteTable("test", "table1");
    String[][] test2 =  {{"name", "grade","id"}};
    Assert.assertArrayEquals(test2 , Query.selectAllColumns("test","table1"));	
    
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

    String []condition2 ={"name","=","aya aly"};

    Query.deleteSubTable("test", "table1", condition2);
    
    String[][] test3 =  {{"name", "grade","id"},{"aya fouad", "10","1"}, {"salma mohamed", "20","2"},
    			{"mona alaa", "40","4"}};

    Assert.assertArrayEquals(test3 , Query.selectAllColumns("test","table1"));
   
String []condition7 ={"name",">","aya aly"};


    Assert.assertArrayEquals(null , Query.selectAllWithCondition("test", "table1", condition7));
    
    String []condition3 ={"id","=","4"};
    String []changes ={"name","=","Another Name"};

    Query.update("test", "table1", condition3, changes);
    String[][] test4 =  {{"name", "grade","id"},{"aya fouad", "10","1"}, {"salma mohamed", "20","2"},
			{"Another Name", "40","4"}};
    
    Assert.assertArrayEquals(test4 , Query.selectAllColumns("test", "table1"));
    
    String []changes2 ={"name","=","Name Changed"};

    Query.updateWhitoutWhere("test", "table1",changes2 );
    
    String[][] test5 =  {{"name", "grade","id"},{"Name Changed", "10","1"}, {"Name Changed", "20","2"},
			{"Name Changed", "40","4"}};

    Assert.assertArrayEquals(test5 , Query.selectAllColumns("test", "table1"));


	}
 
}
	

