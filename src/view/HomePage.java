package view;

import javax.swing.*;
import java.awt.*;

public class HomePage extends JPanel {

    private CardLayout cardLayout;
    private JPanel contentPanel;
    MainFrame frame;

    public HomePage(MainFrame frame) 
    {
    	this.frame=frame;
    	
        setLayout(new BorderLayout());

        // TOP BAR
        JPanel topBar = new JPanel();
        topBar.setPreferredSize(new Dimension(0, 50));
        topBar.add(new JLabel("CineVault-Home"));

        // SIDEBAR
        JPanel sideBar = new JPanel();
        sideBar.setLayout(new BoxLayout(sideBar, BoxLayout.Y_AXIS));
        sideBar.setPreferredSize(new Dimension(100, 0));

        JButton dashboardBtn = new JButton("Dashboard");
        JButton addMovieBtn = new JButton("Add Movie");
        JButton movieListBtn = new JButton("Movie List");
        JButton logOutBtn = new JButton("Log Out");

        dashboardBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        addMovieBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        movieListBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        logOutBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));

        sideBar.add(Box.createVerticalStrut(20));
        sideBar.add(dashboardBtn);
        sideBar.add(Box.createVerticalStrut(10));
        sideBar.add(addMovieBtn);
        sideBar.add(Box.createVerticalStrut(10));
        sideBar.add(movieListBtn);
        sideBar.add(Box.createVerticalStrut(10));
        sideBar.add(logOutBtn);

        // CONTENT AREA
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);

        contentPanel.add(new Dashboard(), "dashboard");
        contentPanel.add(new AddMoviePage(), "add");
        contentPanel.add(new MovieCollectionPage(), "list");
        contentPanel.add(new LoginPage(frame),"login");

        //BUTTON ACTIONS
        dashboardBtn.addActionListener(e -> cardLayout.show(contentPanel, "dashboard"));
        addMovieBtn.addActionListener(e -> cardLayout.show(contentPanel, "add"));
        movieListBtn.addActionListener(e -> cardLayout.show(contentPanel, "list"));
        logOutBtn.addActionListener(e -> System.exit(ABORT));

        // ADD TO MAIN PANEL
        add(topBar, BorderLayout.NORTH);
        add(sideBar, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);
    }
}