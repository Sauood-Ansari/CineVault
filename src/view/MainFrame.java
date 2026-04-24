package view;

import javax.swing.*;
import java.awt.*;
import java.util.Enumeration;
import javax.swing.plaf.FontUIResource;

public class MainFrame extends JFrame {

    private CardLayout cardLayout;
    private JPanel mainPanel;

    public MainFrame() {
        applyGlobalStyles();

        setTitle("CineVault");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        LoginPage loginPage = new LoginPage(this);
        Registration registration = new Registration(this);
        HomePage homePage = new HomePage(this);

        mainPanel.add(loginPage, "login");
        mainPanel.add(registration, "Registration");
        mainPanel.add(homePage, "Home Page");

        add(mainPanel);
        setVisible(true);
    }

    public void showPage(String pageName) {
        cardLayout.show(mainPanel, pageName);
    }

    private void applyGlobalStyles() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        FontUIResource globalFont = new FontUIResource(new Font("Segoe UI", Font.PLAIN, 15));
        Enumeration<Object> keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof FontUIResource) {
                UIManager.put(key, globalFont);
            }
        }

        UIManager.put("OptionPane.messageFont", new Font("Segoe UI", Font.PLAIN, 15));
        UIManager.put("OptionPane.buttonFont", new Font("Segoe UI", Font.BOLD, 14));
        UIManager.put("Panel.background", new Color(245, 245, 245));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame());
    }
}