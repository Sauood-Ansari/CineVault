package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Movie;

// DAO for fetching movies based on rating
public class RatingDAO
{
    // Returns list of movies with rating >= given value
    public List<Movie> getMoviesByRating(double rating) throws SQLException
    {
        List<Movie> movies = new ArrayList<>();

        // Auto-closes connection after execution
        try (Connection conn = DBConnection.getConnection()) {

            // PreparedStatement prevents SQL injection
            String query = "SELECT id, title, genre, year, rating FROM movies WHERE rating >= ?";
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setDouble(1, rating); // set rating parameter
            ResultSet rs = stmt.executeQuery(); // execute query

            // Convert each row into Movie object
            while (rs.next()) {
                Movie movie = new Movie(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("genre"),
                    rs.getInt("year"),
                    rs.getDouble("rating")
                );
                movies.add(movie);
            }
        }

        return movies;
    }
}