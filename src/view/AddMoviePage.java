package view;

import java.awt.Dimension;
import javax.swing.*;
import controller.MovieController;

public class AddMoviePage extends JPanel {

    private JTextField title, genre, year, rating;
    private JButton addMovieButton;
    private MovieController movieController = new MovieController();

    @SuppressWarnings("unused")
	public AddMoviePage() 
    {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        title = new JTextField(20);
        genre = new JTextField(20);
        year = new JTextField(20);
        rating = new JTextField(20);

        add(new JLabel("Movie Name:"));
        add(title);

        add(Box.createRigidArea(new Dimension(0, 10)));

        add(new JLabel("Movie Genre:"));
        add(genre);

        add(Box.createRigidArea(new Dimension(0, 10)));

        add(new JLabel("Release Year:"));
        add(year);

        add(Box.createRigidArea(new Dimension(0, 10)));

        add(new JLabel("Rating (Out of 5):"));
        add(rating);

        add(Box.createRigidArea(new Dimension(0, 20)));

        addMovieButton = new JButton("Add Movie");
        addMovieButton.setAlignmentX(CENTER_ALIGNMENT);

        add(addMovieButton);

        // action listner
        addMovieButton.addActionListener(e -> {

            String movieTitle = title.getText().trim();
            String movieGenre = genre.getText().trim();
            String movieYear = year.getText().trim();
            String movieRating = rating.getText().trim();

            //Empty check
            if (movieTitle.isEmpty() || movieGenre.isEmpty() ||
                movieYear.isEmpty() || movieRating.isEmpty()) {

                JOptionPane.showMessageDialog(
                    this,
                    "Please fill all fields!",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            //Year must be integer
            int yearValue;
            try {
                yearValue = Integer.parseInt(movieYear);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(
                    this,
                    "Year must be a valid number!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );
                year.requestFocus();
                return;
            }

            //Rating must be number between 0 and 5
            double ratingValue;
            try {
                ratingValue = Double.parseDouble(movieRating);

                if (ratingValue < 0 || ratingValue > 5) {
                    JOptionPane.showMessageDialog(
                        this,
                        "Rating must be between 0 and 5!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                    );
                    rating.requestFocus();
                    return;
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(
                    this,
                    "Rating must be a number!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );
                rating.requestFocus();
                return;
            }

            //If everything is valid
            movieController.addMovie(movieTitle, movieGenre, movieYear, movieRating);

            JOptionPane.showMessageDialog(this, "Movie Added Successfully!");

            // Clear fields
            title.setText("");
            genre.setText("");
            year.setText("");
            rating.setText("");
        });
    }
}