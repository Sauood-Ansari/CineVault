package view;

import javax.swing.*;
import java.awt.*;
import controller.MovieController;

public class Dashboard extends JPanel 
{
	MovieController mc=new MovieController();

    public Dashboard() 
    {
        setLayout(new GridLayout(2, 3, 10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        int totalMovieCount=mc.getTotalMovies();
        int movieCount;
        add(createCard("Total Movies", totalMovieCount));
        movieCount=mc.getTotalMoviesByGenre("Action");
        add(createCard("Action Movies", movieCount));
        movieCount=mc.getTotalMoviesByGenre("Sci-Fi");
        add(createCard("Sci-Fi Movies", movieCount));
        movieCount=mc.getTotalMoviesByGenre("Drama");
        add(createCard("Drama Movies", movieCount));
    }

    private JPanel createCard(String title, int totalMovieCount) {

        JPanel card = new JPanel(new BorderLayout());
        card.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        JLabel titleLabel = new JLabel(title, JLabel.CENTER);
        JLabel valueLabel = new JLabel();

        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        valueLabel.setFont(new Font("Arial", Font.BOLD, 24));

        card.add(titleLabel, BorderLayout.NORTH);
        card.add(valueLabel, BorderLayout.CENTER);

        return card;
    }
}