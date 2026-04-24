package view;

import javax.swing.*;
import java.awt.*;
import controller.MovieController;

public class Dashboard extends JPanel {

    private MovieController mc = new MovieController();

    public Dashboard() {
        setLayout(new GridLayout(2, 2, 25, 25));
        setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        setBackground(new Color(245, 245, 245));
        refreshDashboard(); 
    }

    public void refreshDashboard() {
        removeAll();
        
        add(createCard("Total Movies", mc.getTotalMovies(), new Color(52, 152, 219)));
        add(createCard("Action Movies", mc.getTotalMoviesByGenre("Action"), new Color(231, 76, 60)));
        add(createCard("Sci-Fi Movies", mc.getTotalMoviesByGenre("Sci-Fi"), new Color(155, 89, 182)));
        add(createCard("Drama Movies", mc.getTotalMoviesByGenre("Drama"), new Color(46, 204, 113)));
        
        revalidate();
        repaint();
    }

    private JPanel createCard(String title, int value, Color color) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(color);
        card.setBorder(BorderFactory.createEmptyBorder(30, 20, 30, 20));

        JLabel titleLabel = new JLabel(title, JLabel.CENTER);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));

        JLabel valueLabel = new JLabel(String.valueOf(value), JLabel.CENTER);
        valueLabel.setForeground(Color.WHITE);
        valueLabel.setFont(new Font("Segoe UI", Font.BOLD, 48));

        card.add(titleLabel, BorderLayout.NORTH);
        card.add(valueLabel, BorderLayout.CENTER);

        return card;
    }
}