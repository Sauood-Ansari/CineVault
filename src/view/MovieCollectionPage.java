package view;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.List;

import controller.MovieController;
import model.Movie;

public class MovieCollectionPage extends JPanel {

    private JTable table;
    private DefaultTableModel tableModel;
    private MovieController movieController;

    public MovieCollectionPage() {
        setLayout(new BorderLayout(20, 20));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        movieController = new MovieController();

        String[] columns = {"ID", "Title", "Genre", "Year", "Rating"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };

        table = new JTable(tableModel);
        table.setRowHeight(35);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setSelectionBackground(new Color(52, 152, 219));
        table.setSelectionForeground(Color.WHITE);
        table.setShowVerticalLines(false);
        table.setGridColor(new Color(230, 230, 230));

        JTableHeader header = table.getTableHeader();
        header.setPreferredSize(new Dimension(0, 45));
        
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setBackground(new Color(41, 128, 185));
        headerRenderer.setForeground(Color.WHITE);
        headerRenderer.setFont(new Font("Segoe UI", Font.BOLD, 15));
        headerRenderer.setHorizontalAlignment(SwingConstants.LEFT);
        headerRenderer.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 1, new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }

        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
        }

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)));
        scrollPane.getViewport().setBackground(Color.WHITE);
        add(scrollPane, BorderLayout.CENTER);

        JButton editButton = new JButton("Edit Selected");
        editButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        editButton.setPreferredSize(new Dimension(150, 40));
        editButton.setBackground(new Color(41, 128, 185));
        editButton.setForeground(Color.WHITE);
        editButton.setFocusPainted(false);
        editButton.setBorderPainted(false);
        editButton.setOpaque(true);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.setBackground(new Color(245, 245, 245));
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
                    String.valueOf(movie.getYear()), 
                    movie.getRating()
            });
        }
    }

    private void handleEdit() {
        int selectedRow = table.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a movie first!", "Warning", JOptionPane.WARNING_MESSAGE);
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

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
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
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            String newTitle = titleField.getText().trim();
            String newGenre = genreField.getText().trim();
            String newYear = yearField.getText().trim(); 
            String newRating = ratingField.getText().trim();

            if (newTitle.isEmpty() || newGenre.isEmpty() ||
                newYear.isEmpty() || newRating.isEmpty()) {

                JOptionPane.showMessageDialog(this, "All fields are required!", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            double ratingValue;

            try {
                ratingValue = Double.parseDouble(newRating);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Rating must be a number!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (ratingValue < 0 || ratingValue > 5) {
                JOptionPane.showMessageDialog(this, "Rating must be between 0 and 5!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean updated = movieController.updateMovie(
                    movieId,
                    newTitle,
                    newGenre,
                    newYear,
                    ratingValue
            );

            if (updated) {
                JOptionPane.showMessageDialog(this, "Movie updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                refreshTable();
            } else {
                JOptionPane.showMessageDialog(this, "Update failed!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}