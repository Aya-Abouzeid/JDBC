package eg.edu.alexu.csd.oop.DBMS;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ITable {
	public void creatTable( String databaseName,String tableName , String [] properties)  throws SQLException;
	public String[]getType();
	public void dropTable(String databaseName,String tableName ) ;
	public ArrayList<ArrayList<String>> readFile(String databaseName , String tableName);
    public void writeFile(String databaseName , String tableName , ArrayList<ArrayList<String>> table)  throws SQLException;
	public int  insertRow(String databaseName ,String tableName ,String [] properties ) throws SQLException;
	public int insertSub(String databaseName,String tableName ,String[]columSend ,String [] properties ) throws SQLException;
	public int deleteTable(String databaseName,String tableName ) throws SQLException;
	public int deleteSubTable(String databaseName,String tableName ,String[] condition) throws SQLException;
	public int update(String databaseName,String tableName , String [] condition,String [] updateStatment) throws SQLException;
	public int updateWhitoutWhere (String databaseName,String tableName ,String [] updateStatment) throws SQLException;
	public String[][] selectColumnsWithCondition(String databaseName, String tableName,String[] columntitles, String[] Condition) throws SQLException;
	public String[][] selectColumns(String databaseName, String tableName,String[] columntitles) throws SQLException;
	public String[][] selectAllColumns(String databaseName, String tableName) throws SQLException;
	public String[][] selectAllWithCondition(String databaseName, String tableName, String[] Condition) throws SQLException;  
	public int addAlter(String databaseName, String tableName ,String type,String columName)  throws SQLException;
    public int deleteAlter(String databaseName, String tableName ,String columName) throws SQLException ;
    public String[][] distinct(String databaseName, String tableName ,String[] columsName) throws SQLException;
	/* public void update(ArrayList<ArrayList<String>> tableData);
	 * 
		public void updateWhitoutWhere (ArrayList<ArrayList<String>> tableData);
		public void  insertRow(ArrayList<ArrayList<String>> tableData);
		public void insertSub(ArrayList<ArrayList<String>> tableData);
		public void deleteSubTable(ArrayList<ArrayList<String>> tableData);
		public void deleteTable(ArrayList<ArrayList<String>> tableData);
		public String[][] selectColumnsWithCondition(ArrayList<ArrayList<String>> tableData);
		public String[][] selectColumns(ArrayList<ArrayList<String>> tableData);
		public String[][] selectAllColumns(ArrayList<ArrayList<String>> tableData);
		public String[][] selectAllWithCondition(ArrayList<ArrayList<String>> tableData);*/
}
