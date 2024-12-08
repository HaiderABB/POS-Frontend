package SCD.model.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class Product {

  @Id
  @Column(name = "product_code", nullable = false, unique = true)
  private String productCode;

  // Define the foreign key relationship with Vendor
  @ManyToOne
  @JoinColumn(name = "vendor_code", nullable = false, foreignKey = @ForeignKey(name = "FK_product_vendor"))
  private Vendor vendorCode;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "category")
  private String category;

  @Column(name = "original_price", nullable = false)
  private double originalPrice;

  @Column(name = "sale_price", nullable = false)
  private double salePrice;

  @Column(name = "price_by_unit", nullable = false)
  private double priceByUnit;

  @Column(name = "price_by_carton", nullable = false)
  private double priceByCarton;

  @Column(name = "stock_quantity", nullable = false)
  private int stockQuantity;

  @Column(name = "is_active", nullable = false)
  private boolean isActive = true;

  @Column(name = "created_at", nullable = false)
  private LocalDateTime createdAt;

  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  public Product() {
    this.createdAt = LocalDateTime.now();
    this.updatedAt = LocalDateTime.now();
  }

  public Product(String productCode, Vendor vendorCode, String name, String category,
      double originalPrice, double salePrice, double priceByUnit,
      double priceByCarton, int stockQuantity) {
    this.productCode = productCode;
    this.vendorCode = vendorCode;
    this.name = name;
    this.category = category;
    this.originalPrice = originalPrice;
    this.salePrice = salePrice;
    this.priceByUnit = priceByUnit;
    this.priceByCarton = priceByCarton;
    this.stockQuantity = stockQuantity;
    this.isActive = true;
    this.createdAt = LocalDateTime.now();
    this.updatedAt = LocalDateTime.now();
  }

  public Product(Vendor vendorCode, String name, String category,
      double originalPrice, double salePrice, double priceByUnit,
      double priceByCarton, int stockQuantity) {
    this.vendorCode = vendorCode;
    this.name = name;
    this.category = category;
    this.originalPrice = originalPrice;
    this.salePrice = salePrice;
    this.priceByUnit = priceByUnit;
    this.priceByCarton = priceByCarton;
    this.stockQuantity = stockQuantity;
    this.isActive = true;
    this.createdAt = LocalDateTime.now();
    this.updatedAt = LocalDateTime.now();
  }

  public String getProductCode() {
    return productCode;
  }

  public void setProductCode(String productCode) {
    this.productCode = productCode;
  }

  public Vendor getVendor() {
    return vendorCode;
  }

  public void setVendor(Vendor vendor) {
    this.vendorCode = vendor;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public double getOriginalPrice() {
    return originalPrice;
  }

  public void setOriginalPrice(double originalPrice) {
    this.originalPrice = originalPrice;
  }

  public double getSalePrice() {
    return salePrice;
  }

  public void setSalePrice(double salePrice) {
    this.salePrice = salePrice;
  }

  public double getPriceByUnit() {
    return priceByUnit;
  }

  public void setPriceByUnit(double priceByUnit) {
    this.priceByUnit = priceByUnit;
  }

  public double getPriceByCarton() {
    return priceByCarton;
  }

  public void setPriceByCarton(double priceByCarton) {
    this.priceByCarton = priceByCarton;
  }

  public int getStockQuantity() {
    return stockQuantity;
  }

  public void setStockQuantity(int stockQuantity) {
    this.stockQuantity = stockQuantity;
  }

  public boolean isActive() {
    return isActive;
  }

  public void setActive(boolean active) {
    isActive = active;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(LocalDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }

  @Override
  public String toString() {
    return "Product{" +
        "productCode='" + productCode + '\'' +
        ", vendorCode=" + vendorCode +
        ", name='" + name + '\'' +
        ", category='" + category + '\'' +
        ", originalPrice=" + originalPrice +
        ", salePrice=" + salePrice +
        ", priceByUnit=" + priceByUnit +
        ", priceByCarton=" + priceByCarton +
        ", stockQuantity=" + stockQuantity +
        ", isActive=" + isActive +
        ", createdAt=" + createdAt +
        ", updatedAt=" + updatedAt +
        '}';
  }
}
