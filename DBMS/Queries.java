
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
	String[]Type;
    protected DtdFile dtdObject;

	public  Queries( String path,String writerType) {
		this.path=path;
		dtdObject = new DtdFile(path);
		this.writerType= writerType;
	}
	
	protected DataBase  currentDataBase;
	protected DataBase  openDataBase;


    private String databasepath = "";
	@Override
	public void createDatabase(String databaseName ) {
		// TODO Auto-generated method stub
		String lowercaseddb = databaseName.toLowerCase();
		currentDataBase = new DataBase(path,writerType);
		
		File dataBaseDirectory = new File(path+ File.separator + lowercaseddb);
		databasepath = path + File.separator + lowercaseddb;
		if (!dataBaseDirectory.exists()) {
			if (!dataBaseDirectory.mkdirs()) {
				dataBaseDirectory.mkdirs();
			}
		} else {
			for(File file: dataBaseDirectory.listFiles()) 
			    if (!file.isDirectory()) {
			        file.delete();
			        }
		}
		System.out.println("Data Base Created successfully");
		
		
	}

	@Override
	public void dropDatabase(String databaseName) {
		// TODO Auto-generated method stub
		String lowercaseddb = databaseName.toLowerCase();
		File file = new File(path+ File.separator + lowercaseddb);
		
			for(File file1: file.listFiles()) 
			    if (!file1.isDirectory()) 
			        file1.delete();
			 file.delete();
			System.out.println("Data Base Dropped successfully");

		
		
	}

	@Override
	public void creatTable(String databaseName, String tableName, String[] properties) {
		// TODO Auto-generated method stub                                                   
		
		currentDataBase.creatTable(databaseName, tableName, properties);
	}

	@Override
	public int insertRow(String databaseName, String tableName, String[] properties) {
		// TODO Auto-generated method stub
		 File dbdirectory = new File (path + File.separator + databaseName);
		 String[] tablesNames=dbdirectory.list();
		 for (int i = 0; i < tablesNames.length; i++) {
				if(tablesNames[i].substring(0, tablesNames[i].indexOf('.')).equalsIgnoreCase(tableName)){
					
					tableName = tablesNames[i].substring(0, tablesNames[i].indexOf('.'));
				}

		}
		return currentDataBase.insertRow(databaseName, tableName, properties);
		
		
	}

	@Override
	public int update(String databaseName, String tableName, String[] condition, String[] updateStatment) {
		// TODO Auto-generated method stub
		 File dbdirectory = new File (path + File.separator + databaseName);
		 String[] tablesNames=dbdirectory.list();
		 for (int i = 0; i < tablesNames.length; i++) {
				if(tablesNames[i].substring(0, tablesNames[i].indexOf('.')).equalsIgnoreCase(tableName)){
					
					tableName = tablesNames[i].substring(0, tablesNames[i].indexOf('.'));
				}

		}
		return currentDataBase.update(databaseName, tableName, condition, updateStatment);
		
	}

	@Override
	public int updateWhitoutWhere(String databaseName, String tableName, String[] updateStatment) {
		// TODO Auto-generated method stub
		 File dbdirectory = new File (path + File.separator + databaseName);
		 String[] tablesNames=dbdirectory.list();
		 for (int i = 0; i < tablesNames.length; i++) {
				if(tablesNames[i].substring(0, tablesNames[i].indexOf('.')).equalsIgnoreCase(tableName)){
					
					tableName = tablesNames[i].substring(0, tablesNames[i].indexOf('.'));
				}

		}
		return currentDataBase.updateWhitoutWhere(databaseName, tableName, updateStatment);
		
	}

	@Override
	public int insertSub(String databaseName, String tableName, String[] columSend, String[] properties) {
		// TODO Auto-generated method stub
		 File dbdirectory = new File (path + File.separator + databaseName);
		 String[] tablesNames=dbdirectory.list();
		 for (int i = 0; i < tablesNames.length; i++) {
				if(tablesNames[i].substring(0, tablesNames[i].indexOf('.')).equalsIgnoreCase(tableName)){
					
					tableName = tablesNames[i].substring(0, tablesNames[i].indexOf('.'));
				}

		}
		return currentDataBase.insertSub(databaseName, tableName, columSend, properties);
		
	}

	@Override
	public int deleteTable(String databaseName, String tableName) {
		// TODO Auto-generated method stub
		 File dbdirectory = new File (path + File.separator + databaseName);
		 String[] tablesNames=dbdirectory.list();
		 for (int i = 0; i < tablesNames.length; i++) {
				if(tablesNames[i].substring(0, tablesNames[i].indexOf('.')).equalsIgnoreCase(tableName)){
					
					tableName = tablesNames[i].substring(0, tablesNames[i].indexOf('.'));
				}

		}
		return currentDataBase.deleteTable(databaseName, tableName);
	
	}

	@Override
	public int deleteSubTable(String databaseName, String tableName, String[] condition) {
		// TODO Auto-generated method stub
		 File dbdirectory = new File (path + File.separator + databaseName);
		 String[] tablesNames=dbdirectory.list();
		 for (int i = 0; i < tablesNames.length; i++) {
				if(tablesNames[i].substring(0, tablesNames[i].indexOf('.')).equalsIgnoreCase(tableName)){
					
					tableName = tablesNames[i].substring(0, tablesNames[i].indexOf('.'));
				}

		}
		return currentDataBase.deleteSubTable(databaseName, tableName, condition);
	
		
	}

	@Override
	public String[][] selectColumnsWithCondition(String databaseName, String tableName, String[] columntitles,
			String[] Condition) {
		// TODO Auto-generated method stub;
		Type=currentDataBase.Type;
		 File dbdirectory = new File (path + File.separator + databaseName);
		 String[] tablesNames=dbdirectory.list();
		 for (int i = 0; i < tablesNames.length; i++) {
				if(tablesNames[i].substring(0, tablesNames[i].indexOf('.')).equalsIgnoreCase(tableName)){
					
					tableName = tablesNames[i].substring(0, tablesNames[i].indexOf('.'));
				}

		}
		return currentDataBase.selectColumnsWithCondition(databaseName, tableName, columntitles, Condition);
		
	}

	@Override
	public String[][] selectColumns(String databaseName, String tableName, String[] columntitles) {
		// TODO Auto-generated method stub
		Type=currentDataBase.Type;
		 File dbdirectory = new File (path + File.separator + databaseName);
		 String[] tablesNames=dbdirectory.list();
		 for (int i = 0; i < tablesNames.length; i++) {
				if(tablesNames[i].substring(0, tablesNames[i].indexOf('.')).equalsIgnoreCase(tableName)){
					
					tableName = tablesNames[i].substring(0, tablesNames[i].indexOf('.'));
				}

		}
	  return currentDataBase.selectColumns(databaseName, tableName, columntitles);
	}

	@Override
	public String[][] selectAllColumns(String databaseName, String tableName) {
		// TODO Auto-generated method stub
		Type=currentDataBase.Type;
		 File dbdirectory = new File (path + File.separator + databaseName);
		 String[] tablesNames=dbdirectory.list();
		 for (int i = 0; i < tablesNames.length; i++) {
				if(tablesNames[i].substring(0, tablesNames[i].indexOf('.')).equalsIgnoreCase(tableName)){
					
					tableName = tablesNames[i].substring(0, tablesNames[i].indexOf('.'));
				}

		}
		return  currentDataBase.selectAllColumns(databaseName, tableName);
	}

	@Override
	public String[][] selectAllWithCondition(String databaseName, String tableName, String[] Condition) {
		// TODO Auto-generated method stub
		Type=currentDataBase.Type;
		 File dbdirectory = new File (path + File.separator + databaseName);
		 String[] tablesNames=dbdirectory.list();
		 for (int i = 0; i < tablesNames.length; i++) {
				if(tablesNames[i].substring(0, tablesNames[i].indexOf('.')).equalsIgnoreCase(tableName)){
					
					tableName = tablesNames[i].substring(0, tablesNames[i].indexOf('.'));
				}

		}
		return  currentDataBase.selectAllWithCondition(databaseName, tableName, Condition);
	}

	@Override
	public void dropTable(String databaseName, String tableName) {
		// TODO Auto-generated method stub
		 File dbdirectory = new File (path + File.separator + databaseName);
		 String[] tablesNames=dbdirectory.list();
		 for (int i = 0; i < tablesNames.length; i++) {
				if(tablesNames[i].substring(0, tablesNames[i].indexOf('.')).equalsIgnoreCase(tableName)){
					
					tableName = tablesNames[i].substring(0, tablesNames[i].indexOf('.'));
				}

		}
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
		 File dbdirectory = new File (path + File.separator + databaseName);
		 String[] tablesNames=dbdirectory.list();
		 for (int i = 0; i < tablesNames.length; i++) {
				if(tablesNames[i].substring(0, tablesNames[i].indexOf('.')).equalsIgnoreCase(tableName)){
					
					tableName = tablesNames[i].substring(0, tablesNames[i].indexOf('.'));
				}

		}
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
		 File dbdirectory = new File (path + File.separator + databaseName);
		 String[] tablesNames=dbdirectory.list();
		 for (int i = 0; i < tablesNames.length; i++) {
				if(tablesNames[i].substring(0, tablesNames[i].indexOf('.')).equalsIgnoreCase(tableName)){
					
					tableName = tablesNames[i].substring(0, tablesNames[i].indexOf('.'));
				}

		}
		currentDataBase.addAlter(databaseName, tableName, type, columName);
		return 0;
	}

	@Override
	public int deleteAlter(String databaseName, String tableName, String columName) {
		// TODO Auto-generated method stub
		 File dbdirectory = new File (path + File.separator + databaseName);
		 String[] tablesNames=dbdirectory.list();
		 for (int i = 0; i < tablesNames.length; i++) {
				if(tablesNames[i].substring(0, tablesNames[i].indexOf('.')).equalsIgnoreCase(tableName)){
					
					tableName = tablesNames[i].substring(0, tablesNames[i].indexOf('.'));
				}

		}
		currentDataBase.deleteAlter(databaseName, tableName, columName);
		return 0;
	}

	@Override
	public String[][] distinct(String databaseName, String tableName, String[] columsName) {
		// TODO Auto-generated method stub
		 File dbdirectory = new File (path + File.separator + databaseName);
		 String[] tablesNames=dbdirectory.list();
		 for (int i = 0; i < tablesNames.length; i++) {
				if(tablesNames[i].substring(0, tablesNames[i].indexOf('.')).equalsIgnoreCase(tableName)){
					
					tableName = tablesNames[i].substring(0, tablesNames[i].indexOf('.'));
				}

		}
		return currentDataBase.distinct(databaseName, tableName, columsName);
	}
	
	@Override
	public boolean DetectDataBase(String name) {
		String lowercaseddb = name.toLowerCase();

		File file = new File(path+ File.separator + lowercaseddb);
		currentDataBase = new DataBase(path, writerType);
		if (file.exists()){
			return true;
			}
		return false;
	}

}