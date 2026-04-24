package controller;

import java.sql.SQLException;

import utils.Validator;
import java.util.List;
import dao.MovieDAO;
import model.Movie;

// Handles business logic related to movies
public class MovieController 
{
    // DAO for DB operations
    private final MovieDAO movieDAO = new MovieDAO();

    // Adds a new movie after validation
    public String addMovie(String title, String genre, String yearText, String ratingText)
    {
        // Check for empty inputs
        if (Validator.isBlank(title) || Validator.isBlank(genre) || Validator.isBlank(yearText) || Validator.isBlank(ratingText)) {
            return "All fields are required.";
        }

        int year;
        double rating;

        try {
            // Convert input strings to numeric values
            year = Integer.parseInt(yearText.trim());
            rating = Double.parseDouble(ratingText.trim());
        } catch (NumberFormatException e) {
            return "Year and rating must be numeric.";
        }

        // Validate year range 
        if (!Validator.isValidMovieYear(year)) {
            return "Year is out of valid range.";
        }

        // Create Movie object
        Movie movie = new Movie(title.trim(), genre.trim(), year, rating);

        try {
            // Save movie in database
            return movieDAO.addMovie(movie) ? "SUCCESS" : "Movie could not be added.";
        } catch (SQLException e) {
            return "Unable to add movie: " + e.getMessage();
        }
    }

    // Deletes a movie by ID
    public boolean removeMovie(int movieId)
    {
        try {
            return movieDAO.deleteMovie(movieId);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Updates rating of a movie
    public boolean updateMovieRating(int movieId, double newRating)
    {
        try {
            return movieDAO.updateMovieRating(movieId, newRating);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Fetches all movies from database
    public List<Movie> fetchAllMovies()
    {
        try {
            return movieDAO.fetchAllMovies();
        } catch (SQLException e) {
            e.printStackTrace();
            return List.of();
        }
    }

    // Returns total number of movies
    public int getTotalMovies() 
    {
        return movieDAO.getTotalMovies();
    }

    // Returns total movies filtered by genre
    public int getTotalMoviesByGenre(String genre) 
    {
        return movieDAO.getTotalMoviesByGenre(genre);
    }
}
