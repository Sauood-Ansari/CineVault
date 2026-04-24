package view;

import javax.swing.*;
import java.awt.*;

public class HomePage extends JPanel {

    private CardLayout cardLayout;
    private JPanel contentPanel;
    private MainFrame frame;

    private Dashboard dashboardPanel;
    private AddMoviePage addMoviePage;
    private MovieCollectionPage movieListPage;

    public HomePage(MainFrame frame) {
        this.frame = frame;
        setLayout(new BorderLayout());

        JPanel topBar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topBar.setPreferredSize(new Dimension(0, 70));
        topBar.setBackground(new Color(41, 128, 185));
        topBar.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        
        JLabel appTitle = new JLabel("CineVault");
        appTitle.setFont(new Font("Segoe UI", Font.BOLD, 28));
        appTitle.setForeground(Color.WHITE);
        topBar.add(appTitle);

        JPanel sideBar = new JPanel();
        sideBar.setLayout(new BoxLayout(sideBar, BoxLayout.Y_AXIS));
        sideBar.setPreferredSize(new Dimension(220, 0));
        sideBar.setBackground(new Color(44, 62, 80));
        sideBar.setBorder(BorderFactory.createEmptyBorder(30, 10, 30, 10));

        JButton dashboardBtn = createNavButton("Dashboard");
        JButton addMovieBtn = createNavButton("Add Movie");
        JButton movieListBtn = createNavButton("Movie List");
        JButton logOutBtn = createNavButton("Log Out");
        logOutBtn.setBackground(new Color(192, 57, 43));

        sideBar.add(dashboardBtn);
        sideBar.add(Box.createVerticalStrut(15));
        sideBar.add(addMovieBtn);
        sideBar.add(Box.createVerticalStrut(15));
        sideBar.add(movieListBtn);
        sideBar.add(Box.createVerticalGlue());
        sideBar.add(logOutBtn);

        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        contentPanel.setBackground(new Color(245, 245, 245));

        dashboardPanel = new Dashboard();
        addMoviePage = new AddMoviePage();
        movieListPage = new MovieCollectionPage();

        contentPanel.add(dashboardPanel, "dashboard");
        contentPanel.add(addMoviePage, "add");
        contentPanel.add(movieListPage, "list");

        dashboardBtn.addActionListener(e -> {
            dashboardPanel.refreshDashboard(); 
            cardLayout.show(contentPanel, "dashboard");
        });

        addMovieBtn.addActionListener(e -> cardLayout.show(contentPanel, "add"));

        movieListBtn.addActionListener(e -> {
            movieListPage.refreshTable();
            cardLayout.show(contentPanel, "list");
        });

        logOutBtn.addActionListener(e -> frame.showPage("login"));

        add(topBar, BorderLayout.NORTH);
        add(sideBar, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);
    }

    private JButton createNavButton(String text) {
        JButton btn = new JButton(text);
        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        btn.setBackground(new Color(52, 73, 94));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setOpaque(true);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        return btn;
    }
}