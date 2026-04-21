package dao;

import java.util.jdbc.DriverManager;

public class MovieDAO
{    
    public static void addMovie(String title, String genre, int year,Double rating)
    {
        try(conn=DBConnection.getConnection())
        {
            if(conn!=null)
            {
                String query="Insert into movies(title,genre,year,rating) values(?,?,?,?)";
                PreparedStatement stmt=conn.prepareStatement(query);
                stmt.setString(1, title);
                stmt.setString(2, genre);
                stmt.setInt(3, year);
                stmt.setDouble(4, rating);
                int rowsAffected=stmt.executeUpdate();
                if(rowsAffected>0)
                {
                    System.out.println("Movie added successfully!");
                }
                else
                {
                    System.out.println("Failed to add movie. Please try again.");
                }
            }
        }
        catch(SQLException e)
        {
            System.out.println("An error occurred while connecting to the database: " + e.getMessage());
        }
    }

    public static void updateMovieRating(int movieId, double newRating)
    {
        try(conn=DBConnection.getConnection())
        {
            if(conn!=null)
            {
                String query="Update movies set rating=? where id=?";
                PreparedStatement stmt=conn.prepareStatement(query);
                stmt.setDouble(1, newRating);
                stmt.setInt(2, movieId);
                int rowsAffected=stmt.executeUpdate();
                if(rowsAffected>0)
                {
                    System.out.println("Movie rating updated successfully!");
                }
                else
                {
                    System.out.println("Failed to update movie rating. Please try again.");
                }
            }
        }
        catch(SQLException e)
        {
            System.out.println("An error occurred while connecting to the database: " + e.getMessage());
        }
    }

    public static void deleteMovie(int movieId)
    {
        try(conn=DriverManager.getConnection(URL))
        {
            if(conn!=null)
            {
                String query="Delete from movies where id=?";
                PreparedStatement stmt=conn.prepareStatement(query);
                stmt.setInt(1, movieId);
                int rowsAffected=stmt.executeUpdate();
                if(rowsAffected>0)
                {
                    System.out.println("Movie deleted successfully!");
                }
                else
                {
                    System.out.println("Failed to delete movie. Please try again.");
                }
            }
        }
        catch(SQLException e)
        {
            System.out.println("An error occurred while connecting to the database: " + e.getMessage());
        }
    }
    
    public static void fetchAllMovies()
    {
        try(conn=DriverManager.getConnection(URL))
        {
            if(conn!=null)
            {
                String query="Select * from movies";
                Statement stmt=conn.createStatement();
                ResultSet rs=stmt.executeQuery(query);
                System.out.println("All Movies:");
                while(rs.next())
                {
                    int id=rs.getInt("id");
                    String title=rs.getString("title");
                    String genre=rs.getString("genre");
                    int year=rs.getInt("year");
                    double rating=rs.getDouble("rating");
                    System.out.println("ID: " + id + ", Title: " + title + ", Genre: " + genre + ", Year: " + year + ", Rating: " + rating);
                }
            }
        }
        catch(SQLException e)
        {
            System.out.println("An error occurred while connecting to the database: " + e.getMessage());
        }
    }
}