package eg.edu.alexu.csd.oop.jdbc;
import java.sql.SQLException;
import java.sql.Types;
public class ResultSetMetaData implements java.sql.ResultSetMetaData{
   private ResultSet resultSet=null; 
   public ResultSetMetaData(ResultSet resultSet) {
		this.resultSet=resultSet;
	} 
	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		// TODO Auto-generated method stub
		throw new java.lang.UnsupportedOperationException();
	}
 
	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		// TODO Auto-generated method stub
		throw new java.lang.UnsupportedOperationException();

	}
 
	@Override
	public String getCatalogName(int column) throws SQLException {
		// TODO Auto-generated method stub
		throw new java.lang.UnsupportedOperationException();

	}
 
	@Override
	public String getColumnClassName(int column) throws SQLException {
		// TODO Auto-generated method stub
		throw new java.lang.UnsupportedOperationException();

	}
 
	@Override
	public int getColumnCount() throws SQLException {
		if(this.resultSet.resultSet==null){
			return 0;
		}
		int columnCount=this.resultSet.resultSet[0].length;
		return columnCount;
	}
 
	@Override
	public int getColumnDisplaySize(int column) throws SQLException {
		// TODO Auto-generated method stub
		throw new java.lang.UnsupportedOperationException();

	}
 
	@Override
	public String getColumnLabel(int column) throws SQLException {
		if(this.resultSet.resultSet==null){
			return null;
		}
		String ColumnLabel=this.resultSet.resultSet[0][column-1];
		return ColumnLabel;
	}
 
	@Override
	public String getColumnName(int column) throws SQLException {
		if(this.resultSet.resultSet==null){
			return null;
		}
		String ColumnLabel=this.resultSet.resultSet[0][column-1];
		return ColumnLabel;
	}
 
	@Override
	public int getColumnType(int column) throws SQLException {
		if(this.resultSet.resultSet==null){
			return 0;
		}
		if(column<=0||column>=this.resultSet.resultSet[0].length){
			return 0;
		}
 
			String type=this.resultSet.getType(column);
			if(type.equals("int")){
				return Types.INTEGER;
			}
			if(type.equals("varchar")){
				return Types.VARCHAR;
			}
			if(type.equals("date")){
				return Types.DATE;
			}
			if(type.equals("float")){
				return Types.FLOAT;
			}
 
		return 0;
	}
 
	@Override
	public String getColumnTypeName(int column) throws SQLException {
		// TODO Auto-generated method stub
		throw new java.lang.UnsupportedOperationException();
	}
 
	@Override
	public int getPrecision(int column) throws SQLException {
		// TODO Auto-generated method stub
		throw new java.lang.UnsupportedOperationException();
	}
 
	@Override
	public int getScale(int column) throws SQLException {
		// TODO Auto-generated method stub
		throw new java.lang.UnsupportedOperationException();
	}
 
	@Override
	public String getSchemaName(int column) throws SQLException {
		// TODO Auto-generated method stub
		throw new java.lang.UnsupportedOperationException();
	}
 
	@Override
	public String getTableName(int column) throws SQLException {
 
		return resultSet.getName();
	}
 
	@Override
	public boolean isAutoIncrement(int column) throws SQLException {
		// TODO Auto-generated method stub
		throw new java.lang.UnsupportedOperationException();
	}
 
	@Override
	public boolean isCaseSensitive(int column) throws SQLException {
		// TODO Auto-generated method stub
		throw new java.lang.UnsupportedOperationException();
	}
 
	@Override
	public boolean isCurrency(int column) throws SQLException {
		// TODO Auto-generated method stub
		throw new java.lang.UnsupportedOperationException();
	}
 
	@Override
	public boolean isDefinitelyWritable(int column) throws SQLException {
		// TODO Auto-generated method stub
		throw new java.lang.UnsupportedOperationException();
	}
 
	@Override
	public int isNullable(int column) throws SQLException {
		// TODO Auto-generated method stub
		throw new java.lang.UnsupportedOperationException();
	}
 
	@Override
	public boolean isReadOnly(int column) throws SQLException {
		// TODO Auto-generated method stub
		throw new java.lang.UnsupportedOperationException();
	}
 
	@Override
	public boolean isSearchable(int column) throws SQLException {
		// TODO Auto-generated method stub
		throw new java.lang.UnsupportedOperationException();
	}
 
	@Override
	public boolean isSigned(int column) throws SQLException {
		// TODO Auto-generated method stub
		throw new java.lang.UnsupportedOperationException();
	}
 
	@Override
	public boolean isWritable(int column) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
 
}