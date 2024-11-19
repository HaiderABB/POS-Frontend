package SCD.Dao;

import SCD.Connection.DBConnection;
import SCD.Model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsersDAO {


    public void addUser(User user) throws SQLException {
        String sql = "INSERT INTO users (username, password, role, branch_id, emp_no, email, salary, is_first_login, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?, NOW(), NOW())";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getRole());
            stmt.setObject(4, user.getBranchId(), java.sql.Types.INTEGER); // Nullable for SuperAdmin
            stmt.setString(5, user.getEmpNo());
            stmt.setString(6, user.getEmail());
            stmt.setDouble(7, user.getSalary());
            stmt.setBoolean(8, user.isFirstLogin());
            stmt.executeUpdate();
        }
    }


    public User getUserByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM users WHERE username = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToUser(rs);
            }
        }
        return null;
    }

    
    public List<User> getUsersByRole(String role) throws SQLException {
        String sql = "SELECT * FROM users WHERE role = ?";
        List<User> users = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, role);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                users.add(mapResultSetToUser(rs));
            }
        }
        return users;
    }

    private User mapResultSetToUser(ResultSet rs) throws SQLException {
        return new User(
                rs.getInt("user_id"),
                rs.getString("username"),
                rs.getString("password"),
                rs.getString("role"),
                rs.getInt("branch_id"),
                rs.getString("emp_no"),
                rs.getString("email"),
                rs.getDouble("salary"),
                rs.getBoolean("is_first_login"),
                rs.getTimestamp("created_at"),
                rs.getTimestamp("updated_at")
        );
    }
}
