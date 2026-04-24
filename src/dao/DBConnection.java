package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// class to manage database connection
public class DBConnection 
{  
    // Database URL
    private static final String URL = "";

    // Returns a new database connection
    public static Connection getConnection() throws SQLException
    {
        // DriverManager establishes connection using given URL
        return DriverManager.getConnection(URL);
    }
}