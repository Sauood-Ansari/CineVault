package controller;

import dao.RatingDAO;
import model.Movie;
import utils.SessionManager;

import java.sql.SQLException;
import java.util.List;

// Handles business logic related to movie ratings
public class RatingController {

    private final RatingDAO ratingDAO = new RatingDAO(); // DAO for DB queries

    // Fetches movies with rating >= given value for current user
    public List<Movie> getMoviesByRating(double rating) {
        try {
            // Delegate DB operation to DAO
            int userId = SessionManager.getCurrentUser().getId();
            return ratingDAO.getMoviesByRating(rating, userId);
        } catch (SQLException e) {
            e.printStackTrace(); // basic error handling
            return List.of(); // return empty list on failure
        }
    }
}