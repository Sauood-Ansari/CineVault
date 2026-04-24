package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import controller.MovieController;

public class AddMoviePage extends JPanel {

    private JTextField title, genre, year, rating;
    private JButton addMovieButton;
    private MovieController movieController = new MovieController();

    public AddMoviePage() {
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(20, 20, 20, 20));

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true),
            new EmptyBorder(30, 50, 30, 50)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(12, 10, 12, 10);

        JLabel headerLabel = new JLabel("Add New Movie", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        gbc.gridx = 0; 
        gbc.gridy = 0;
        gbc.gridwidth = 2; 
        formPanel.add(headerLabel, gbc);

        gbc.gridwidth = 1; 
        gbc.gridy = 1; 
        gbc.gridx = 0;
        JLabel nameLabel = new JLabel("Movie Name:");
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        formPanel.add(nameLabel, gbc);

        gbc.gridx = 1;
        title = new JTextField(15);
        title.setPreferredSize(new Dimension(250, 35));
        formPanel.add(title, gbc);

        gbc.gridy = 2; 
        gbc.gridx = 0;
        JLabel genreLabel = new JLabel("Movie Genre:");
        genreLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        formPanel.add(genreLabel, gbc);

        gbc.gridx = 1;
        genre = new JTextField(15);
        genre.setPreferredSize(new Dimension(250, 35));
        formPanel.add(genre, gbc);

        gbc.gridy = 3; 
        gbc.gridx = 0;
        JLabel yearLabel = new JLabel("Release Year:");
        yearLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        formPanel.add(yearLabel, gbc);

        gbc.gridx = 1;
        year = new JTextField(15);
        year.setPreferredSize(new Dimension(250, 35));
        formPanel.add(year, gbc);

        gbc.gridy = 4; 
        gbc.gridx = 0;
        JLabel ratingLabel = new JLabel("Rating (Out of 5):");
        ratingLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        formPanel.add(ratingLabel, gbc);

        gbc.gridx = 1;
        rating = new JTextField(15);
        rating.setPreferredSize(new Dimension(250, 35));
        formPanel.add(rating, gbc);

        gbc.gridy = 5; 
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(30, 10, 10, 10);
        
        addMovieButton = new JButton("Add Movie");
        addMovieButton.setPreferredSize(new Dimension(200, 40));
        addMovieButton.setBackground(new Color(41, 128, 185));
        addMovieButton.setForeground(Color.WHITE);
        addMovieButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        addMovieButton.setFocusPainted(false);
        addMovieButton.setOpaque(true);
        addMovieButton.setBorderPainted(false);
        addMovieButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        formPanel.add(addMovieButton, gbc);

        JPanel centerWrapper = new JPanel(new GridBagLayout());
        centerWrapper.add(formPanel);
        add(centerWrapper, BorderLayout.CENTER);

        addMovieButton.addActionListener(e -> {
            String movieTitle = title.getText().trim();
            String movieGenre = genre.getText().trim();
            String movieYear = year.getText().trim();
            String movieRating = rating.getText().trim();

            if (movieTitle.isEmpty() || movieGenre.isEmpty() ||
                movieYear.isEmpty() || movieRating.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields!", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int yearValue;
            try {
                yearValue = Integer.parseInt(movieYear);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Year must be a valid number!", "Error", JOptionPane.ERROR_MESSAGE);
                year.requestFocus();
                return;
            }

            double ratingValue;
            try {
                ratingValue = Double.parseDouble(movieRating);
                if (ratingValue < 0 || ratingValue > 5) {
                    JOptionPane.showMessageDialog(this, "Rating must be between 0 and 5!", "Error", JOptionPane.ERROR_MESSAGE);
                    rating.requestFocus();
                    return;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Rating must be a number!", "Error", JOptionPane.ERROR_MESSAGE);
                rating.requestFocus();
                return;
            }

            movieController.addMovie(movieTitle, movieGenre, movieYear, movieRating);
            JOptionPane.showMessageDialog(this, "Movie Added Successfully!");

            title.setText("");
            genre.setText("");
            year.setText("");
            rating.setText("");
        });
    }
}