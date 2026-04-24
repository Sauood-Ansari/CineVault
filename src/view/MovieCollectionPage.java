package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

import controller.MovieController;
import model.Movie;

public class MovieCollectionPage extends JPanel {

    private JTable table;
    private DefaultTableModel tableModel;
    private MovieController movieController;

    public MovieCollectionPage() {

        setLayout(new BorderLayout());

        movieController = new MovieController();

        // ID included (important)
        String[] columns = {"ID", "Title", "Genre", "Year", "Rating"};
        tableModel = new DefaultTableModel(columns, 0);

        table = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        JButton editButton = new JButton("Edit Selected");

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(editButton);

        add(bottomPanel, BorderLayout.SOUTH);

        loadMovies();

        editButton.addActionListener(e -> handleEdit());
    }

    public void refreshTable() {
        loadMovies();
    }

    private void loadMovies() {

        List<Movie> movies = movieController.fetchAllMovies();

        tableModel.setRowCount(0);

        for (Movie movie : movies) {
            tableModel.addRow(new Object[]{
                    movie.getId(),
                    movie.getTitle(),
                    movie.getGenre(),
                    String.valueOf(movie.getYear()), // DB stores TEXT
                    movie.getRating()
            });
        }
    }

    private void handleEdit() {

        int selectedRow = table.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a movie first!");
            return;
        }

        int movieId = (int) tableModel.getValueAt(selectedRow, 0);
        String title = tableModel.getValueAt(selectedRow, 1).toString();
        String genre = tableModel.getValueAt(selectedRow, 2).toString();
        String year = tableModel.getValueAt(selectedRow, 3).toString();
        String rating = tableModel.getValueAt(selectedRow, 4).toString();

        JTextField titleField = new JTextField(title);
        JTextField genreField = new JTextField(genre);
        JTextField yearField = new JTextField(year);
        JTextField ratingField = new JTextField(rating);

        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.add(new JLabel("Title:"));
        panel.add(titleField);
        panel.add(new JLabel("Genre:"));
        panel.add(genreField);
        panel.add(new JLabel("Year:"));
        panel.add(yearField);
        panel.add(new JLabel("Rating:"));
        panel.add(ratingField);

        int result = JOptionPane.showConfirmDialog(
                this,
                panel,
                "Edit Movie",
                JOptionPane.OK_CANCEL_OPTION
        );

        if (result == JOptionPane.OK_OPTION) {

            String newTitle = titleField.getText().trim();
            String newGenre = genreField.getText().trim();
            String newYear = yearField.getText().trim(); // stays STRING
            String newRating = ratingField.getText().trim();

            // Validation
            if (newTitle.isEmpty() || newGenre.isEmpty() ||
                newYear.isEmpty() || newRating.isEmpty()) {

                JOptionPane.showMessageDialog(this, "All fields are required!");
                return;
            }

            double ratingValue;

            try {
                ratingValue = Double.parseDouble(newRating);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Rating must be a number!");
                return;
            }

            if (ratingValue < 0 || ratingValue > 5) {
                JOptionPane.showMessageDialog(this, "Rating must be between 0 and 5!");
                return;
            }

            // Update FULL movie 
            boolean updated = movieController.updateMovie(
                    movieId,
                    newTitle,
                    newGenre,
                    newYear,
                    ratingValue
            );

            if (updated) {
                JOptionPane.showMessageDialog(this, "Movie updated successfully!");
                refreshTable();
            } else {
                JOptionPane.showMessageDialog(this, "Update failed!");
            }
        }
    }
}