package eg.edu.alexu.csd.oop.DBMS;

import java.io.BufferedWriter;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlTable implements ITable {
	 protected String[] ArrayOfTypes;
	 public String[]Type;
	 protected String[] headers;
	 /*protected  DocumentBuilderFactory documentBuilderFactory;
	 protected DocumentBuilder documentBuilder;
	 protected Document document;
	 protected BufferedWriter fileWriter;
	 protected int indexOfTable = 0;*/
	 private Titles titles= new Titles();
	 private xml xmlObject;
	 private String path;
	 private DtdFile dtdObject;

	 public  XmlTable(String path) {
		this.path = path;
		dtdObject = new DtdFile(path);
		System.out.println("XMLTable: " + path);
		xmlObject= new xml(this.path); 

	}
	  public String[] getType(){
		  return this.Type;
	  }
	  
	 private EngineDelete  deleteObject = new EngineDelete();
	 private EngineInsert  insetObject = new EngineInsert();
	 private EngineSelect  SelectObject =new EngineSelect();
	 private EngineUpdate  UpdatObject = new EngineUpdate();
	 private EngineAlter   alterObject = new EngineAlter();
	 private EngineDistinct distinctObject = new EngineDistinct();
	 private ArrayList<ArrayList<String>>  working ;
	 @Override
	 public void creatTable( String databaseName,String tableName , String[] properties) {
			if(xmlObject.fileMinimizeBoolean(databaseName,tableName)){return ;} 
		    Element tableNameElement = xmlObject.document.createElement(tableName);
			Element rows = xmlObject.document.createElement(tableName);
			Attr numberOfrows =xmlObject. document.createAttribute("numberOfRows");
			numberOfrows.setValue("0");
			tableNameElement.setAttributeNode(numberOfrows);
			String[]type =new String[properties.length] ;
			String[]dtd =new String[properties.length] ;
			for (int i = 0; i < properties.length; i++) {
				String[] str = properties[i].split(" ");
				if (str[0].equalsIgnoreCase("int")) {
					
				}else if (str[0].equalsIgnoreCase("varchar")) {
					
				}else if (str[0].equalsIgnoreCase("float")) {
					
				}else if (str[0].equalsIgnoreCase("date")) {
					
				}else{
					//throw new SQLException();
				}
				Node column = xmlObject.document.createElement(str[0]);
				dtd[i]=str[1];
				type[i]=str[0];
				column.appendChild(xmlObject.document.createTextNode(str[1]));
				rows.appendChild(column);}
			tableNameElement.appendChild(rows);
			xmlObject.document.appendChild(tableNameElement);
			xmlObject.document.normalize();
			xmlObject.transform(xmlObject.document,databaseName,tableName);
			dtdObject.CreateDtDFile(databaseName, tableName,dtd,type);		
		}
	 ArrayList<ArrayList<String>> tableData;
	 @Override
	public ArrayList<ArrayList<String>> readFile(String databaseName , String tableName){
		File tables = new File(path + File.separator + databaseName+File.separator+tableName+".xml");
		if (xmlObject.fileMinimizeBolean(tables,databaseName,tableName)){return null;}
		Element root = xmlObject.document.getDocumentElement();
		NodeList roots = root.getElementsByTagName(tableName);
		 tableData = new ArrayList<ArrayList<String>>();
		String[] Titles = titles.columTitles(root);
		headers=titles.columTitles(root);
		ArrayOfTypes = titles.columType(root, Titles);
		ArrayList<String> tableRows;
		ArrayList<String> tableColumsName= new ArrayList<String>();
		for (int i = 0; i < Titles.length; i++) {
			tableColumsName.add(Titles[i]);
		}
		tableData.add(tableColumsName);
		for(int i = 1;i<roots.getLength();i++){
			tableRows = new ArrayList<String>();
			NodeList rowValues = roots.item(i).getChildNodes();
			for(int y = 0; y < rowValues.getLength(); y++){
				if(!(rowValues.item(y).getNodeName().equals("#text"))){
					tableRows.add(rowValues.item(y).getTextContent());
				}
			}
			tableData.add(tableRows);
		}
	return tableData; 
 }
	 @Override
		public void dropTable(String databaseName, String TableName) {
			File table = new File(path + File.separator + databaseName+File.separator+TableName+".xml");
			File dtd = new File(path + File.separator + databaseName+File.separator+TableName+".dtd");

			if (xmlObject.DetectDataBase(databaseName)&& table.exists()) {
				table.delete();
				dtd.delete();
			}else{
				System.out.println("Invalid command.");
			}
		}
	 @Override
 public void writeFile(String databaseName , String tableName , ArrayList<ArrayList<String>> tableData){
		File tables = new File(path + File.separator + databaseName+File.separator+tableName+".xml");
		dropTable(databaseName, tableName);
		if (xmlObject.fileMinimizeBoolean(databaseName,tableName)){return ;}
		    Element tableFile = xmlObject.document.createElement(tableName);
		    Element row = xmlObject. document.createElement(tableName);
		    String[]dtd=new String[tableData.get(0).size()];
		    for(int j = 0; j < tableData.get(0).size(); j++){
				Node tablerownodes =xmlObject.document.createElement(ArrayOfTypes[j]);
				tablerownodes.appendChild(xmlObject.document.createTextNode(tableData.get(0).get(j)));
				row.appendChild(tablerownodes);
				dtd[j]=tableData.get(0).get(j);
			}
		    int u = 0;
		    tableFile.appendChild(row);
			for(int i = 1; i < tableData.size();i++){
				Element tablerow = xmlObject.document.createElement(tableName);
			    for(int j = 0; j < tableData.get(0).size(); j++){
					Node tablerownodes = xmlObject.document.createElement(tableData.get(0).get(j));
					tablerownodes.appendChild(xmlObject.document.createTextNode(tableData.get(i).get(j)));
					tablerow.appendChild(tablerownodes);
				}
			    tableFile.appendChild(tablerow);                                    /////////////////////////error         done 
			    u++;
			}
			Attr numberOfrows = xmlObject.document.createAttribute("numberOfRows");
			numberOfrows.setValue(Integer.toString(u));
			tableFile.setAttributeNode(numberOfrows);
			xmlObject.document.appendChild(tableFile);
			xmlObject.document.normalize();
			xmlObject.transform(xmlObject.document, databaseName, tableName);
			dtdObject.CreateDtDFile(databaseName, tableName,dtd,ArrayOfTypes);	
			tableData=new  ArrayList<ArrayList<String>>();
			
 }
	
	public void  check( String databaseName ,String tableName) {
		File tables = new File(path + File.separator + databaseName+File.separator+tableName+".xml");
		 if (xmlObject.fileMinimizeBolean(tables,databaseName,tableName)){
			 return;
		 }

		
	}
	@Override
	public int insertRow(String databaseName, String tableName, String[] properties) {
		// TODO Auto-generated method stub
		working = readFile(databaseName, tableName);
		tableData=insetObject.insertRow(working, properties, ArrayOfTypes,headers);
         writeFile(databaseName, tableName,tableData);
		
		return insetObject.getCounter();
	}
	@Override
	public int insertSub(String databaseName, String tableName, String[] columSend, String[] properties) {
		// TODO Auto-generated method stub
		working = readFile(databaseName, tableName);
		tableData=insetObject.insertSub(working,columSend, properties, ArrayOfTypes,headers);
	
        writeFile(databaseName, tableName,tableData);
		
		return insetObject.getCounter();
	}
	@Override
	public int deleteTable(String databaseName, String tableName) {
		// TODO Auto-generated method stub
		working = readFile(databaseName, tableName);
		tableData= new ArrayList<ArrayList<String>>();
		tableData= deleteObject.deleteTable(working,headers);
		
		 writeFile(databaseName, tableName,tableData);
		return deleteObject.getCounter();
	}
	@Override
	public int deleteSubTable(String databaseName, String tableName, String[] condition) {
		// TODO Auto-generated method stub
		working= readFile(databaseName, tableName);
		tableData= new ArrayList<ArrayList<String>>();
		tableData=  deleteObject.deleteSubTable(working,condition, ArrayOfTypes,headers);
		writeFile(databaseName, tableName,tableData);
		return deleteObject.getCounter();
	}
	@Override
	public int update(String databaseName, String tableName, String[] condition, String[] updateStatment) {
		// TODO Auto-generated method stub
		working= readFile(databaseName, tableName);
		tableData= new ArrayList<ArrayList<String>>();
		tableData=  UpdatObject.update(working,condition,updateStatment, ArrayOfTypes,headers);
		writeFile(databaseName, tableName,tableData);
		return UpdatObject.getCounter();
	}
	@Override
	public int updateWhitoutWhere(String databaseName, String tableName, String[] updateStatment) {
		// TODO Auto-generated method stub
		working= readFile(databaseName, tableName);
		tableData= new ArrayList<ArrayList<String>>();
		tableData=  UpdatObject.updateWhitoutWhere(working,updateStatment, ArrayOfTypes,headers);
		writeFile(databaseName, tableName,tableData);
		return UpdatObject.getCounter();
	}
	@Override
	public String[][] selectColumnsWithCondition(String databaseName, String tableName, String[] columntitles,
			String[] Condition) {
		// TODO Auto-generated method stub
		working= readFile(databaseName, tableName);
		tableData= new ArrayList<ArrayList<String>>();
		tableData = working;
		writeFile(databaseName, tableName, tableData); ///////////// return array
		String[][]outputTable = SelectObject.selectColumnsWithCondition(tableData,Condition,columntitles,ArrayOfTypes,headers);
		Type=SelectObject.getType();
		return outputTable;
	}
	@Override
	public String[][] selectColumns(String databaseName, String tableName, String[] columntitles) {
		// TODO Auto-generated method stub
		working= readFile(databaseName, tableName);
		tableData= new ArrayList<ArrayList<String>>();
		tableData = working;
		writeFile(databaseName, tableName, tableData); ///////////// return array
		String[][]outputTable = SelectObject.selectColumns(tableData,columntitles,ArrayOfTypes,headers);
		Type=SelectObject.getType();
		return outputTable;
	}
	@Override
	public String[][] selectAllColumns(String databaseName, String tableName) {
		// TODO Auto-generated method stub
		working= readFile(databaseName, tableName);
		writeFile(databaseName, tableName, SelectObject.selectAllColumns(working,ArrayOfTypes)); ///////////// return array
		Type=SelectObject.getType();
		String[][]outputTable = new String[(working.size())][working.get(0).size()];
		for (int i = 0; i < working.size(); i++) {
			for (int j = 0; j < working.get(0).size(); j++) {
				outputTable[i][j] = working.get(i).get(j);
			}
		}
		return outputTable;
	}
	@Override
	public String[][] selectAllWithCondition(String databaseName, String tableName, String[] Condition) {
		// TODO Auto-generated method stub
		working= readFile(databaseName, tableName);
		tableData= new ArrayList<ArrayList<String>>();
		tableData = working;
		writeFile(databaseName, tableName, tableData); ///////////// return array
		String[][]outputTable = SelectObject.selectAllWithCondition(tableData,Condition,ArrayOfTypes,headers);
		Type=SelectObject.getType();
		return outputTable;
	}
	@Override
	public int addAlter(String databaseName, String tableName, String type, String columName) {
		// TODO Auto-generated method stub
		working = readFile(databaseName, tableName);
		tableData=alterObject.addColum(working, ArrayOfTypes,headers,type,columName);
		headers=alterObject.getHeaders();
		ArrayOfTypes =alterObject.getArrayOfTypes();
         writeFile(databaseName, tableName,tableData);	
		return alterObject.getCounter();
	}
	@Override
	public int deleteAlter(String databaseName, String tableName, String columName) {
		// TODO Auto-generated method stub
		working = readFile(databaseName, tableName);
		tableData=alterObject.deleteColum(working, ArrayOfTypes,headers,columName);
		headers=alterObject.getHeaders();
		ArrayOfTypes =alterObject.getArrayOfTypes();
         writeFile(databaseName, tableName,tableData);	
		return alterObject.getCounter();
	}
	@Override
	public String[][] distinct(String databaseName, String tableName, String[] columsName) {
		// TODO Auto-generated method stub
		working= readFile(databaseName, tableName);
		tableData= new ArrayList<ArrayList<String>>();
		tableData = working;
		working= distinctObject.distinct(tableData, columsName,headers,ArrayOfTypes);
		writeFile(databaseName, tableName, tableData); ///////////// return array
		String[][]outputTable = new String[(working.size())][working.get(0).size()];
		for (int i = 0; i < working.size(); i++) {
			for (int j = 0; j < working.get(0).size(); j++) {
				outputTable[i][j] = working.get(i).get(j);
			}
		}
		return outputTable;
	}
}
