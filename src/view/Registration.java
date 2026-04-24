package view;

import javax.swing.*;
import java.awt.*;

public class Registration extends JPanel {

    @SuppressWarnings("unused")
    private MainFrame frame;

    public Registration(MainFrame frame) {
        this.frame = frame;

        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));

        JTextField username = new JTextField(15);
        JPasswordField password = new JPasswordField(15);

        JButton registerBtn = new JButton("Register");
        JButton backBtn = new JButton("Back");

        add(new JLabel("New Username:"));
        add(username);

        add(new JLabel("New Password:"));
        add(password);

        add(registerBtn);
        add(backBtn);

        //redirect back to login page
        backBtn.addActionListener(e -> {
            frame.showPage("login");
        });

        // redirect back to login page after registering data 
        registerBtn.addActionListener(e -> {    
        	JOptionPane.showMessageDialog(this, "Registered!");
            frame.showPage("login");
        });
    }
}