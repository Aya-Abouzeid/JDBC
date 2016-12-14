package eg.edu.alexu.csd.oop.DBMS;

import java.io.IOException;
import java.sql.SQLException;

import javax.xml.parsers.ParserConfigurationException;

/*
 * an interface for all tables in the directory.
 */
public interface DataBaseInterface {
		
	/**
	  * Create database with the given name, or use it if exists. This method performs 
	  * a call to executeStructureQuery() internally to create or drop the database. 
	  * @param databaseName   Database name
	  */
	public void createDatabase(String databaseName);
	/**
	  * Drop database with the given name from the directory.  
	  * @param databaseName   Database name
	  */
	public void dropDatabase(String databaseName) throws SQLException;
	/**
	  * Create an XML file to save database in a table .  
	  * @param databaseName   Database name .
	  * @param tableName   XML file	Name . 
	  * @param properties   table columns titles.
	  */
	public void creatTable( String databaseName,String tableName , String [] properties)  throws SQLException;
	/**
	  * insert a row in the table and save it in the given XML file name .  
	  * @param databaseName   Database name .
	  * @param tableName   XML file	Name . 
	  * @param properties   inserted row values .
	  */
	public int  insertRow(String databaseName ,String tableName ,String [] properties ) throws SQLException;
	public void dropTable(String databaseName,String tableName ) throws SQLException ;
	/**
	  * change types or values in the table with special condition and add it in the given XML file .  
	  * @param databaseName   Database name .
	  * @param tableName   XML file	Name . 
	  * @param condition   the special condition .
	  * @param updateStatement   an array that has the new data .
	  */
	public int update(String databaseName,String tableName , String [] condition,String [] updateStatment) throws SQLException;
	/**
	  * change types or values in all the given table  and add it in the given XML file .  
	  * @param databaseName   Database name .
	  * @param tableName   XML file	Name . 
	  * @param updateStatement   an array that has the new data .
	  */
	public int updateWhitoutWhere (String databaseName,String tableName ,String [] updateStatment) throws SQLException;
	/**
	  * insert a values for special columns in the table and add it in the given XML file name .  
	  * @param databaseName   Database name .
	  * @param tableName   XML file	Name . 
	  * @param columSend   special columns will be given a value.
	  * @param properties   values for the special columns .
	  */
	public int insertSub(String databaseName,String tableName ,String[]columSend ,String [] properties ) throws SQLException;
	/**
	  * delete the given table from the directory that means delete the XML file .
	  * @param databaseName   Database name .
	  * @param tableName   XML file	Name . 
	  */
	public int deleteTable(String databaseName,String tableName ) throws SQLException;
	/**
	  * delete in the given table special rows that are suitable with the given condition  .
	  * @param databaseName   Database name .
	  * @param tableName   XML file	Name . 
	  * @param condition   
	  */
	public int deleteSubTable(String databaseName,String tableName ,String[] condition) throws SQLException;
	/**
	  * select from the given table special columns that are suitable with the given condition and return the value of every row for this columns .
	  * @param databaseName   Database name .
	  * @param tableName   XML file	Name . 
	  * @param columntitles   the wanted columns from the table.  
	  * @return an array that contain data of the selected columns .
	  */
	public String[][] selectColumnsWithCondition(String databaseName, String tableName,String[] columntitles, String[] Condition) throws SQLException;
	/**
	  * select from the given table special columns and return the value of every row for this columns .
	  * @param databaseName   Database name .
	  * @param tableName   XML file	Name . 
	  * @param columntitles the wanted columns from the table.  
	  * @return an array that contain data of the selected columns .
	  */
	public String[][] selectColumns(String databaseName, String tableName,String[] columntitles) throws SQLException;
	/**
	  * select from the given table all columns and return the value of every row for this columns .
	  * @param databaseName   Database name .
	  * @param tableName   XML file	Name . 
	  * @return an array that contain data of selected columns.
	  */
	public String[][] selectAllColumns(String databaseName, String tableName) throws SQLException;
	/**
	  * select from the given table all columns that are suitable with a special condition and return the value of every row for this columns .
	  * @param databaseName   Database name .
	  * @param tableName   XML file	Name . 
	  * @return an array that contain data of selected columns.
	  */
	public String[][] selectAllWithCondition(String databaseName, String tableName, String[] Condition) throws SQLException;    
       /**
	 * Create DTD file and save XML file in it.
	 * @param databaseName   Database name.
	 * @param DtdFileName  DTD file name .
	 * @param tableName XML file name . 
	 */
	public void CreateDtDFile(String databaseName,String tableName, String[] dtd,String[]type);
       /**
	 * validate using DOM (DTD as defined in the XML).
	 * @param databaseName   Database name .
	 * @param tableName   XML file Name . 
	 * @return true for validation DTD file and XML file
	 */
	public boolean validateWithDTDUsingDOM(String databaseName, String tableName )throws ParserConfigurationException, IOException;
    public void ListTables(String DataBase);
    public int addAlter(String databaseName, String tableName ,String type,String columName)  throws SQLException;
    public int deleteAlter(String databaseName, String tableName ,String columName)  throws SQLException;
    public String[][] distinct(String databaseName, String tableName ,String[] columsName) throws SQLException;
    public boolean DetectDataBase(String name);
}