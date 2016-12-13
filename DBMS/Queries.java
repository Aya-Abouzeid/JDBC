
package eg.edu.alexu.csd.oop.DBMS;

import java.awt.List;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.management.RuntimeErrorException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.io.FileUtils;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

//import paintProject.Circle;

public class Queries  implements DataBaseInterface  {
	
    //protected DataBase  currentDataBase= new DataBase("kdfojanfj", "kjlkajewijf");
	String path;
	String writerType;
	public  Queries( String path,String writerType) {
		this.path=path;
		this.writerType= writerType;
	}
	
	protected DataBase  currentDataBase;
	protected DataBase  openDataBase;
    protected DtdFile dtdObject = new DtdFile(path);
    /*protected Table tableObject = new Table();
    protected XmlInsert xmlInsertObject = new XmlInsert();
    protected XmlSelect xmlSelectObject = new XmlSelect();
    protected XmlDelete xmlDeleteObject = new XmlDelete();
    protected XmlUpdate xmlUpdateObject = new XmlUpdate();
    protecte d DtdFile dtdObject = new DtdFile();*/
  /*public static void main(String[] args) {
		// TODO Auto-generated method stub
    	 //Queries salma = new Queries();
    	 salma.createDatabase("toka","xmldb");
    	 String[]properties ={"varchar name","int id"};
    	 salma.creatTable("toka", "select1", properties);
    	 String[]value={"nkjo[o","55"};
    	 String[]columSend ={"id"};
    	 String[]condition={"id",">","0"};
    	 String[] values={"55"};
    	 String[]columsName={"id","name"};
    	 String[]updateStatment={"id","=","88","name","=","karima"};
    	 salma.insertRow("toka", "select1", value);
    	 salma.insertRow("toka", "select1", value);
    	 salma.insertSub("toka", "select1", columSend, values);
    	 salma.addAlter("toka", "select1", "int", "saloka");
    	 String[][]select=salma.distinct("toka", "select1", columsName);
    	 //salma.deleteAlter("toka", "select1", "nour");
    	 //salma.updateWhitoutWhere("toka", "select1", updateStatment);
    	 //salma.deleteTable("toka", "select1");
    	 //salma.deleteSubTable("toka", "select1", condition);
    	 //String[]columntitles={"id","name"};
    	 //String[][]select =salma.selectColumnsWithCondition("toka", "select1",columntitles ,condition);
    	 //String[][]select =salma.selectAllWithCondition("toka", "select1" ,condition);
    	for (int i = 0; i < select.length; i++) {
			for (int j = 0; j < select[0].length; j++) {
				//System.out.println(select[i][j]);
			}
		}
    }*/


    private String databasepath = "";
	@Override
	public void createDatabase(String databaseName ) {
		// TODO Auto-generated method stub
		currentDataBase = new DataBase(databaseName,writerType);
		File dataBaseDirectory = new File(path+ File.separator + databaseName);
		databasepath = path + File.separator + databaseName;
		if (!dataBaseDirectory.exists()) {
			if (!dataBaseDirectory.mkdirs()) {
				dataBaseDirectory.mkdirs();
			}
		} else {
			try {
				FileUtils.cleanDirectory(dataBaseDirectory);
			} catch (IOException e) {
				System.out.println("Invalid Database.");
			}
		}
		System.out.println("Data Base Created successfully");
		
		
	}

	@Override
	public void dropDatabase(String databaseName) {
		// TODO Auto-generated method stub
		File file = new File(path+ File.separator + databaseName);
		try {
			FileUtils.deleteDirectory(file);
			System.out.println("Data Base Dropped successfully");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void creatTable(String databaseName, String tableName, String[] properties) {
		// TODO Auto-generated method stub                                                   
		
		currentDataBase.creatTable(databaseName, tableName, properties);
	}

	@Override
	public int insertRow(String databaseName, String tableName, String[] properties) {
		// TODO Auto-generated method stub
		return currentDataBase.insertRow(databaseName, tableName, properties);
		
		
	}

	@Override
	public int update(String databaseName, String tableName, String[] condition, String[] updateStatment) {
		// TODO Auto-generated method stub
		return currentDataBase.update(databaseName, tableName, condition, updateStatment);
		
	}

	@Override
	public int updateWhitoutWhere(String databaseName, String tableName, String[] updateStatment) {
		// TODO Auto-generated method stub
		return currentDataBase.updateWhitoutWhere(databaseName, tableName, updateStatment);
		
	}

	@Override
	public int insertSub(String databaseName, String tableName, String[] columSend, String[] properties) {
		// TODO Auto-generated method stub
		return currentDataBase.insertSub(databaseName, tableName, columSend, properties);
		
	}

	@Override
	public int deleteTable(String databaseName, String tableName) {
		// TODO Auto-generated method stub
		return currentDataBase.deleteTable(databaseName, tableName);
	
	}

	@Override
	public int deleteSubTable(String databaseName, String tableName, String[] condition) {
		// TODO Auto-generated method stub
		return currentDataBase.deleteSubTable(databaseName, tableName, condition);
	
		
	}

	@Override
	public String[][] selectColumnsWithCondition(String databaseName, String tableName, String[] columntitles,
			String[] Condition) {
		// TODO Auto-generated method stub;
		return currentDataBase.selectColumnsWithCondition(databaseName, tableName, columntitles, Condition);
		
	}

	@Override
	public String[][] selectColumns(String databaseName, String tableName, String[] columntitles) {
		// TODO Auto-generated method stub
	  return currentDataBase.selectColumns(databaseName, tableName, columntitles);
	}

	@Override
	public String[][] selectAllColumns(String databaseName, String tableName) {
		// TODO Auto-generated method stub
		return  currentDataBase.selectAllColumns(databaseName, tableName);
	}

	@Override
	public String[][] selectAllWithCondition(String databaseName, String tableName, String[] Condition) {
		// TODO Auto-generated method stub
		return  currentDataBase.selectAllWithCondition(databaseName, tableName, Condition);
	}

	@Override
	public void dropTable(String databaseName, String tableName) {
		// TODO Auto-generated method stub
		currentDataBase.dropTable(databaseName, tableName);
	}

	@Override
	public void CreateDtDFile(String databaseName, String tableName, String[] dtd, String[] type) {
		// TODO Auto-generated method stub
		//dtdObject.CreateDtDFile(databaseName, tableName, dtd, type);	                 monaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
	}

	@Override
	public boolean validateWithDTDUsingDOM(String databaseName, String tableName)
			throws ParserConfigurationException, IOException {
		// TODO Auto-generated method stub
		return dtdObject.validateWithDTDUsingDOM(databaseName, tableName);
	}

	@Override
	public void ListTables(String DataBase) {
		// TODO Auto-generated method stub
		currentDataBase.ListTables(DataBase);
	}

	@Override
	public int addAlter(String databaseName, String tableName, String type, String columName) {
		// TODO Auto-generated method stub
		currentDataBase.addAlter(databaseName, tableName, type, columName);
		return 0;
	}

	@Override
	public int deleteAlter(String databaseName, String tableName, String columName) {
		// TODO Auto-generated method stub
		currentDataBase.deleteAlter(databaseName, tableName, columName);
		return 0;
	}

	@Override
	public String[][] distinct(String databaseName, String tableName, String[] columsName) {
		// TODO Auto-generated method stub
		return currentDataBase.distinct(databaseName, tableName, columsName);
	}
	@Override
	public boolean DetectDataBase(String name) {
		File file = new File(path+ File.separator + name);
		currentDataBase = new DataBase(path, writerType);
		if (file.exists()){
			return true;
			}
		return false;
	}

}