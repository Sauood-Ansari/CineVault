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
        try {
        Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new SQLException("SQLite JDBC driver not found.", e);
        }

        Connection conn = DriverManager.getConnection(URL);

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
                rating REAL,
                user_id INTEGER REFERENCES users(id)
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
            stmt.execute(createUsersTable);
            stmt.execute(createMoviesTable);

            // Safe migration: add user_id column to existing movies table
            // SQLite will throw if column already exists, so we catch and ignore
            try {
                stmt.execute("ALTER TABLE movies ADD COLUMN user_id INTEGER REFERENCES users(id)");
            } catch (SQLException e) {
                // Column already exists, ignore
            }
        } 
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}