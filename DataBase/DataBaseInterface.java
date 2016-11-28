package eg.edu.alexu.csd.oop.DataBase;
 
public interface DataBaseInterface {
 
    public void createDatabase(String databaseName);
    public void dropDatabase(String databaseName);
    public void creatTable( String databaseName,String tableName , String [] properties);
    public void  insertRow(String databaseName ,String tableName ,String [] properties );
    public void update(String databaseName,String tableName , String [] condition,String [] updateStatment);
    public void updateWhitoutWhere (String databaseName,String tableName ,String [] updateStatment); 
    public void insertSub(String databaseName,String tableName ,String[]columSend ,String [] properties );
    public void deleteTable(String databaseName,String tableName );
    public void deleteSubTable(String databaseName,String tableName ,String[] condition);
    public String[][] selectColumnsWithCondition(String databaseName, String tableName,String[] columntitles, String[] Condition);
    public String[][] selectColumns(String databaseName, String tableName,String[] columntitles);
    public String[] selectAllColumns(String databaseName, String tableName);
	public String[] selectAllWithCondition(String databaseName, String tableName, String[] Condition);
    
}