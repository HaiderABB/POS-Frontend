package SCD.Model;

import java.util.Objects;

public class SaleItem {
    private int saleItemId;
    private int saleId; // Foreign Key to sales
    private int productId; // Foreign Key to products
    private int quantity;
    private double unitPrice;
    private double totalPrice;

    public SaleItem(int saleItemId, int saleId, int productId, int quantity, double unitPrice, double totalPrice) {
        this.saleItemId = saleItemId;
        this.saleId = saleId;
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
    }

    public int getSaleItemId() {
        return saleItemId;
    }

    public void setSaleItemId(int saleItemId) {
        this.saleItemId = saleItemId;
    }

    public int getSaleId() {
        return saleId;
    }

    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
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

    public String toString() {
        return "SaleItem{" +
                "saleItemId=" + saleItemId +
                ", saleId=" + saleId +
                ", productId=" + productId +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                ", totalPrice=" + totalPrice +
                '}';
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SaleItem saleItem = (SaleItem) o;
        return saleItemId == saleItem.saleItemId &&
                saleId == saleItem.saleId &&
                productId == saleItem.productId &&
                quantity == saleItem.quantity &&
                Double.compare(saleItem.unitPrice, unitPrice) == 0 &&
                Double.compare(saleItem.totalPrice, totalPrice) == 0;
    }

    public int hashCode() {
        return Objects.hash(saleItemId, saleId, productId, quantity, unitPrice, totalPrice);
    }
}

