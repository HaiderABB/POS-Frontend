package SCD.model.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "sale_items")
public class SaleItem {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "sale_item_id")
  private int saleItemId;

  @ManyToOne
  @JoinColumn(name = "product_code", referencedColumnName = "product_code", nullable = false)
  private Product product;

  @ManyToOne
  @JoinColumn(name = "sale_id", nullable = false)
  private Sale sale;

  @Column(name = "quantity", nullable = false)
  private int quantity;

  @Column(name = "sale_price", nullable = false)
  private double salePrice;

  @Column(name = "total_price", nullable = false)
  private double totalPrice;

  @Column(name = "actual_price", nullable = false)
  private double actualPrice; // New field for the actual price

  public SaleItem() {
  }

  public SaleItem(Product product, int quantity, double salePrice, double actualPrice) {
    this.product = product;
    this.quantity = quantity;
    this.salePrice = salePrice;
    this.actualPrice = actualPrice;
    this.totalPrice = quantity * salePrice; // Automatically calculate total price
  }

  // Getters and Setters

  public int getSaleItemId() {
    return saleItemId;
  }

  public void setSaleItemId(int saleItemId) {
    this.saleItemId = saleItemId;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public Sale getSale() {
    return sale;
  }

  public void setSale(Sale sale) {
    this.sale = sale;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public void increment(int quantity) {
    this.quantity += quantity;
    calculateTotalPrice(); // Update total price when quantity changes
  }

  public void decrement(int quantity) {
    if (this.quantity >= quantity) {
      this.quantity -= quantity;
      calculateTotalPrice(); // Update total price when quantity changes
    }
  }

  public double getSalePrice() {
    return salePrice;
  }

  public void setSalePrice(double salePrice) {
    this.salePrice = salePrice;
    calculateTotalPrice(); // Update total price if sale price changes
  }

  public double getTotalPrice() {
    return totalPrice;
  }

  public void setTotalPrice(double totalPrice) {
    this.totalPrice = totalPrice;
  }

  public double getActualPrice() {
    return actualPrice;
  }

  public void setActualPrice(double actualPrice) {
    this.actualPrice = actualPrice;
  }

  private void calculateTotalPrice() {
    this.totalPrice = this.quantity * this.salePrice; // Calculate total price based on sale price
  }

  @Override
  public String toString() {
    return "SaleItem{" +
        "saleItemId=" + saleItemId +
        ", product=" + (product != null ? product.getProductCode() : "Unknown") +
        ", sale=" + (sale != null ? sale.getSaleId() : "Unknown") +
        ", quantity=" + quantity +
        ", salePrice=" + salePrice +
        ", actualPrice=" + actualPrice + // Include actual price in toString output
        ", totalPrice=" + totalPrice +
        '}';
  }
}
