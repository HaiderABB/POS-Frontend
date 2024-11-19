package SCD.Dao;

import SCD.Connection.DBConnection;
import SCD.Model.Vendor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VendorsDAO {

    public void addVendor(Vendor vendor) throws SQLException {
        String sql = "INSERT INTO vendors (branch_id, name, contact_info, email, address) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, vendor.getBranchId());
            stmt.setString(2, vendor.getName());
            stmt.setString(3, vendor.getContactInfo());
            stmt.setString(4, vendor.getEmail());
            stmt.setString(5, vendor.getAddress());
            stmt.executeUpdate();
        }
    }


    public List<Vendor> getVendorsByBranch(int branchId) throws SQLException {
        String sql = "SELECT * FROM vendors WHERE branch_id = ?";
        List<Vendor> vendors = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, branchId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                vendors.add(mapResultSetToVendor(rs));
            }
        }
        return vendors;
    }

    private Vendor mapResultSetToVendor(ResultSet rs) throws SQLException {
        return new Vendor(
                rs.getInt("vendor_id"),
                rs.getInt("branch_id"),
                rs.getString("name"),
                rs.getString("contact_info"),
                rs.getString("email"),
                rs.getString("address")
        );
    }
}

