package controller;

import dao.RatingDAO;
import model.Movie;

import java.sql.SQLException;
import java.util.List;

// Handles business logic related to movie ratings
public class RatingController {

    private final RatingDAO ratingDAO = new RatingDAO(); // DAO for DB queries

    // Fetches movies with rating >= given value
    public List<Movie> getMoviesByRating(double rating) {
        try {
            // Delegate DB operation to DAO
            return ratingDAO.getMoviesByRating(rating);
        } catch (SQLException e) {
            e.printStackTrace(); // basic error handling
            return List.of(); // return empty list on failure
        }
    }
}