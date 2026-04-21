package view;

import javax.swing.*;
public class MovieCollectionPage 
{
    private JPanel mainPanel;
    private JButton addMovieButton;
    private JButton viewCollectionButton;
    private JButton searchMovieButton;

    public MovieCollectionPage() {
        addMovieButton.addActionListener(e -> {
            
            JOptionPane.showMessageDialog(mainPanel, "Add Movie Page opened!");
        });

        viewCollectionButton.addActionListener(e -> {
            
            JOptionPane.showMessageDialog(mainPanel, "View Collection Page opened!");
        });

        searchMovieButton.addActionListener(e -> {
            
            JOptionPane.showMessageDialog(mainPanel, "Search Movie Page opened!");
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }


    static void main(String[] args) {
        
        
    }
}
