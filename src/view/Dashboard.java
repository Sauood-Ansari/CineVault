package view;

import javax.swing.*;
import java.awt.*;
import controller.MovieController;

public class Dashboard extends JPanel {

    private MovieController mc = new MovieController();

    public Dashboard() {
        setLayout(new GridLayout(2, 3, 10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        refreshDashboard(); // initial load
    }

    // 🔥 Refresh method
    public void refreshDashboard() {

        removeAll(); // clear old cards

        add(createCard("Total Movies", mc.getTotalMovies()));
        add(createCard("Action Movies", mc.getTotalMoviesByGenre("Action")));
        add(createCard("Sci-Fi Movies", mc.getTotalMoviesByGenre("Sci-Fi")));
        add(createCard("Drama Movies", mc.getTotalMoviesByGenre("Drama")));

        revalidate();
        repaint();
    }

    private JPanel createCard(String title, int value) {

        JPanel card = new JPanel(new BorderLayout());
        card.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        JLabel titleLabel = new JLabel(title, JLabel.CENTER);
        JLabel valueLabel = new JLabel(String.valueOf(value), JLabel.CENTER);

        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        valueLabel.setFont(new Font("Arial", Font.BOLD, 24));

        card.add(titleLabel, BorderLayout.NORTH);
        card.add(valueLabel, BorderLayout.CENTER);

        return card;
    }
}