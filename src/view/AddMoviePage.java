package view;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import javax.swing.*;
import controller.MovieController;

public class AddMoviePage{
	private JFrame movieCollectionFrame;
	private JPanel mainPanel;
	private JTextField title, genre, year, rating;
	private JButton addMovieButton;
	
	String movieTitle,movieGenre;
	int movieYear;
	double movieRating;

	MovieController movieController = new MovieController();

	public void addMovie() {
		movieCollectionFrame =new JFrame("Movies Collection");
		movieCollectionFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		movieCollectionFrame.setSize(400, 400);
		movieCollectionFrame.setLayout(new GridBagLayout());
		//movieCollectionFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
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

		
		movieCollectionFrame.add(mainPanel);

		addMovieButton.addActionListener(e -> {
			movieTitle = title.getText();
			movieGenre = genre.getText();
			movieYear = Integer.parseInt(year.getText());
			movieRating = Double.parseDouble(rating.getText());

			// movieController.addMovie(movieTitle, movieGenre, movieYear, movieRating);
			
		});
		movieCollectionFrame.setLayout(new GridBagLayout());
		movieCollectionFrame.setVisible(true);

	}
	
}
