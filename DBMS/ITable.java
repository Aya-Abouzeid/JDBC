package eg.edu.alexu.csd.oop.DBMS;

import java.util.ArrayList;

public interface ITable {
	public void creatTable( String databaseName,String tableName , String [] properties);
	public void dropTable(String databaseName,String tableName ) ;
	public ArrayList<ArrayList<String>> readFile(String databaseName , String tableName);
    public void writeFile(String databaseName , String tableName , ArrayList<ArrayList<String>> table);
	public int  insertRow(String databaseName ,String tableName ,String [] properties );
	public int insertSub(String databaseName,String tableName ,String[]columSend ,String [] properties );
	public int deleteTable(String databaseName,String tableName );
	public int deleteSubTable(String databaseName,String tableName ,String[] condition);
	public int update(String databaseName,String tableName , String [] condition,String [] updateStatment);
	public int updateWhitoutWhere (String databaseName,String tableName ,String [] updateStatment);
	public String[][] selectColumnsWithCondition(String databaseName, String tableName,String[] columntitles, String[] Condition);
	public String[][] selectColumns(String databaseName, String tableName,String[] columntitles);
	public String[][] selectAllColumns(String databaseName, String tableName);
	public String[][] selectAllWithCondition(String databaseName, String tableName, String[] Condition);  
	public int addAlter(String databaseName, String tableName ,String type,String columName) ;
    public int deleteAlter(String databaseName, String tableName ,String columName) ;
    public String[][] distinct(String databaseName, String tableName ,String[] columsName);
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
