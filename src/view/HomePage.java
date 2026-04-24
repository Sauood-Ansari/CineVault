package view;

import javax.swing.*;
import java.awt.*;

public class HomePage extends JPanel {

    private CardLayout cardLayout;
    private JPanel contentPanel;
    private MainFrame frame;

    // 🔥 Store references
    private Dashboard dashboardPanel;
    private AddMoviePage addMoviePage;
    private MovieCollectionPage movieListPage;

    public HomePage(MainFrame frame) {

        this.frame = frame;

        setLayout(new BorderLayout());

        // TOP BAR
        JPanel topBar = new JPanel();
        topBar.setPreferredSize(new Dimension(0, 50));
        topBar.add(new JLabel("CineVault-Home"));

        // SIDEBAR
        JPanel sideBar = new JPanel();
        sideBar.setLayout(new BoxLayout(sideBar, BoxLayout.Y_AXIS));
        sideBar.setPreferredSize(new Dimension(120, 0));

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

        // 🔥 Initialize once
        dashboardPanel = new Dashboard();
        addMoviePage = new AddMoviePage();
        movieListPage = new MovieCollectionPage();

        contentPanel.add(dashboardPanel, "dashboard");
        contentPanel.add(addMoviePage, "add");
        contentPanel.add(movieListPage, "list");

        // BUTTON ACTIONS

        dashboardBtn.addActionListener(e -> {
            dashboardPanel.refreshDashboard(); // 🔥 refresh data
            cardLayout.show(contentPanel, "dashboard");
        });

        addMovieBtn.addActionListener(e -> {
            cardLayout.show(contentPanel, "add");
        });

        movieListBtn.addActionListener(e -> {
            movieListPage.refreshTable(); // 🔥 refresh table
            cardLayout.show(contentPanel, "list");
        });

        logOutBtn.addActionListener(e -> {
            frame.showPage("login"); // 🔥 proper logout
        });

        add(topBar, BorderLayout.NORTH);
        add(sideBar, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);
    }
}