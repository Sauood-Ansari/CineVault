package view;

import java.awt.*;
import javax.swing.*;

public class Dashboard extends JPanel
{
	public JPanel mainPanel,topPanel,leftPanel,rightPanel,centrePanel;
	private JLabel topLabel,totalMovies,horroMovies,actionMovies,dramaMovies,sciFiMoveis;
	
	private MainFrame frame;
	
	public  Dashboard(MainFrame frame)
	{
		this.frame=frame;
		//Code for top panel to display 
			topPanel=new JPanel();
			topPanel.setLayout(new BoxLayout(topPanel,BoxLayout.Y_AXIS));
			
			topLabel=new JLabel("Dashboard");
			
			topPanel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
			
			topPanel.add(topLabel);		
		//Code for left panel for navigation
			leftPanel=new JPanel();
			leftPanel.setLayout(new FlowLayout(FlowLayout.CENTER,10,5));
			
			leftPanel.add(new JButton("Account"));
			leftPanel.add(new JButton("Settings"));
			leftPanel.add(new JButton("Dashboard"));
			
		//Code for center Panel to Display all the informations
			centrePanel=new JPanel(new GridLayout(3,2));
			
			
		//Code for main Panel which contains all these panel	
			mainPanel=new JPanel();
			mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		
	}
}
