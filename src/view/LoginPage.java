package view;

import javax.swing.*;
import java.awt.*;
import controller.*;

public class LoginPage extends JPanel {

	private MainFrame frame;

	public LoginPage(MainFrame frame) {

		this.frame = frame;

		setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));

		// Username row
		JPanel userPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		userPanel.add(new JLabel("Username:"));
		JTextField username = new JTextField(15);
		userPanel.add(username);

		// Password row
		JPanel passPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		passPanel.add(new JLabel("Password:"));
		JPasswordField password = new JPasswordField(15);
		passPanel.add(password);

		// Button row
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JButton loginButton = new JButton("Login");
		buttonPanel.add(loginButton);
		JButton registerButton = new JButton("Register");
		buttonPanel.add(registerButton);

		// Add all rows
		add(userPanel);
		add(passPanel);
		add(buttonPanel);

		// action listener to redirect to registration
		registerButton.addActionListener(e -> {
			frame.showPage("Registration");
		});

		// action listener to redirect to dashboard
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
				JOptionPane.showMessageDialog(this, message);
			} else {
				JOptionPane.showMessageDialog(this, "Invalid username or password.");
			}
		});
	}

}