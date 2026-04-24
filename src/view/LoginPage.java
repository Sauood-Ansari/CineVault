package view;

import javax.swing.*;
import java.awt.*;
import controller.*;

public class LoginPage extends JPanel {

    private MainFrame frame;

    public LoginPage(MainFrame frame) {
        this.frame = frame;
        setLayout(new GridBagLayout());

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true),
            BorderFactory.createEmptyBorder(50, 60, 50, 60)
        ));
        formPanel.setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel("Welcome to CineVault");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 26));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JTextField username = new JTextField(15);
        username.setMaximumSize(new Dimension(300, 40));

        JLabel passLabel = new JLabel("Password:");
        passLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JPasswordField password = new JPasswordField(15);
        password.setMaximumSize(new Dimension(300, 40));

        JButton loginButton = createStyledButton("Login", new Color(41, 128, 185));
        JButton registerButton = createStyledButton("Register", new Color(149, 165, 166));

        formPanel.add(titleLabel);
        formPanel.add(Box.createRigidArea(new Dimension(0, 40)));
        formPanel.add(userLabel);
        formPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        formPanel.add(username);
        formPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        formPanel.add(passLabel);
        formPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        formPanel.add(password);
        formPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        formPanel.add(loginButton);
        formPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        formPanel.add(registerButton);

        add(formPanel);

        registerButton.addActionListener(e -> frame.showPage("Registration"));

        loginButton.addActionListener(e -> {
            String user = username.getText();
            String pass = new String(password.getPassword());
            AuthController obj = new AuthController();

            String message = obj.login(user, pass);

            if (message.equals("SUCCESS")) {
                username.setText("");
                password.setText("");
                frame.showPage("Home Page");
            } else if (message.equals("Username and password are required.")) {
                JOptionPane.showMessageDialog(this, message, "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private JButton createStyledButton(String text, Color bgColor) {
        JButton btn = new JButton(text);
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setMaximumSize(new Dimension(300, 40));
        btn.setBackground(bgColor);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setOpaque(true);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }
}