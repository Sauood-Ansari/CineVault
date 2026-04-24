package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;
import model.Movie;

public class MovieDAO
{   
    // Inserts a new movie record into the database
    public boolean addMovie(Movie movie) throws SQLException 
    {
        // Try-with-resources ensures connection closes automatically
        try (Connection conn = DBConnection.getConnection())
        {
            String query = "INSERT INTO movies(title, genre, year, rating) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);

            // Setting values from Movie object to SQL query
            stmt.setString(1, movie.getTitle());
            stmt.setString(2, movie.getGenre());
            stmt.setInt(3, movie.getYear());
            stmt.setDouble(4, movie.getRating());

            // executeUpdate returns number of rows affected
            return stmt.executeUpdate() > 0;
        }
    }

    // Updates rating of a specific movie using its ID
    public boolean updateMovieRating(int movieId, double newRating) throws SQLException
    {
        try (Connection conn = DBConnection.getConnection())
        {
            String query = "UPDATE movies SET rating=? WHERE id=?";
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setDouble(1, newRating);
            stmt.setInt(2, movieId);

            return stmt.executeUpdate() > 0;
        }
    }

    // Deletes a movie record based on ID
    public boolean deleteMovie(int movieId) throws SQLException
    {
        try (Connection conn = DBConnection.getConnection())
        {
            String query = "DELETE FROM movies WHERE id=?";
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setInt(1, movieId);

            return stmt.executeUpdate() > 0;
        }
    }
    
    // Fetches all movies from database and returns as a list
    public List<Movie> fetchAllMovies() throws SQLException
    {
        List<Movie> movies = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection())
        {
            String query = "SELECT * FROM movies";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            // Iterate over result set and convert each row into Movie object
            while (rs.next()) {
                Movie movie = new Movie(rs.getInt("id"),rs.getString("title"),rs.getString("genre"),rs.getInt("year"),rs.getDouble("rating"));
                movies.add(movie);
            }
        }

        return movies;
    }

    // Returns total number of movies in the database
    public int getTotalMovies()
    {
        String sql = "SELECT COUNT(*) FROM movies";

        try(Connection conn = DBConnection.getConnection(); 
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery())
            {
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
    
    // Returns total number of movies filtered by genre
    public int getTotalMoviesByGenre(String genre)
    {
        String sql = "SELECT COUNT(*) FROM movies WHERE genre = ?";
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql))
        {
            // Set genre parameter
            stmt.setString(1, genre);
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
    
    //Editing Movies
    public boolean updateMovie(int id, String title, String genre, String year, double rating) throws SQLException {

    	try (Connection conn = DBConnection.getConnection())
        {
        String sql = "UPDATE movies SET title=?, genre=?, year=?, rating=? WHERE id=?";

        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, title);
        ps.setString(2, genre);
        ps.setString(3, year);   // 🔥 stays TEXT
        ps.setDouble(4, rating);
        ps.setInt(5, id);

        return ps.executeUpdate() > 0;
        }
    }
}