package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.User;

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

    // Validates user login credentials and returns User if valid, null otherwise
    public User validateUser(String username, String password) throws SQLException {
        try (Connection conn = DBConnection.getConnection()) {

            // Fetch stored id and password for given username
            String query = "SELECT id, password FROM users WHERE username=?";
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            // Check if user exists and password matches
            if (rs.next()) {
                String storedPassword = rs.getString("password");
                if (storedPassword.equals(password)) {
                    return new User(rs.getInt("id"), username, null); // return user with id
                }
            }

            return null; // user not found or password mismatch
        }
    }
}