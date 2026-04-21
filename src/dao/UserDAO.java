package dao;

import java.util.jdbc.DriverManager;

public class userDAO 
{
    Connection conn=null;

    private static final String URL = "";
    
    public static String getPassword(Strign username)
    {
        try(conn =DriverManager.getConnection(URL))
        {
            if(conn!=null)
            {
                String query="Select password from users where username=?";
                PreparedStatement stmt=conn.prepareStatement(query);
                stmt.setString(1, username);
                ResultSet rs=stmt.executeQuery();
                if(rs.next())
                {
                    return rs.getString("password");
                }
                else 
                {
                    return null;
                }
            }
        }
        catch(SQLException e)
        {
            System.out.println("An error occurred while connecting to the database: " + e.getMessage());
        }
    }

    public static void Registration(String username, String password)
    {
        try(conn=DriverManager.getConnection(URL))
        {
            if(conn!=null)
            {
                String query="Insert into users(username,password) values(?,?)";
                PreparedStatement stmt=conn.prepareStatement(query);
                stmt.setString(1, username);
                stmt.setString(2, password);
                int rowsAffected=stmt.executeUpdate();
                if(rowsAffected>0)
                {
                    System.out.println("Registration successful!");
                }
                else
                {
                    System.out.println("Registration failed. Please try again.");
                }
            }
        }
        catch(SQLException e)
        {
            System.out.println("An error occurred while connecting to the database: " + e.getMessage());
        }
    }
}
