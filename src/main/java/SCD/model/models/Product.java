package SCD.model.models;

import java.util.Date;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "products")
public class Product {

  @Id
  @Column(name = "product_code", nullable = false, unique = true) // Primary key
  private String productCode;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "vendor_code", referencedColumnName = "vendor_code", nullable = false, foreignKey = @ForeignKey(name = "fk_vendor_code"))
  private Vendor vendor;

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

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "created_at", nullable = false, updatable = false)
  private Date createdAt;

  @Column(name = "is_active", nullable = false)
  private boolean isActive = true;

  public Product() {
  }

  public Product(String productCode, Vendor vendor, String name, String category,
      double originalPrice, double salePrice, double priceByUnit,
      double priceByCarton, int stockQuantity, Date createdAt) {
    this.productCode = productCode;
    this.vendor = vendor;
    this.name = name;
    this.category = category;
    this.originalPrice = originalPrice;
    this.salePrice = salePrice;
    this.priceByUnit = priceByUnit;
    this.priceByCarton = priceByCarton;
    this.stockQuantity = stockQuantity;
    this.createdAt = createdAt;
    this.isActive = true;
  }

  public String getProductCode() {
    return productCode;
  }

  public void setProductCode(String productCode) {
    this.productCode = productCode;
  }

  public Vendor getVendor() {
    return vendor;
  }

  public void setVendor(Vendor vendor) {
    this.vendor = vendor;
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

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public boolean isActive() {
    return isActive;
  }

  public void setActive(boolean active) {
    isActive = active;
  }

  @Override
  public String toString() {
    return "Product{" +
        "productCode='" + productCode + '\'' +
        ", vendor=" + vendor +
        ", name='" + name + '\'' +
        ", category='" + category + '\'' +
        ", originalPrice=" + originalPrice +
        ", salePrice=" + salePrice +
        ", priceByUnit=" + priceByUnit +
        ", priceByCarton=" + priceByCarton +
        ", stockQuantity=" + stockQuantity +
        ", createdAt=" + createdAt +
        ", isActive=" + isActive +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Product product = (Product) o;
    return Double.compare(product.originalPrice, originalPrice) == 0 &&
        Double.compare(product.salePrice, salePrice) == 0 &&
        Double.compare(product.priceByUnit, priceByUnit) == 0 &&
        Double.compare(product.priceByCarton, priceByCarton) == 0 &&
        stockQuantity == product.stockQuantity &&
        isActive == product.isActive &&
        Objects.equals(productCode, product.productCode) &&
        Objects.equals(vendor, product.vendor) &&
        Objects.equals(name, product.name) &&
        Objects.equals(category, product.category) &&
        Objects.equals(createdAt, product.createdAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(productCode, vendor, name, category, originalPrice,
        salePrice, priceByUnit, priceByCarton, stockQuantity, createdAt, isActive);
  }
}
