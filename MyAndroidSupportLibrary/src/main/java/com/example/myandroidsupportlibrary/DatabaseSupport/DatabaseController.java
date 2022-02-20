package com.example.myandroidsupportlibrary.DatabaseSupport;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseController
{
    /* This class is used to establish a connection to a database and then perform operations
     * like queries or updates (add, modify or delete items) to the tables in the database.
     * This class works with all kinds of databases as long as the appropiate jdbc driver is
     * embedded in the app that uses this library.
     */

    private String connectionString;   //The connection string specifies the database server location and the exact database we are connecting to
    private String username;
    private String password;
    private Connection connection;
    private Statement statement;
    private  String driverClassName;

    /* To connect to a database all that is required is a connection string which identifies
     * the database server and its network location and valid username and password credentials
     * to be able to gain access. */
    public DatabaseController(String connectionString,
                              String username,
                              String password)
    {
        this.connectionString = connectionString;
        this.username = username;
        this.password = password;
    }

    /* Receives a string with the class path for the java class implementing
     * the database driver and loads it into memory. Throws a class not found
     * exception if the driver can't be found. */
    public void setDriver(String driverClassName) throws ClassNotFoundException
    {
        if (!driverClassName.isEmpty())
        {
            this.driverClassName = driverClassName;
            Class.forName(driverClassName);
        }
    }

    //Open a connection to the database
    public boolean openConnection()
    {
        boolean success = false;

        try
        {
            Class.forName(driverClassName);
            connection = DriverManager.getConnection(connectionString, username, password);
            success = true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return success;
    }

    //Execute a query that returns some table results, returns a ResultSet with those table records.
    public ResultSet executeResultsQuery(String query)
    {
        ResultSet resultSet = null;

        try
        {
            if (!query.isEmpty())
            {
                statement = connection.createStatement();
                resultSet = statement.executeQuery(query);
            }
        }
        catch (SQLException exception)
        {
            exception.printStackTrace();
        }
        return resultSet;
    }

    /* Execute a query that returns no table results, these are basically inserting, modifying and deleting specific records in a table.
     * Returns the number of records modified. */
    public int executeUpdateQuery(String query)
    {
        int rowsModified = 0;

        try
        {
            if (!query.isEmpty())
            {
                statement = connection.createStatement();
                rowsModified = statement.executeUpdate(query);
                statement.close();
            }
        }
        catch (SQLException exception)
        {
            exception.printStackTrace();
        }
        return rowsModified;
    }

    //Once we are done with a result set we should always release its resources by calling this method
    public void closeResultSet(ResultSet resultSet)
    {
        try
        {
            if (resultSet != null)
            {
                resultSet.close();
                statement.close();
            }
        }
        catch (SQLException exception)
        {
            exception.printStackTrace();
        }
    }

    public void closeConnection()
    {
        try
        {
            connection.close();
        }
        catch (SQLException exception)
        {
            exception.printStackTrace();
        }
    }






}
