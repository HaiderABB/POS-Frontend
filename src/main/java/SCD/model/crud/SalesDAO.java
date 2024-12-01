package SCD.model.crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import SCD.config.DBConnection;
import SCD.model.models.Sale;

public class SalesDAO {

    public void addSale(Sale sale) throws SQLException {
        String sql = "INSERT INTO sales (cashier_id, branch_id, total_amount, sale_date) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, sale.getCashierCode());
            stmt.setString(2, sale.getBranchCode());
            stmt.setDouble(3, sale.getTotalAmount());
            stmt.setDate(4, new java.sql.Date(sale.getSaleDate().getTime()));
            stmt.executeUpdate();

            // Retrieve the generated sale_id
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    sale.setSaleId(generatedKeys.getInt(1));
                }
            }
        }
    }

    public List<Sale> getSalesByBranch(int branchId) throws SQLException {
        String sql = "SELECT * FROM sales WHERE branch_id = ?";
        List<Sale> sales = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, branchId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                sales.add(mapResultSetToSale(rs));
            }
        }
        return sales;
    }

    private Sale mapResultSetToSale(ResultSet rs) throws SQLException {
        return new Sale(
                rs.getInt("sale_id"),
                rs.getString("cashier_code"),
                rs.getString("branch_code"),
                rs.getDouble("total_amount"),
                rs.getDate("sale_date"));
    }
}
