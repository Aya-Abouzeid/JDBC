# JDBC

## Description:

Java Database Connectivity (JDBC) provides Java developers with a standard API that is used to access databases, 
regardless of the driver and database product.
JDBC presents a uniform interface to databases - change vendors and your applications only need to change their driver.

## Specs:

-  support these new data types (Date, Float).

- Implemented the JDBC main interfaces to access the tables' data.

- The following interfaces/methods are implemented:
- java.sql.Driver
```R
       acceptsURL(String url)
       connect(String url, Properties info)
       getPropertyInfo(String url, Properties info)
```
- java.sql.Connection
```R
       close()
       createStatement()
```

- java.sql.Statement
```R
       addBatch(String sql)
       clearBatch()
       close()
       execute(String sql)
       executeBatch()
       executeQuery(String sql)
       executeUpdate(String sql)
       getConnection()
       getQueryTimeout()
       setQueryTimeout(int seconds)
       getResultSet()
       getUpdateCount()
 ```
- java.sql.Resultset
```R
       absolute(int row)
       afterLast()
       beforeFirst()
       close()
       findColumn(String columnLabel)
       first()
       getInt(int columnIndex)
       getInt(String columnLabel)
       getDate(int columnIndex)
       getDate(String columnLabel)
       getString(int columnIndex)
       getString(String columnLabel)
       getFloat(int columnIndex)
       getFloat(String columnLabel)
       getMetaData()
       getObject(int columnIndex)
       getStatement()
       isAfterLast()
       isBeforeFirst()
       isClosed()
       isFirst()
       isLast()
       last()
       next()
       previous()
```
- java.sql.ResultSetMetaData
```R
       getColumnCount()
       getColumnLabel(int column)
       getColumnName(int column)
       getColumnType(int column)
       getTableName(int column)
```

- Any other method in the interfaces not mentioned above have empty implementation that
  throws java.lang.UnsupportedOperationException.   

-Complete log of the operations done on the database through the DBMS are shown. Operations include initiating DB connections,
  query execution, errors and warnings, connections closing. Operations timestamp are indicated.
  
- JUnit is used to write test scenarios that cover all the functionalities, errors and possible use cases for a developer
   that uses the JDBC driver.
   
 ## Resources:
 
 - JDBC Tutorial: http://docs.oracle.com/javase/tutorial/jdbc/
-  DBMS: http://en.wikipedia.org/wiki/Database_management_system
