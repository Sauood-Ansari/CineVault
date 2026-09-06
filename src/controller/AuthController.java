package controller;

import dao.UserDAO;
import model.User;
import utils.SessionManager;

import java.sql.SQLException;

// Handles authentication logic (login, register, logout)
public class AuthController {

    private final UserDAO userDAO = new UserDAO(); // DAO for DB operations

    // Handles user login
    public String login(String username, String password)
    {
        // Basic input validation
        if (username == null || username.trim().isEmpty() ||
            password == null || password.trim().isEmpty()) {
            return "Username and password are required.";
        }

        try {
            // Check credentials from database
            User user = userDAO.validateUser(username.trim(), password.trim());

            if (user != null) {
                // Start session if login successful
                SessionManager.startSession(user);
                return "SUCCESS";
            } else {
                return "Invalid username or password.";
            }

        } catch (SQLException e) {
            // Handle DB errors
            return "Login error: " + e.getMessage();
        }
    }

    // Handles new user registration
    public String register(String username, String password)
    {
        // Basic input validation
        if (username == null || username.trim().isEmpty() ||
            password == null || password.trim().isEmpty()) {
            return "Username and password are required.";
        }

        try {
            // Save user in database
            boolean success = userDAO.registerUser(username.trim(), password.trim());

            return success ? "SUCCESS" : "Registration failed.";

        } catch (SQLException e) {
            return "Registration error: " + e.getMessage();
        }
    }

    // Ends current user session
    public void logout() {
        SessionManager.clearSession();
    }
}