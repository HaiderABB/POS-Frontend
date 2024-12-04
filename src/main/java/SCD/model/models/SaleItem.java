package SCD.model.models;

import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PreRemove;
import jakarta.persistence.Table;

@Entity
@Table(name = "sale_items")
public class SaleItem {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) // Automatically assigned by the database
  @Column(name = "sale_item_id")
  private int saleItemId;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "sale_id", referencedColumnName = "sale_id", foreignKey = @ForeignKey(name = "FK_SALE_ID"))
  private Sale sale; // Foreign Key to Sales

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "product_code", referencedColumnName = "product_code", foreignKey = @ForeignKey(name = "FK_PRODUCT_CODE"))
  private Product product; // Foreign Key to Products

  @Column(name = "quantity", nullable = false)
  private int quantity;

  @Column(name = "unitPrice", nullable = false)
  private double unitPrice;

  @Column(name = "totalPrice", nullable = false)
  private double totalPrice;

  public SaleItem() {
  }

  public SaleItem(Sale sale, Product product, int quantity, double unitPrice) {
    this.sale = sale;
    this.product = product;
    this.quantity = quantity;
    this.unitPrice = unitPrice;
    updateTotalPrice();
  }

  public int getSaleItemId() {
    return saleItemId;
  }

  public void setSaleItemId(int saleItemId) {
    this.saleItemId = saleItemId;
  }

  public Sale getSale() {
    return sale;
  }

  public void setSale(Sale sale) {
    this.sale = sale;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
    updateTotalPrice();
  }

  public double getUnitPrice() {
    return unitPrice;
  }

  public void setUnitPrice(double unitPrice) {
    this.unitPrice = unitPrice;
    updateTotalPrice();
  }

  public double getTotalPrice() {
    return totalPrice;
  }

  private void updateTotalPrice() {
    this.totalPrice = this.unitPrice * this.quantity;
  }

  @Override
  public String toString() {
    return "SaleItem{" +
        "saleItemId=" + saleItemId +
        ", sale=" + sale +
        ", product=" + product +
        ", quantity=" + quantity +
        ", unitPrice=" + unitPrice +
        ", totalPrice=" + totalPrice +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    SaleItem saleItem = (SaleItem) o;
    return saleItemId == saleItem.saleItemId &&
        quantity == saleItem.quantity &&
        Double.compare(saleItem.unitPrice, unitPrice) == 0 &&
        Double.compare(saleItem.totalPrice, totalPrice) == 0 &&
        sale.equals(saleItem.sale) &&
        product.equals(saleItem.product);
  }

  @Override
  public int hashCode() {
    return Objects.hash(saleItemId, sale, product, quantity, unitPrice, totalPrice);
  }

  /**
   * Handles updating product code to default when the associated product is
   * deleted.
   */
  @PreRemove
  public void handleProductDeletion() {
    if (this.product != null) {
      this.product.setProductCode("PO-0011"); // Default product code when product is deleted
    }
  }
}
