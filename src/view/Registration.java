package view;

import javax.swing.*;
import controller.*;
import java.awt.*;

public class Registration extends JPanel {

	private MainFrame frame;

	AuthController ac = new AuthController();

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

		// redirect back to login page
		backBtn.addActionListener(e -> {
			frame.showPage("login");
		});

		// redirect back to login page after registering data
		registerBtn.addActionListener(e -> {
			String uname = username.getText().trim();
			String pass = new String(password.getPassword()).trim();

			String message = ac.register(uname, pass);
			if (message.equals("SUCCESS")) {
				JOptionPane.showMessageDialog(this, "Registered!");
				username.setText("");
				password.setText("");
				frame.showPage("login");
			} else if (message.equals("Username and password are required.")) {
				JOptionPane.showMessageDialog(this, message);
			} else {
				JOptionPane.showMessageDialog(this, message);
			}

		});
	}
}