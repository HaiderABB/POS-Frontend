package SCD.Dao;

import SCD.Connection.DBConnection;
import SCD.Model.Branch;

import java.sql.*;


public class BranchesDAO {

    public void addBranch(Branch branch) throws SQLException {
        String sql = "INSERT INTO branches (branch_code, name, city, address, phone, no_of_employees, created_by, is_active) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, branch.getBranchCode());
            stmt.setString(2, branch.getName());
            stmt.setString(3, branch.getCity());
            stmt.setString(4, branch.getAddress());
            stmt.setString(5, branch.getPhone());
            stmt.setInt(6, branch.getNoOfEmployees());
            stmt.setInt(7, branch.getCreatedBy());
            stmt.setBoolean(8, branch.isActive());
            stmt.executeUpdate();
        }
    }

    public Branch getBranchByCode(String branchCode) throws SQLException {
        String sql = "SELECT * FROM branches WHERE branch_code = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, branchCode);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToBranch(rs);
            }
        }
        return null;
    }

    private Branch mapResultSetToBranch(ResultSet rs) throws SQLException {
        return new Branch(
                rs.getInt("branch_id"),
                rs.getString("branch_code"),
                rs.getString("name"),
                rs.getString("city"),
                rs.getString("address"),
                rs.getString("phone"),
                rs.getInt("no_of_employees"),
                rs.getInt("created_by"),
                rs.getBoolean("is_active"),
                rs.getTimestamp("created_at")
        );
    }

}

