package ru.otus.l91;

import java.sql.*;

public class DBService implements AutoCloseable {

    private final Connection connection;

    public DBService() throws SQLException {
        String connectionUrl = "jdbc:sqlserver://localhost:1434;databaseName=myORM;user=sa;password=1qaz!QAZ;";
        this.connection = DriverManager.getConnection(connectionUrl);
    }

    public Connection getConnection() {
        return connection;
    }

    public void createTable() throws SQLException, Exception {
        final String query = "CREATE TABLE [dbo].[DataSet]([id] [bigint] IDENTITY(1,1) NOT NULL," +
                " [name] [varchar](255) NULL, [age] [int] NULL) ON [PRIMARY]";
        Statement statement = getConnection().createStatement();
        statement.execute(query);
        statement.close();
        close();
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }
}

