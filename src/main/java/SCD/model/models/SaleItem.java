package SCD.model.models;

import java.util.Objects;

public class SaleItem {
    private int sale_item_id;
    private int sale_id; // Foreign Key to sales
    private String product_code; // U // Foreign Key to products
    private int quantity; // U
    private double unitPrice;
    private double totalPrice;

    public SaleItem(int sale_item_id, int sale_id, String product_code, int quantity, double unitPrice,
            double totalPrice) {
        this.sale_item_id = sale_item_id;
        this.sale_id = sale_id;
        this.product_code = product_code;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
    }

    public int getSaleItemId() {
        return sale_item_id;
    }

    public void setSaleItemId(int sale_item_id) {
        this.sale_item_id = sale_item_id;
    }

    public int getSaleId() {
        return sale_id;
    }

    public void setSaleId(int sale_id) {
        this.sale_id = sale_id;
    }

    public String getProductCode() {
        return product_code;
    }

    public void setProductCode(String product_code) {
        this.product_code = product_code;
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
                "sale_item_id=" + sale_item_id +
                ", sale_id=" + sale_id +
                ", product_code=" + product_code +
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
        return sale_item_id == saleItem.sale_item_id &&
                sale_id == saleItem.sale_id &&
                (product_code == null ? saleItem.product_code == null : product_code.equals(saleItem.product_code)) &&
                quantity == saleItem.quantity &&
                Double.compare(saleItem.unitPrice, unitPrice) == 0 &&
                Double.compare(saleItem.totalPrice, totalPrice) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sale_item_id, sale_id, product_code, quantity, unitPrice, totalPrice);
    }
}
