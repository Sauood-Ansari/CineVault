package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// DAO for user authentication and registration
public class UserDAO {

    // Registers a new user in the database
    public boolean registerUser(String username, String password) throws SQLException {
        try (Connection conn = DBConnection.getConnection()) {

            // Insert user credentials
            String query = "INSERT INTO users(username, password) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(1, username);
            stmt.setString(2, password); // should be hashed in real apps

            return stmt.executeUpdate() > 0; // true if insert successful
        }
    }

    // Validates user login credentials
    public boolean validateUser(String username, String password) throws SQLException {
        try (Connection conn = DBConnection.getConnection()) {

            // Fetch stored password for given username
            String query = "SELECT password FROM users WHERE username=?";
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            // Check if user exists and password matches
            if (rs.next()) {
                String storedPassword = rs.getString("password");
                return storedPassword.equals(password); // plain-text comparison
            }

            return false; // user not found
        }
    }
}