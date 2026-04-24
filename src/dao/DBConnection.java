package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

// class to manage database connection
public class DBConnection 
{  
    // Database URL
    private static final String URL = "jdbc:sqlite:" + System.getProperty("user.dir") + "/database/mydb.db";
    
    // Returns a new database connection
    public static Connection getConnection() throws SQLException
    {
        // DriverManager establishes connection using given URL
        Connection conn = DriverManager.getConnection(URL);

        // Initialize tables
        initializeDatabase(conn);

        return conn;
    }

    private static void initializeDatabase(Connection conn)
    {
        String createMoviesTable = """
            CREATE TABLE IF NOT EXISTS movies (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                title TEXT NOT NULL,
                genre TEXT,
                year TEXT,
                rating REAL
            );
        """;

        String createUsersTable = """
            CREATE TABLE IF NOT EXISTS users (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                username TEXT NOT NULL UNIQUE,
                password TEXT NOT NULL
            );
        """;

        try (Statement stmt = conn.createStatement())
        {
            stmt.execute(createMoviesTable);
            stmt.execute(createUsersTable);
        } 
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}