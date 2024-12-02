package SCD.model.crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import SCD.config.DBConnection;
import SCD.model.models.Product;

public class ProductsDAO {

    public void addProduct(Product product) throws SQLException {
        String sql = "INSERT INTO products (vendor_id, branch_id, name, category, original_price, sale_price, price_by_unit, price_by_carton, stock_quantity, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), NOW())";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, product.getVendorId());
            stmt.setInt(2, product.getBranchId());
            stmt.setString(3, product.getName());
            stmt.setString(4, product.getCategory());
            stmt.setDouble(5, product.getOriginalPrice());
            stmt.setDouble(6, product.getSalePrice());
            stmt.setDouble(7, product.getPriceByUnit());
            stmt.setDouble(8, product.getPriceByCarton());
            stmt.setInt(9, product.getStockQuantity());
            stmt.executeUpdate();
        }
    }

    // Get products by branch
    public List<Product> getProductsByBranch(int branchId) throws SQLException {
        String sql = "SELECT * FROM products WHERE branch_id = ?";
        List<Product> products = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, branchId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                products.add(mapResultSetToProduct(rs));
            }
        }
        return products;
    }

    private Product mapResultSetToProduct(ResultSet rs) throws SQLException {
        return new Product(
                rs.getInt("product_id"),
                rs.getInt("vendor_id"),
                rs.getInt("branch_id"),
                rs.getString("name"),
                rs.getString("category"),
                rs.getDouble("original_price"),
                rs.getDouble("sale_price"),
                rs.getDouble("price_by_unit"),
                rs.getDouble("price_by_carton"),
                rs.getInt("stock_quantity"),
                rs.getTimestamp("created_at"),
                rs.getTimestamp("updated_at"));
    }
}
