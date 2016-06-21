

package java.sql;

import java.util.Properties;
import java.util.concurrent.Executor;


public interface Connection  extends Wrapper, AutoCloseable {

    
    Statement createStatement() throws SQLException;

    
    PreparedStatement prepareStatement(String sql)
        throws SQLException;

    
    CallableStatement prepareCall(String sql) throws SQLException;

    
    String nativeSQL(String sql) throws SQLException;

    
    void setAutoCommit(boolean autoCommit) throws SQLException;

    
    boolean getAutoCommit() throws SQLException;

    
    void commit() throws SQLException;

    
    void rollback() throws SQLException;

    
    void close() throws SQLException;

    
    boolean isClosed() throws SQLException;

    
    

    
    DatabaseMetaData getMetaData() throws SQLException;

    
    void setReadOnly(boolean readOnly) throws SQLException;

    
    boolean isReadOnly() throws SQLException;

    
    void setCatalog(String catalog) throws SQLException;

    
    String getCatalog() throws SQLException;

    
    int TRANSACTION_NONE             = 0;

    
    int TRANSACTION_READ_UNCOMMITTED = 1;

    
    int TRANSACTION_READ_COMMITTED   = 2;

    
    int TRANSACTION_REPEATABLE_READ  = 4;

    
    int TRANSACTION_SERIALIZABLE     = 8;

    
    void setTransactionIsolation(int level) throws SQLException;

    
    int getTransactionIsolation() throws SQLException;

    
    SQLWarning getWarnings() throws SQLException;

    
    void clearWarnings() throws SQLException;


    

    
    Statement createStatement(int resultSetType, int resultSetConcurrency)
        throws SQLException;

    
    PreparedStatement prepareStatement(String sql, int resultSetType,
                                       int resultSetConcurrency)
        throws SQLException;

    
    CallableStatement prepareCall(String sql, int resultSetType,
                                  int resultSetConcurrency) throws SQLException;

    
    java.util.Map<String,Class<?>> getTypeMap() throws SQLException;

    
    void setTypeMap(java.util.Map<String,Class<?>> map) throws SQLException;

    


    
    void setHoldability(int holdability) throws SQLException;

    
    int getHoldability() throws SQLException;

    
    Savepoint setSavepoint() throws SQLException;

    
    Savepoint setSavepoint(String name) throws SQLException;

    
    void rollback(Savepoint savepoint) throws SQLException;

    
    void releaseSavepoint(Savepoint savepoint) throws SQLException;

    
    Statement createStatement(int resultSetType, int resultSetConcurrency,
                              int resultSetHoldability) throws SQLException;

    
    PreparedStatement prepareStatement(String sql, int resultSetType,
                                       int resultSetConcurrency, int resultSetHoldability)
        throws SQLException;

    
    CallableStatement prepareCall(String sql, int resultSetType,
                                  int resultSetConcurrency,
                                  int resultSetHoldability) throws SQLException;


    
    PreparedStatement prepareStatement(String sql, int autoGeneratedKeys)
        throws SQLException;

    
    PreparedStatement prepareStatement(String sql, int columnIndexes[])
        throws SQLException;

    
    PreparedStatement prepareStatement(String sql, String columnNames[])
        throws SQLException;

    
    Clob createClob() throws SQLException;

    
    Blob createBlob() throws SQLException;

    
    NClob createNClob() throws SQLException;

    
    SQLXML createSQLXML() throws SQLException;

        
         boolean isValid(int timeout) throws SQLException;

        
         void setClientInfo(String name, String value)
                throws SQLClientInfoException;

        
         void setClientInfo(Properties properties)
                throws SQLClientInfoException;

        
         String getClientInfo(String name)
                throws SQLException;

        
         Properties getClientInfo()
                throws SQLException;


 Array createArrayOf(String typeName, Object[] elements) throws
SQLException;


 Struct createStruct(String typeName, Object[] attributes)
throws SQLException;

   

   
    void setSchema(String schema) throws SQLException;

    
    String getSchema() throws SQLException;

    
    void abort(Executor executor) throws SQLException;

    
    void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException;


    
    int getNetworkTimeout() throws SQLException;
}
