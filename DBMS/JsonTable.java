package eg.edu.alexu.csd.oop.DBMS;

import java.util.ArrayList;

public class JsonTable implements ITable{

	@Override
	public void creatTable(String databaseName, String tableName, String[] properties) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dropTable(String databaseName, String tableName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<ArrayList<String>> readFile(String databaseName, String tableName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void writeFile(String databaseName, String tableName, ArrayList<ArrayList<String>> table) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int insertRow(String databaseName, String tableName, String[] properties) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSub(String databaseName, String tableName, String[] columSend, String[] properties) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteTable(String databaseName, String tableName) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteSubTable(String databaseName, String tableName, String[] condition) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(String databaseName, String tableName, String[] condition, String[] updateStatment) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateWhitoutWhere(String databaseName, String tableName, String[] updateStatment) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String[][] selectColumnsWithCondition(String databaseName, String tableName, String[] columntitles,
			String[] Condition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[][] selectColumns(String databaseName, String tableName, String[] columntitles) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[][] selectAllColumns(String databaseName, String tableName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[][] selectAllWithCondition(String databaseName, String tableName, String[] Condition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addAlter(String databaseName, String tableName, String type, String columName) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteAlter(String databaseName, String tableName, String columName) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String[][] distinct(String databaseName, String tableName, String[] columsName) {
		// TODO Auto-generated method stub
		return null;
	}

}
