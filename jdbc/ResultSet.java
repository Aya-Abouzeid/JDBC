package eg.edu.alexu.csd.oop.jdbc;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.Ref;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Map;

public class ResultSet implements java.sql.ResultSet {
	public String[][] resultSet = null;
	private String TableName;
	private int cursor = 0;
	private Statement statement = null;
	private String[]Type;
	boolean closed = false;

	public ResultSet(Statement statement, String[][] Rset) {
		this.statement = statement;
		this.resultSet = Rset;
	}
	public void getTypes()  {
		this.Type=this.statement.Type;
		
		}
	public String getName() {
		this.TableName=this.statement.tableName;
		return this.TableName;
	}

	public String getType(int column) {
        getTypes();
		return  this.Type[column-1];
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public boolean absolute(int row) throws SQLException {
		if (closed) {
			throw new SQLException();
		}

		if (this.resultSet == null || this.resultSet.length <= 1) {
			return false;
		}
		if (row >= 0) {
			this.cursor = row;
		} else if (row < 0) {
			this.cursor = this.resultSet.length + row;
		}
		if (this.cursor <= 0 || this.cursor >= this.resultSet.length) {
			this.cursor = 0;
			return false;
		}
		return true;

	}

	@Override
	public void afterLast() throws SQLException {
		if (closed) {
			throw new SQLException();
		}
		if (this.resultSet != null && this.resultSet.length > 1) {
			this.cursor = this.resultSet.length;
		}

	}

	@Override
	public void beforeFirst() throws SQLException {
		if (closed) {
			throw new SQLException();
		}

		if (this.resultSet != null && this.resultSet.length > 1) {
			this.cursor = 0;
		}
	}

	@Override
	public void cancelRowUpdates() throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void clearWarnings() throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void close() throws SQLException {
		if (closed) {
			throw new SQLException();
		}
		this.resultSet = null;
		this.cursor = 0;
		this.TableName = null;
		this.closed = true;

	}

	@Override
	public void deleteRow() throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public int findColumn(String columnLabel) throws SQLException {
		if (closed) {
			throw new SQLException();
		}

		if (this.resultSet == null || this.resultSet.length <= 1) {
			return 0;
		}
		for (int i = 0; i < this.resultSet[0].length; i++) {
			if (this.resultSet[0][i].equals(columnLabel)) {
				return i + 1;
			}
		}
		return 0;

	}

	@Override
	public boolean first() throws SQLException {
		if (closed) {
			throw new SQLException();
		}

		if (this.resultSet == null || this.resultSet.length <= 1) {
			return false;
		}
		this.cursor = 1;
		return true;

	}

	@Override
	public Array getArray(int columnIndex) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public Array getArray(String columnLabel) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public InputStream getAsciiStream(int columnIndex) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public InputStream getAsciiStream(String columnLabel) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public BigDecimal getBigDecimal(int columnIndex) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public BigDecimal getBigDecimal(String columnLabel) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public BigDecimal getBigDecimal(int columnIndex, int scale) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public BigDecimal getBigDecimal(String columnLabel, int scale) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public InputStream getBinaryStream(int columnIndex) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public InputStream getBinaryStream(String columnLabel) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public Blob getBlob(int columnIndex) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public Blob getBlob(String columnLabel) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public boolean getBoolean(int columnIndex) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public boolean getBoolean(String columnLabel) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public byte getByte(int columnIndex) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public byte getByte(String columnLabel) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public byte[] getBytes(int columnIndex) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public byte[] getBytes(String columnLabel) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public Reader getCharacterStream(int columnIndex) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public Reader getCharacterStream(String columnLabel) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public Clob getClob(int columnIndex) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public Clob getClob(String columnLabel) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public int getConcurrency() throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public String getCursorName() throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public Date getDate(int columnIndex) throws SQLException {
		if (closed) {
			throw new SQLException();
		}
		if (this.resultSet == null || this.resultSet.length <= 1||!this.getType(columnIndex).equalsIgnoreCase("date")) {
			return null;
		}
	
		if(this.resultSet[this.cursor][columnIndex - 1].equals("null")){
	    	   return null;
	       }
		Date value = Date.valueOf(this.resultSet[this.cursor][columnIndex - 1]);
		return value;

	}

	@Override
	public Date getDate(String columnLabel) throws SQLException {
		if (closed) {
			throw new SQLException();
		}
		System.out.println(this.getType(this.findColumn(columnLabel)));
		ResultSetMetaData n=this.getMetaData();
		 System.out.println("333"+n.getColumnType(this.findColumn(columnLabel)));
		
		if (this.resultSet == null || this.resultSet.length <= 1||!this.getType(this.findColumn(columnLabel)).equalsIgnoreCase("date")) {
			return null;
		}
		if(this.resultSet[this.cursor][this.findColumn(columnLabel) - 1].equals("null")){
	    	   return null;
	       }

		int columnIndex = this.findColumn(columnLabel);
		return this.getDate(columnIndex);

	}

	@Override
	public Date getDate(int columnIndex, Calendar cal) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public Date getDate(String columnLabel, Calendar cal) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public double getDouble(int columnIndex) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public double getDouble(String columnLabel) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public int getFetchDirection() throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public int getFetchSize() throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public float getFloat(int columnIndex) throws SQLException {
		if (closed) {
			throw new SQLException();
		}
		if (this.resultSet == null || this.resultSet.length <= 1||!this.getType(columnIndex).equalsIgnoreCase("float")) {
			return 0;
		}
		if(this.resultSet[this.cursor][columnIndex - 1].equals("null")){
	    	   return 0;
	       }
		float value = Float.parseFloat(this.resultSet[this.cursor][columnIndex - 1]);
		return value;

	}

	@Override
	public float getFloat(String columnLabel) throws SQLException {
		if (closed) {
			throw new SQLException();
		}
		if (this.resultSet == null || this.resultSet.length <= 1||!this.getType(this.findColumn(columnLabel)).equalsIgnoreCase("float")) {
			return 0;
		}
		if(this.resultSet[this.cursor][findColumn(columnLabel) - 1].equals("null")){
	    	   return 0;
	       }
		int columnIndex = this.findColumn(columnLabel);
		return this.getFloat(columnIndex);

	}

	@Override
	public int getHoldability() throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public int getInt(int columnIndex) throws SQLException {
		if (closed) {
			throw new SQLException();
		}
		if (this.resultSet == null || this.resultSet.length <= 1||!this.getType(columnIndex).equalsIgnoreCase("int")) {
			return 0;
		}
		if(this.resultSet[this.cursor][columnIndex - 1].equals("null")){
	    	   return 0;
	       }
		int value = Integer.parseInt(this.resultSet[this.cursor][columnIndex - 1]);
		return value;

	}

	@Override
	public int getInt(String columnLabel) throws SQLException {
		if (closed) {
			throw new SQLException();
		}
		if (this.resultSet == null || this.resultSet.length <= 1||!this.getType(this.findColumn(columnLabel)).equalsIgnoreCase("int")) {
			return 0;
		}
		if(this.resultSet[this.cursor][ this.findColumn(columnLabel)- 1].equals("null")){
	    	   return 0;
	       }
		int columnIndex = this.findColumn(columnLabel);
		return this.getInt(columnIndex);
	}

	@Override
	public long getLong(int columnIndex) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public long getLong(String columnLabel) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public ResultSetMetaData getMetaData() throws SQLException {

		return  new ResultSetMetaData(this);
	}

	@Override
	public Reader getNCharacterStream(int columnIndex) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public Reader getNCharacterStream(String columnLabel) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public NClob getNClob(int columnIndex) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public NClob getNClob(String columnLabel) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public String getNString(int columnIndex) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public String getNString(String columnLabel) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public Object getObject(int columnIndex) throws SQLException {
		if (closed) {
			throw new SQLException();
		}
		if (this.resultSet == null || this.resultSet.length <= 1) {
			return null;
		}

		return this.resultSet[this.cursor][columnIndex - 1];
	}

	@Override
	public Object getObject(String columnLabel) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public Object getObject(int columnIndex, Map<String, Class<?>> map) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public Object getObject(String columnLabel, Map<String, Class<?>> map) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public <T> T getObject(int columnIndex, Class<T> type) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public <T> T getObject(String columnLabel, Class<T> type) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public Ref getRef(int columnIndex) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public Ref getRef(String columnLabel) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public int getRow() throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public RowId getRowId(int columnIndex) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public RowId getRowId(String columnLabel) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public SQLXML getSQLXML(int columnIndex) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public SQLXML getSQLXML(String columnLabel) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public short getShort(int columnIndex) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public short getShort(String columnLabel) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public Statement getStatement() throws SQLException {
		if (closed) {
			throw new SQLException();
		}
		return this.statement;
	}

	@Override
	public String getString(int columnIndex) throws SQLException {
		if (closed) {
			throw new SQLException();
		}
		if (this.resultSet == null || this.resultSet.length <= 1||!this.getType(columnIndex).equalsIgnoreCase("varchar")) {
			return null;
		}
		if(this.resultSet[this.cursor][columnIndex - 1].equals("null")){
	    	   return null;
	       }
		return this.resultSet[this.cursor][columnIndex - 1];
	}

	@Override
	public String getString(String columnLabel) throws SQLException {
		if (closed) {
			throw new SQLException();
		}
		if (this.resultSet == null || this.resultSet.length <= 1||!this.getType(this.findColumn(columnLabel)).equalsIgnoreCase("varchar")) {
			return null;
		}
		if(this.resultSet[this.cursor][ this.findColumn(columnLabel)- 1].equals("null")){
	    	   return null;
	       }
		int columnIndex = this.findColumn(columnLabel);
		return this.getString(columnIndex);
	}

	@Override
	public Time getTime(int columnIndex) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public Time getTime(String columnLabel) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public Time getTime(int columnIndex, Calendar cal) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public Time getTime(String columnLabel, Calendar cal) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public Timestamp getTimestamp(int columnIndex) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public Timestamp getTimestamp(String columnLabel) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public Timestamp getTimestamp(int columnIndex, Calendar cal) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public Timestamp getTimestamp(String columnLabel, Calendar cal) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public int getType() throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public URL getURL(int columnIndex) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public URL getURL(String columnLabel) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public InputStream getUnicodeStream(int columnIndex) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public InputStream getUnicodeStream(String columnLabel) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public SQLWarning getWarnings() throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void insertRow() throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public boolean isAfterLast() throws SQLException {
		if (closed) {
			throw new SQLException();
		}

		if (this.resultSet == null || this.resultSet.length <= 1) {
			return false;
		}
		if (this.cursor == this.resultSet.length) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isBeforeFirst() throws SQLException {
		if (closed) {
			throw new SQLException();
		}

		if (this.resultSet == null || this.resultSet.length <= 1) {
			return false;
		}
		if (this.cursor == 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isClosed() throws SQLException {

		return this.closed;
	}

	@Override
	public boolean isFirst() throws SQLException {
		if (closed) {
			throw new SQLException();
		}

		if (this.resultSet == null || this.resultSet.length <= 1) {
			return false;
		}
		if (this.cursor == 1) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isLast() throws SQLException {
		if (closed) {
			throw new SQLException();
		}

		if (this.resultSet == null || this.resultSet.length <= 1) {
			return false;
		}
		if (this.cursor == this.resultSet.length - 1) {
			return true;
		}
		return false;
	}

	@Override
	public boolean last() throws SQLException {
		if (closed) {
			throw new SQLException();
		}

		if (this.resultSet == null || this.resultSet.length <= 1) {
			return false;
		}
		this.cursor = this.resultSet.length - 1;
		return true;
	}

	@Override
	public void moveToCurrentRow() throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void moveToInsertRow() throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public boolean next() throws SQLException {
		if (closed) {
			throw new SQLException();
		}

		if (this.resultSet == null || this.resultSet.length <= 1) {
			return false;
		}
		this.cursor++;
		if (this.cursor > this.resultSet.length - 1) {
			this.cursor = this.resultSet.length;
			return false;
		}
		return true;
	}

	@Override
	public boolean previous() throws SQLException {
		if (closed) {
			throw new SQLException();
		}

		if (this.resultSet == null || this.resultSet.length <= 1) {
			return false;
		}
		this.cursor--;
		if (this.cursor < 1) {
			this.cursor = 0;
			return false;
		}
		return true;
	}

	@Override
	public void refreshRow() throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public boolean relative(int rows) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public boolean rowDeleted() throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public boolean rowInserted() throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public boolean rowUpdated() throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void setFetchDirection(int direction) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void setFetchSize(int rows) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void updateArray(int columnIndex, Array x) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void updateArray(String columnLabel, Array x) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void updateAsciiStream(int columnIndex, InputStream x) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void updateAsciiStream(String columnLabel, InputStream x) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void updateAsciiStream(int columnIndex, InputStream x, int length) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void updateAsciiStream(String columnLabel, InputStream x, int length) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void updateAsciiStream(int columnIndex, InputStream x, long length) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void updateAsciiStream(String columnLabel, InputStream x, long length) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void updateBigDecimal(int columnIndex, BigDecimal x) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void updateBigDecimal(String columnLabel, BigDecimal x) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void updateBinaryStream(int columnIndex, InputStream x) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void updateBinaryStream(String columnLabel, InputStream x) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void updateBinaryStream(int columnIndex, InputStream x, int length) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void updateBinaryStream(String columnLabel, InputStream x, int length) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void updateBinaryStream(int columnIndex, InputStream x, long length) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void updateBinaryStream(String columnLabel, InputStream x, long length) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void updateBlob(int columnIndex, Blob x) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void updateBlob(String columnLabel, Blob x) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void updateBlob(int columnIndex, InputStream inputStream) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void updateBlob(String columnLabel, InputStream inputStream) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void updateBlob(int columnIndex, InputStream inputStream, long length) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void updateBlob(String columnLabel, InputStream inputStream, long length) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void updateBoolean(int columnIndex, boolean x) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void updateBoolean(String columnLabel, boolean x) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void updateByte(int columnIndex, byte x) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void updateByte(String columnLabel, byte x) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void updateBytes(int columnIndex, byte[] x) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void updateBytes(String columnLabel, byte[] x) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void updateCharacterStream(int columnIndex, Reader x) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void updateCharacterStream(String columnLabel, Reader reader) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void updateCharacterStream(int columnIndex, Reader x, int length) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void updateCharacterStream(String columnLabel, Reader reader, int length) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void updateCharacterStream(int columnIndex, Reader x, long length) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void updateCharacterStream(String columnLabel, Reader reader, long length) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void updateClob(int columnIndex, Clob x) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void updateClob(String columnLabel, Clob x) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void updateClob(int columnIndex, Reader reader) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void updateClob(String columnLabel, Reader reader) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void updateClob(int columnIndex, Reader reader, long length) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void updateClob(String columnLabel, Reader reader, long length) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void updateDate(int columnIndex, Date x) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void updateDate(String columnLabel, Date x) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void updateDouble(int columnIndex, double x) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void updateDouble(String columnLabel, double x) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void updateFloat(int columnIndex, float x) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void updateFloat(String columnLabel, float x) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void updateInt(int columnIndex, int x) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void updateInt(String columnLabel, int x) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void updateLong(int columnIndex, long x) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void updateLong(String columnLabel, long x) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void updateNCharacterStream(int columnIndex, Reader x) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void updateNCharacterStream(String columnLabel, Reader reader) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void updateNCharacterStream(int columnIndex, Reader x, long length) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void updateNCharacterStream(String columnLabel, Reader reader, long length) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void updateNClob(int columnIndex, NClob nClob) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void updateNClob(String columnLabel, NClob nClob) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void updateNClob(int columnIndex, Reader reader) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void updateNClob(String columnLabel, Reader reader) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void updateNClob(int columnIndex, Reader reader, long length) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void updateNClob(String columnLabel, Reader reader, long length) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void updateNString(int columnIndex, String nString) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void updateNString(String columnLabel, String nString) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void updateNull(int columnIndex) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void updateNull(String columnLabel) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void updateObject(int columnIndex, Object x) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void updateObject(String columnLabel, Object x) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void updateObject(int columnIndex, Object x, int scaleOrLength) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void updateObject(String columnLabel, Object x, int scaleOrLength) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void updateRef(int columnIndex, Ref x) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void updateRef(String columnLabel, Ref x) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void updateRow() throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void updateRowId(int columnIndex, RowId x) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void updateRowId(String columnLabel, RowId x) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void updateSQLXML(int columnIndex, SQLXML xmlObject) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void updateSQLXML(String columnLabel, SQLXML xmlObject) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void updateShort(int columnIndex, short x) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void updateShort(String columnLabel, short x) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void updateString(int columnIndex, String x) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void updateString(String columnLabel, String x) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void updateTime(int columnIndex, Time x) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void updateTime(String columnLabel, Time x) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void updateTimestamp(int columnIndex, Timestamp x) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void updateTimestamp(String columnLabel, Timestamp x) throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public boolean wasNull() throws SQLException {
		throw new java.lang.UnsupportedOperationException();

	}

}
