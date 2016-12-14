package eg.edu.alexu.csd.oop.jdbc;

import java.sql.SQLException;
import java.sql.SQLWarning;
import java.util.ArrayList;

import javax.naming.OperationNotSupportedException;

import eg.edu.alexu.csd.oop.DBMS.*;

public class Statement implements java.sql.Statement {
	Insert InsertObject = new Insert();
	Create CreateObject = new Create();
	Delete DeleteObject = new Delete();
	Drop DropObject = new Drop();
	Select SelectObject = new Select();
	Update UpdateObject = new Update();
	Alter AlterObject = new Alter();
	XmlValidation DetectObject;
	public String[]Type;
	public String tableName;
	private boolean counted = false;
	private boolean RsetFound = false;
	private boolean OperationNotExecuted = false;
	private boolean SelectNotExecuted = false;
	private boolean IsClosed = false;
	private ArrayList<String> batch;
	private Connection connection;
	private int UpdateCount;
	private ResultSet Rset = null;
	private Parser parse;
	Queries query ;
	public Statement(Connection connection, Parser parse , Queries query , XmlValidation Detect) {
		// TODO Auto-generated constructor stub
		this.batch = new ArrayList<String>();
		this.connection = connection;
		this.parse = parse;
		this.IsClosed = false;
		this.OperationNotExecuted = false;
		this.RsetFound = false;
		this.counted = false;
		this.SelectNotExecuted = false;
		this.UpdateCount = -1;
		this.query = query;
		this.DetectObject = Detect;
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
	public void addBatch(String sql) throws SQLException {
		// TODO Auto-generated method stub
		if (!this.IsClosed) {
			this.batch.add(sql);
		} else
			throw new SQLException();

	}

	@Override
	public void cancel() throws SQLException {
		// TODO Auto-generated method stub
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public void clearBatch() throws SQLException {
		// TODO Auto-generated method stub
		if (!this.IsClosed) {
			this.batch.clear();
		} else
			throw new SQLException();
	}

	@Override
	public void clearWarnings() throws SQLException {
		// TODO Auto-generated method stub
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public void close() throws SQLException {
		// TODO Auto-generated method stub
		if (!this.IsClosed) {
			if (Rset != null) {
				this.Rset.close();
			}
			this.RsetFound = false;
			this.parse = null;
			this.UpdateCount = -1;
			this.batch.clear();
			this.IsClosed = true;
			this.RsetFound = false;
			this.counted = false;
			this.SelectNotExecuted = false;
			this.query = null;
			this.DetectObject = null;
		}
	}

	@Override
	public void closeOnCompletion() throws SQLException {
		// TODO Auto-generated method stub
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public boolean execute(String sql) throws SQLException {
		// TODO Auto-generated method stub

		if (!this.IsClosed) {

			String FirstWord = parse.Parse(sql);
			
			if (sql != null){
				ChooseQuery(FirstWord);
		
			}
			
			if (Rset != null)
				return true;

			return false;
		} else
			throw new SQLException();

	}

	private void CaseInsert() throws SQLException {
		if (parse.GetDBfound()) {
			counted = true;
			UpdateCount = InsertObject.Insert(parse.GetDBfound(), parse.GetCurrentDB(), parse.GetGetRest(),query,DetectObject);
          
			if (InsertObject.GetExecuted() == false || (UpdateCount == 0 && InsertObject.GetExecuted() == true)){
				OperationNotExecuted = true;
				throw new SQLException();

			}
			
		} else
			System.out.println("Invalid command. Select a Database first.");

	}

	private void CaseDelete() throws SQLException {
		if (parse.GetDBfound()) {
			counted = true;
			UpdateCount = DeleteObject.Delete(parse.GetDBfound(), parse.GetCurrentDB(), parse.GetGetRest() , query,DetectObject);
		
			if (DeleteObject.GetExecuted() == false|| (UpdateCount == 0 && DeleteObject.GetExecuted() == true)) {
				OperationNotExecuted = true;
				throw new SQLException();
	
			}
		} else
			System.out.println("Invalid command. Select a Database first.");

	}

	private void CaseUpdate() throws SQLException {
		if (parse.GetDBfound()) {
			counted = true;
			UpdateCount = UpdateObject.Update(parse.GetDBfound(), parse.GetCurrentDB(), parse.GetGetRest() , query,DetectObject);
			if (UpdateObject.GetExecuted() == false  || (UpdateCount == -1 && UpdateObject.GetExecuted() == true)) {
				OperationNotExecuted = true;
				throw new SQLException();
	
			}
		} else
			System.out.println("Invalid command. Select a Database first.");

	}

	private void CaseSelect() throws SQLException {
		if (parse.GetDBfound()) { 
			RsetFound = true;
			if(SelectObject.Select(parse.GetDBfound(),parse.GetCurrentDB(), parse.GetGetRest() , query,DetectObject) == null)
				Rset = null;
			else
			Rset = new ResultSet(this,SelectObject.Select(parse.GetDBfound(),parse.GetCurrentDB(), parse.GetGetRest() , query,DetectObject));
			Type=SelectObject.Type;
            tableName=SelectObject.current_table1;
			if (SelectObject.GetExecuted() == false ){
				SelectNotExecuted = true;
				throw new SQLException();
	
			}
		} else
			System.out.println("Invalid command. Select a Database first.");

	}

	private void CaseAlter() throws SQLException {
		if (parse.GetDBfound()) {
			counted = true;
			UpdateCount = AlterObject.Alter(parse.GetDBfound(), parse.GetCurrentDB(), parse.GetGetRest() ,query,DetectObject);
			if (AlterObject.GetExecuted() == false ){
				OperationNotExecuted = true;
				throw new SQLException();
	
			}
		} else
			System.out.println("Invalid command. Select a Database first.");

	}

	private void ChooseQuery(String FirstWord) throws SQLException {
		SelectNotExecuted = false;
		OperationNotExecuted = false;
		Rset = null;
		RsetFound = false;
		counted = false;
		UpdateCount = -1;

		switch (FirstWord.toLowerCase()) {
		case "create": {
			counted = true;
			UpdateCount = CreateObject.Create(parse.GetDBfound(), parse.GetCurrentDB(), parse.GetGetRest(),query,DetectObject);
			if (CreateObject.GetExecuted() == false) {
				OperationNotExecuted = true;
				throw new SQLException();
			}
			break;
		}
		case "drop": {
			counted = true;
			UpdateCount = DropObject.Drop(parse.GetDBfound(), parse.GetCurrentDB(), parse.GetGetRest(),query,DetectObject);
			parse.SetDBFound(DropObject.NewDpFound());
			if (DropObject.GetExecuted() == false) {
				OperationNotExecuted = true;
				throw new SQLException();
			}
		}
			break;
		case "insert":
			CaseInsert();
			break;
		case "delete":
			CaseDelete();
			break;
		case "use":
			CaseUse();
			break;
		case "update":
			CaseUpdate();
			break;
		case "select":
			CaseSelect();
			break;
		case "alter":
			CaseAlter();
			break;
		default: {
			System.out.println("Invalid Command.");
		}
		}
	}
	public void CaseUse() throws SQLException {
        String Name = parse.GetGetRest();
 
if(query.DetectDataBase(Name)){
        
            parse.SetCurrentlyUsed(Name);
            parse.SetDBFound(true);
            System.out.println("database found");
        } else {
            System.out.println("Invalid DataBase Name.");
            parse.SetDBFound(false);

            throw new SQLException();
        }
 
    }

	@Override
	public boolean execute(String sql, int autoGeneratedKeys) throws SQLException {
		// TODO Auto-generated method stub
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public boolean execute(String sql, int[] columnIndexes) throws SQLException {
		// TODO Auto-generated method stub
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public boolean execute(String sql, String[] columnNames) throws SQLException {
		// TODO Auto-generated method stub
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public int[] executeBatch() throws SQLException {
		// TODO Auto-generated method stub
		if (!this.IsClosed) {
			int[] counts = new int[batch.size()];
			for (int i = 0; i < batch.size(); i++) {

				execute(batch.get(i));
				if (counted && !OperationNotExecuted)
					counts[i] = UpdateCount;
				else if (!SelectNotExecuted && RsetFound)
					counts[i] = SUCCESS_NO_INFO;
				else if ((SelectNotExecuted && RsetFound) || (counted && OperationNotExecuted))
					counts[i] = EXECUTE_FAILED;
			}
			return counts;
		}

		else
			throw new SQLException();

	}

	@Override
	public ResultSet executeQuery(String sql) throws SQLException {
		// TODO Auto-generated method stub
		if (!IsClosed) {

				execute(sql);
				
			if (RsetFound && !SelectNotExecuted)
				return Rset;
			else
				throw new SQLException();

		} else {
			throw new SQLException();
		}
	}

	@Override
	public int executeUpdate(String sql) throws SQLException {
		// TODO Auto-generated method stub
		if (!IsClosed) {

			execute(sql);
			if (counted)
				return UpdateCount;
			else
				throw new SQLException();

			
		} else {
			throw new SQLException();
		}

	}

	@Override
	public int executeUpdate(String sql, int autoGeneratedKeys) throws SQLException {
		// TODO Auto-generated method stub
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public int executeUpdate(String sql, int[] columnIndexes) throws SQLException {
		// TODO Auto-generated method stub
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public int executeUpdate(String sql, String[] columnNames) throws SQLException {
		// TODO Auto-generated method stub
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public Connection getConnection() throws SQLException {
		// TODO Auto-generated method stub
		if (!IsClosed) {
			return this.connection;
		} else
			throw new SQLException();

	}

	@Override
	public int getFetchDirection() throws SQLException {
		// TODO Auto-generated method stub
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public int getFetchSize() throws SQLException {
		// TODO Auto-generated method stub
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public ResultSet getGeneratedKeys() throws SQLException {
		// TODO Auto-generated method stub
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public int getMaxFieldSize() throws SQLException {
		// TODO Auto-generated method stub
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public int getMaxRows() throws SQLException {
		// TODO Auto-generated method stub
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public boolean getMoreResults() throws SQLException {
		// TODO Auto-generated method stub
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public boolean getMoreResults(int current) throws SQLException {
		// TODO Auto-generated method stub
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public int getQueryTimeout() throws SQLException {
		// TODO Auto-generated method stub
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public ResultSet getResultSet() throws SQLException {
		// TODO Auto-generated method stub
		if (!IsClosed) {
			if (Rset == null || counted)
				return null;
			else
				return this.Rset;
		} else
			throw new SQLException();
	}

	@Override
	public int getResultSetConcurrency() throws SQLException {
		// TODO Auto-generated method stub
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public int getResultSetHoldability() throws SQLException {
		// TODO Auto-generated method stub
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public int getResultSetType() throws SQLException {
		// TODO Auto-generated method stub
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public int getUpdateCount() throws SQLException {
		// TODO Auto-generated method stub
		if (!IsClosed)
			return UpdateCount;
		else
			throw new SQLException();
	}

	@Override
	public SQLWarning getWarnings() throws SQLException {
		// TODO Auto-generated method stub
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public boolean isCloseOnCompletion() throws SQLException {
		// TODO Auto-generated method stub
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public boolean isClosed() throws SQLException {
		// TODO Auto-generated method stub
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public boolean isPoolable() throws SQLException {
		// TODO Auto-generated method stub
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void setCursorName(String name) throws SQLException {
		// TODO Auto-generated method stub
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void setEscapeProcessing(boolean enable) throws SQLException {
		// TODO Auto-generated method stub
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void setFetchDirection(int direction) throws SQLException {
		// TODO Auto-generated method stub
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void setFetchSize(int rows) throws SQLException {
		// TODO Auto-generated method stub
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void setMaxFieldSize(int max) throws SQLException {
		// TODO Auto-generated method stub
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void setMaxRows(int max) throws SQLException {
		// TODO Auto-generated method stub
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void setPoolable(boolean poolable) throws SQLException {
		// TODO Auto-generated method stub
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void setQueryTimeout(int seconds) throws SQLException {
		// TODO Auto-generated method stub
		throw new java.lang.UnsupportedOperationException();

	}

}
