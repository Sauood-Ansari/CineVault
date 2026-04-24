package view;

import javax.swing.*;
import controller.*;
import java.awt.*;

public class Registration extends JPanel {

    private MainFrame frame;
    private AuthController ac = new AuthController();

    public Registration(MainFrame frame) {
        this.frame = frame;
        setLayout(new GridBagLayout());

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true),
            BorderFactory.createEmptyBorder(50, 60, 50, 60)
        ));
        formPanel.setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel("Create an Account");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 26));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel userLabel = new JLabel("New Username:");
        userLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JTextField username = new JTextField(15);
        username.setMaximumSize(new Dimension(300, 40));

        JLabel passLabel = new JLabel("New Password:");
        passLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JPasswordField password = new JPasswordField(15);
        password.setMaximumSize(new Dimension(300, 40));

        JButton registerBtn = createStyledButton("Register", new Color(41, 128, 185));
        JButton backBtn = createStyledButton("Back to Login", new Color(149, 165, 166));

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
        formPanel.add(registerBtn);
        formPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        formPanel.add(backBtn);

        add(formPanel);

        backBtn.addActionListener(e -> frame.showPage("login"));

        registerBtn.addActionListener(e -> {
            String uname = username.getText().trim();
            String pass = new String(password.getPassword()).trim();

            String message = ac.register(uname, pass);
            if (message.equals("SUCCESS")) {
                JOptionPane.showMessageDialog(this, "Registered successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                username.setText("");
                password.setText("");
                frame.showPage("login");
            } else if (message.equals("Username and password are required.")) {
                JOptionPane.showMessageDialog(this, message, "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, message, "Information", JOptionPane.INFORMATION_MESSAGE);
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