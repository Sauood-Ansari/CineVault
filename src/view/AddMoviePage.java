package view;

import java.awt.Dimension;
import javax.swing.*;
import controller.MovieController;

public class AddMoviePage{

	private JPanel mainPanel;
	private JTextField title, genre, year, rating;
	private JButton addMovieButton;
	
	String movieTitle,movieGenre,movieYear,movieRating;

	MovieController movieController = new MovieController();

	public void addMovie() {

		
		title=new JTextField();
		genre=new JTextField();		
		year=new JTextField();		
		rating=new JTextField();
		
		mainPanel=new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		
		mainPanel.add(new JLabel("Movie Name:"));
		mainPanel.add(title);
		
		mainPanel.add(new JLabel("Movie Genre:"));
		mainPanel.add(genre);
		
		mainPanel.add(new JLabel("Release Year:"));
		mainPanel.add(year);
		
		mainPanel.add(new JLabel("Rating Out Of Five"));
		mainPanel.add(rating);
		
		addMovieButton=new JButton("Add Movie");
		addMovieButton.setBounds(50, 200, 200, 30);	
		
		mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		mainPanel.add(addMovieButton);		

		

		addMovieButton.addActionListener(e -> {
			movieTitle = title.getText();
			movieGenre = genre.getText();
			movieYear = year.getText();
			movieRating = rating.getText();

			movieController.addMovie(movieTitle, movieGenre, movieYear, movieRating);
			
		});
	}
	
	public void setVisible(boolean visible)
    {
        mainPanel.setVisible(visible);
    }
	
}
