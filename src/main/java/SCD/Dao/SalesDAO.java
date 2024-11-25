package SCD.Dao;

import SCD.Connection.DBConnection;
import SCD.Model.Sale;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SalesDAO {

    public void addSale(Sale sale) throws SQLException {
        String sql = "INSERT INTO sales (cashier_id, branch_id, total_amount, sale_date) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, sale.getCashierId());
            stmt.setInt(2, sale.getBranchId());
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
                rs.getInt("cashier_id"),
                rs.getInt("branch_id"),
                rs.getDouble("total_amount"),
                rs.getDate("sale_date")
        );
    }
}

