package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBConnection 
{  
    private static final String URL="";

    public static Connection getConnection() throws SQLException
    {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
