package view;


import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private CardLayout cardLayout;
    private JPanel mainPanel;

    public MainFrame() {
        setTitle("CineVault");
        //setExtendedState(JFrame.MAXIMIZED_BOTH);
        setSize(400,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // CardLayout setup
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Create pages
        LoginPage loginPage = new LoginPage(this);
        Registration registration = new Registration(this);
        HomePage homePage=new HomePage(this);

        // Add pages with names
        mainPanel.add(loginPage, "login");
        mainPanel.add(registration, "Registration");
        mainPanel.add(homePage, "Home Page");


        add(mainPanel);

        setVisible(true);
    }

    // Method to switch pages
    public void showPage(String pageName) {
        cardLayout.show(mainPanel, pageName);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame());
    }
}