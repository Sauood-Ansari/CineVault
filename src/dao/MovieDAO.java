package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import model.Movie;

public class MovieDAO
{   
    // Inserts a new movie record into the database, linked to a specific user
    public boolean addMovie(Movie movie, int userId) throws SQLException 
    {
        // Try-with-resources ensures connection closes automatically
        try (Connection conn = DBConnection.getConnection())
        {
            String query = "INSERT INTO movies(title, genre, year, rating, user_id) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);

            // Setting values from Movie object to SQL query
            stmt.setString(1, movie.getTitle());
            stmt.setString(2, movie.getGenre());
            stmt.setInt(3, movie.getYear());
            stmt.setDouble(4, movie.getRating());
            stmt.setInt(5, userId);

            // executeUpdate returns number of rows affected
            return stmt.executeUpdate() > 0;
        }
    }

    // Updates rating of a specific movie using its ID, scoped to user
    public boolean updateMovieRating(int movieId, double newRating, int userId) throws SQLException
    {
        try (Connection conn = DBConnection.getConnection())
        {
            String query = "UPDATE movies SET rating=? WHERE id=? AND user_id=?";
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setDouble(1, newRating);
            stmt.setInt(2, movieId);
            stmt.setInt(3, userId);

            return stmt.executeUpdate() > 0;
        }
    }

    // Deletes a movie record based on ID, scoped to user
    public boolean deleteMovie(int movieId, int userId) throws SQLException
    {
        try (Connection conn = DBConnection.getConnection())
        {
            String query = "DELETE FROM movies WHERE id=? AND user_id=?";
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setInt(1, movieId);
            stmt.setInt(2, userId);

            return stmt.executeUpdate() > 0;
        }
    }
    
    // Fetches all movies for a specific user from database
    public List<Movie> fetchAllMovies(int userId) throws SQLException
    {
        List<Movie> movies = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection())
        {
            String query = "SELECT * FROM movies WHERE user_id=?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            // Iterate over result set and convert each row into Movie object
            while (rs.next()) {
                Movie movie = new Movie(rs.getInt("id"),rs.getString("title"),rs.getString("genre"),rs.getInt("year"),rs.getDouble("rating"));
                movies.add(movie);
            }
        }

        return movies;
    }

    // Returns total number of movies for a specific user
    public int getTotalMovies(int userId)
    {
        String sql = "SELECT COUNT(*) FROM movies WHERE user_id=?";

        try(Connection conn = DBConnection.getConnection(); 
            PreparedStatement stmt = conn.prepareStatement(sql))
            {
                stmt.setInt(1, userId);
                ResultSet rs = stmt.executeQuery();
                // If result exists, return count
                if(rs.next()){
                    return rs.getInt(1);
                }
            }
            catch(SQLException e){
                e.printStackTrace();
            }
        return 0; // Default if error occurs
    }
    
    // Returns total number of movies filtered by genre for a specific user
    public int getTotalMoviesByGenre(String genre, int userId)
    {
        String sql = "SELECT COUNT(*) FROM movies WHERE genre = ? AND user_id=?";
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql))
        {
            // Set parameters
            stmt.setString(1, genre);
            stmt.setInt(2, userId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }

        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        return 0;
    }
    
    //Editing Movies, scoped to user
    public boolean updateMovie(int id, String title, String genre, String year, double rating, int userId) throws SQLException {

    	try (Connection conn = DBConnection.getConnection())
        {
        String sql = "UPDATE movies SET title=?, genre=?, year=?, rating=? WHERE id=? AND user_id=?";

        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, title);
        ps.setString(2, genre);
        ps.setString(3, year);
        ps.setDouble(4, rating);
        ps.setInt(5, id);
        ps.setInt(6, userId);

        return ps.executeUpdate() > 0;
        }
    }
}