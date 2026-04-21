package view;

import javax.swing.*;

public class Regidtration 
{
    private JFrame frame;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton registerButton;

    public Regidtration()
    {
        frame = new JFrame("Registration");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setSize(400, 400);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //frame.setLocationRelativeTo(null);

        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        registerButton = new JButton("Register");

        JPanel panel = new JPanel();
        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        panel.add(registerButton);

        frame.add(panel);
    }

    public void setVisible(boolean visible)
    {
        frame.setVisible(visible);
    }

    public static void main(String[] args)
    {
        Regidtration registration = new Regidtration();
        registration.setVisible(true);
    }
}
