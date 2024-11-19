package SCD.Dao;

import SCD.Connection.DBConnection;
import SCD.Model.SaleItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SaleItemsDAO {

    public void addSaleItem(SaleItem saleItem) throws SQLException {
        String sql = "INSERT INTO sale_items (sale_id, product_id, quantity, unit_price, total_price) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, saleItem.getSaleId());
            stmt.setInt(2, saleItem.getProductId());
            stmt.setInt(3, saleItem.getQuantity());
            stmt.setDouble(4, saleItem.getUnitPrice());
            stmt.setDouble(5, saleItem.getTotalPrice());
            stmt.executeUpdate();
        }
    }

    public List<SaleItem> getSaleItemsBySaleId(int saleId) throws SQLException {
        String sql = "SELECT * FROM sale_items WHERE sale_id = ?";
        List<SaleItem> saleItems = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, saleId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                saleItems.add(mapResultSetToSaleItem(rs));
            }
        }
        return saleItems;
    }

    private SaleItem mapResultSetToSaleItem(ResultSet rs) throws SQLException {
        return new SaleItem(
                rs.getInt("sale_item_id"),
                rs.getInt("sale_id"),
                rs.getInt("product_id"),
                rs.getInt("quantity"),
                rs.getDouble("unit_price"),
                rs.getDouble("total_price")
        );
    }
}

