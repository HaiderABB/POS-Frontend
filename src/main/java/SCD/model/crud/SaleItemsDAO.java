package SCD.model.crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import SCD.config.DBConnection;
import SCD.model.models.SaleItem;

public class SaleItemsDAO {

    public void addSaleItem(SaleItem saleItem) throws SQLException {
        String sql = "INSERT INTO sale_items (sale_id, product_id, quantity, unit_price, total_price) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, saleItem.getSaleId());
            stmt.setString(2, saleItem.getProductCode());
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
                rs.getString("product_code"),
                rs.getInt("quantity"),
                rs.getDouble("unit_price"),
                rs.getDouble("total_price"));
    }
}
