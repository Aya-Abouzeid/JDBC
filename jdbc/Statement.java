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
	Log4j logger = new Log4j();
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
		logger.LOG().info("new Statement Created");
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
		logger.LOG().warning("Unsupported Operation !");

		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		// TODO Auto-generated method stub
		logger.LOG().warning("Unsupported Operation !");

		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public void addBatch(String sql) throws SQLException {
		// TODO Auto-generated method stub

		if (!this.IsClosed) {
			logger.LOG().info("New command added to batch : "+ sql );

			this.batch.add(sql);
		} else {
logger.LOG().warning("Can not add to batch , Connection is Closed !");
			throw new SQLException();

		}
	}

	@Override
	public void cancel() throws SQLException {
		// TODO Auto-generated method stub
		logger.LOG().warning("Unsupported Operation !");

		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public void clearBatch() throws SQLException {
		// TODO Auto-generated method stub
		if (!this.IsClosed) {
			System.out.println("Batch is cleared.");
			logger.LOG().info("Batch is cleared.");

			this.batch.clear();
		} else{
			logger.LOG().warning("Cannot clear batch.Statement is closed !");

			throw new SQLException();
			
		}
	}

	@Override
	public void clearWarnings() throws SQLException {
		// TODO Auto-generated method stub
		logger.LOG().warning("Unsupported Operation !");

		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public void close() throws SQLException {
		// TODO Auto-generated method stub
		if (!this.IsClosed) {
			if (Rset != null) {
				this.Rset.close();
			}
			System.out.println("Statement is closed");
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
			logger.LOG().info("Statement is closed");

		}
	}

	@Override
	public void closeOnCompletion() throws SQLException {
		// TODO Auto-generated method stub
		logger.LOG().warning("Unsupported Operation !");

		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public boolean execute(String sql) throws SQLException {
		// TODO Auto-generated method stub

		if (!this.IsClosed) {
			logger.LOG().info("Executing"+ sql);
System.out.println("Executing query");
			String FirstWord = parse.Parse(sql);
			
			if (sql != null){
				ChooseQuery(FirstWord);
		
			}
			
			if (Rset != null)
				return true;

			return false;
		} else{
			logger.LOG().warning("Cannot execute. statement is closed !");

			throw new SQLException();
		}

	}

	private void CaseInsert() throws SQLException {
		if (parse.GetDBfound()) {
			counted = true;
			UpdateCount = InsertObject.Insert(parse.GetDBfound(), parse.GetCurrentDB(), parse.GetGetRest(),query,DetectObject);
          
			if (InsertObject.GetExecuted() == false || (UpdateCount == 0 && InsertObject.GetExecuted() == true)){
				OperationNotExecuted = true;
				throw new SQLException();

			}
			else
				logger.LOG().info("Update count = " + UpdateCount);

			
		} else{
			logger.LOG().warning("Cannot execute , no selectd database!");

			System.out.println("Invalid command. Select a Database first.");
		throw new SQLException();	
		}

	}

	private void CaseDelete() throws SQLException {
		if (parse.GetDBfound()) {
			counted = true;
			UpdateCount = DeleteObject.Delete(parse.GetDBfound(), parse.GetCurrentDB(), parse.GetGetRest() , query,DetectObject);
			if (DeleteObject.GetExecuted() == false|| (UpdateCount == 0 && DeleteObject.GetExecuted() == true)) {
				OperationNotExecuted = true;
				throw new SQLException();
	
			}
			else
				logger.LOG().info("Update count = " + UpdateCount);
		} else{
			logger.LOG().warning("Cannot execute , no selectd database!");

			System.out.println("Invalid command. Select a Database first.");
		throw new SQLException();	
		}

	}

	private void CaseUpdate() throws SQLException {
		if (parse.GetDBfound()) {
			counted = true;
			UpdateCount = UpdateObject.Update(parse.GetDBfound(), parse.GetCurrentDB(), parse.GetGetRest() , query,DetectObject);
			if (UpdateObject.GetExecuted() == false  || (UpdateCount == -1 && UpdateObject.GetExecuted() == true)) {
				OperationNotExecuted = true;
				throw new SQLException();
	
			}
			else
				logger.LOG().info("Update count = " + UpdateCount);
		} else{
			logger.LOG().warning("Cannot execute , no selectd database!");

			System.out.println("Invalid command. Select a Database first.");
		throw new SQLException();	
		}
	}

	private void CaseSelect() throws SQLException {
		if (parse.GetDBfound()) { 
			RsetFound = true;
			if(SelectObject.Select(parse.GetDBfound(),parse.GetCurrentDB(), parse.GetGetRest() , query,DetectObject) == null)
				Rset = null;
			else
			{Rset = new ResultSet(this,SelectObject.Select(parse.GetDBfound(),parse.GetCurrentDB(), parse.GetGetRest() , query,DetectObject));
			logger.LOG().info("Result Set found");
			}Type=SelectObject.Type;
            tableName=SelectObject.current_table1;
			if (SelectObject.GetExecuted() == false ){
				SelectNotExecuted = true;
				throw new SQLException();
	
			}
			
		} else{
			logger.LOG().warning("Cannot execute , no selectd database!");

			System.out.println("Invalid command. Select a Database first.");
		throw new SQLException();	
		}
	}

	private void CaseAlter() throws SQLException {
		if (parse.GetDBfound()) {
			counted = true;
			UpdateCount = AlterObject.Alter(parse.GetDBfound(), parse.GetCurrentDB(), parse.GetGetRest() ,query,DetectObject);
			UpdateCount = -1;
			if (AlterObject.GetExecuted() == false ){
				OperationNotExecuted = true;
				throw new SQLException();
	
			}
		} else{
			logger.LOG().warning("Cannot execute , no selectd database!");

			System.out.println("Invalid command. Select a Database first.");
		throw new SQLException();	
		}
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
			UpdateCount = -1;

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
			UpdateCount = -1;

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
			logger.LOG().warning("Invalid sql command!");

			throw new SQLException();
		}
		}
	}
	public void CaseUse() throws SQLException {
        String Name = parse.GetGetRest();
 
if(query.DetectDataBase(Name.toLowerCase())){
	logger.LOG().info("Using Database : " + Name);

            parse.SetCurrentlyUsed(Name.toLowerCase());
            parse.SetDBFound(true);
            System.out.println("database found");
        } else {
			logger.LOG().warning("Cannot Find Database : "+ Name);

            System.out.println("Invalid DataBase Name.");
            parse.SetDBFound(false);

            throw new SQLException();
        }
 
    }

	@Override
	public boolean execute(String sql, int autoGeneratedKeys) throws SQLException {
		// TODO Auto-generated method stub
		logger.LOG().warning("Unsupported Operation !");

		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public boolean execute(String sql, int[] columnIndexes) throws SQLException {
		// TODO Auto-generated method stub
		logger.LOG().warning("Unsupported Operation !");

		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public boolean execute(String sql, String[] columnNames) throws SQLException {
		// TODO Auto-generated method stub
		logger.LOG().warning("Unsupported Operation !");

		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public int[] executeBatch() throws SQLException {
		// TODO Auto-generated method stub
		if (!this.IsClosed) {
			logger.LOG().info("Executing Batch.");

			int[] counts = new int[batch.size()];
			for (int i = 0; i < batch.size(); i++) {
				logger.LOG().info("Executing sql"+ batch.get(i));

				execute(batch.get(i));
				if (counted && !OperationNotExecuted){
					counts[i] = UpdateCount;
					logger.LOG().info("Update Count"+ UpdateCount);

				}
				else if (!SelectNotExecuted && RsetFound){
					counts[i] = SUCCESS_NO_INFO;
					logger.LOG().info("Success no info");

				}
				else if ((SelectNotExecuted && RsetFound) || (counted && OperationNotExecuted)){
					counts[i] = EXECUTE_FAILED;
					logger.LOG().info("EXECUTE_FAILED");

				}
			}
			return counts;
		}

		else{
			logger.LOG().warning("Cannot execute , Statement is Closed!");

			throw new SQLException();
			
		}

	}

	@Override
	public ResultSet executeQuery(String sql) throws SQLException {
		// TODO Auto-generated method stub
		if (!IsClosed) {
			logger.LOG().info("Executing Query");

				execute(sql);
				
			if (RsetFound && !SelectNotExecuted){
				logger.LOG().info("Select Executed , Result set returned");
				return Rset;

			}
			else{
				logger.LOG().warning("Cannot execute , Select was not chosen for executeQuery");

				throw new SQLException();
			}
		} else {
			logger.LOG().warning("Cannot execute , Statement is Closed!");
			throw new SQLException();
		}
	}

	@Override
	public int executeUpdate(String sql) throws SQLException {
		// TODO Auto-generated method stub
		if (!IsClosed) {

			execute(sql);
			if (counted){
				logger.LOG().info("executeUpdate Succeeded");

				return UpdateCount;
			}
			else{
				logger.LOG().warning("Cannot execute , Select was chosen for executeUpdate");

				throw new SQLException();
			}
			
		} else {
			logger.LOG().warning("Cannot execute , Statement is Closed!");

			throw new SQLException();
		}

	}

	@Override
	public int executeUpdate(String sql, int autoGeneratedKeys) throws SQLException {
		// TODO Auto-generated method stub
		logger.LOG().warning("Unsupported Operation !");

		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public int executeUpdate(String sql, int[] columnIndexes) throws SQLException {
		// TODO Auto-generated method stub
		logger.LOG().warning("Unsupported Operation !");

		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public int executeUpdate(String sql, String[] columnNames) throws SQLException {
		// TODO Auto-generated method stub
		logger.LOG().warning("Unsupported Operation !");

		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public Connection getConnection() throws SQLException {
		// TODO Auto-generated method stub
		if (!IsClosed) {
			logger.LOG().warning("Returning Connection : " + this.connection);

			return this.connection;
		} else {
			logger.LOG().warning("Connection was not found");

			throw new SQLException();
		}

	}

	@Override
	public int getFetchDirection() throws SQLException {
		// TODO Auto-generated method stub
		logger.LOG().warning("Unsupported Operation !");

		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public int getFetchSize() throws SQLException {
		// TODO Auto-generated method stub
		logger.LOG().warning("Unsupported Operation !");

		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public ResultSet getGeneratedKeys() throws SQLException {
		// TODO Auto-generated method stub
		logger.LOG().warning("Unsupported Operation !");

		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public int getMaxFieldSize() throws SQLException {
		// TODO Auto-generated method stub
		logger.LOG().warning("Unsupported Operation !");

		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public int getMaxRows() throws SQLException {
		// TODO Auto-generated method stub
		logger.LOG().warning("Unsupported Operation !");

		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public boolean getMoreResults() throws SQLException {
		// TODO Auto-generated method stub
		logger.LOG().warning("Unsupported Operation !");

		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public boolean getMoreResults(int current) throws SQLException {
		// TODO Auto-generated method stub
		logger.LOG().warning("Unsupported Operation !");

		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public int getQueryTimeout() throws SQLException {
		// TODO Auto-generated method stub
		logger.LOG().warning("Unsupported Operation !");

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
		logger.LOG().warning("Unsupported Operation !");

		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public int getResultSetHoldability() throws SQLException {
		// TODO Auto-generated method stub
		logger.LOG().warning("Unsupported Operation !");

		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public int getResultSetType() throws SQLException {
		// TODO Auto-generated method stub
		logger.LOG().warning("Unsupported Operation !");

		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public int getUpdateCount() throws SQLException {
		// TODO Auto-generated method stub
		if (!IsClosed){
			logger.LOG().info("returning update count : "+ UpdateCount);

			return UpdateCount;
		}
		else{
			logger.LOG().warning("Can not execute getUpdateCount");

			throw new SQLException();
		}
	}

	@Override
	public SQLWarning getWarnings() throws SQLException {
		// TODO Auto-generated method stub
		logger.LOG().warning("Unsupported Operation !");

		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public boolean isCloseOnCompletion() throws SQLException {
		// TODO Auto-generated method stub
		logger.LOG().warning("Unsupported Operation !");

		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public boolean isClosed() throws SQLException {
		// TODO Auto-generated method stub
		logger.LOG().warning("Unsupported Operation !");

		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public boolean isPoolable() throws SQLException {
		// TODO Auto-generated method stub
		logger.LOG().warning("Unsupported Operation !");

		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void setCursorName(String name) throws SQLException {
		// TODO Auto-generated method stub
		logger.LOG().warning("Unsupported Operation !");

		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void setEscapeProcessing(boolean enable) throws SQLException {
		// TODO Auto-generated method stub
		logger.LOG().warning("Unsupported Operation !");

		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void setFetchDirection(int direction) throws SQLException {
		// TODO Auto-generated method stub
		logger.LOG().warning("Unsupported Operation !");

		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void setFetchSize(int rows) throws SQLException {
		// TODO Auto-generated method stub
		logger.LOG().warning("Unsupported Operation !");

		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void setMaxFieldSize(int max) throws SQLException {
		// TODO Auto-generated method stub
		logger.LOG().warning("Unsupported Operation !");

		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void setMaxRows(int max) throws SQLException {
		// TODO Auto-generated method stub
		logger.LOG().warning("Unsupported Operation !");

		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void setPoolable(boolean poolable) throws SQLException {
		// TODO Auto-generated method stub
		logger.LOG().warning("Unsupported Operation !");

		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void setQueryTimeout(int seconds) throws SQLException {
		// TODO Auto-generated method stub
		logger.LOG().warning("Unsupported Operation !");

		throw new java.lang.UnsupportedOperationException();

	}

}
