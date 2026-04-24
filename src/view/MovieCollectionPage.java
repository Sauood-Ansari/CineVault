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
    MovieController movieController=new MovieController();

    public MovieCollectionPage() {

        setLayout(new BorderLayout());

        movieController = new MovieController();

        // Column names
        String[] columns = {"Title", "Genre", "Year", "Rating"};

        // Table model
        tableModel = new DefaultTableModel(columns, 0);

        table = new JTable(tableModel);

        // Load data
        loadMovies();

        // Scroll pane (important for table)
        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane, BorderLayout.CENTER);
    }

    private void loadMovies() {

        List<Movie> movies = movieController.fetchAllMovies();

        // Clear existing rows
        tableModel.setRowCount(0);

        // Add rows
        for (Movie movie : movies) {
        	 tableModel.addRow(new Object[]{
        	            movie.getTitle(),
        	            movie.getGenre(),
        	            movie.getYear(),
        	            movie.getRating()
        	        });
        }
    }
}