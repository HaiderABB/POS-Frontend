package SCD.Model;

import java.util.Date;
import java.util.Objects;

public class Product {
    private int productId;
    private int vendorId;
    private int branchId;
    private String name;
    private String category;
    private double originalPrice;
    private double salePrice;
    private double priceByUnit;
    private double priceByCarton;
    private int stockQuantity;
    private Date createdAt;
    private Date updatedAt;


    public Product(int productId, int vendorId, int branchId, String name, String category,
                   double originalPrice, double salePrice, double priceByUnit,
                   double priceByCarton, int stockQuantity, Date createdAt, Date updatedAt) {
        this.productId = productId;
        this.vendorId = vendorId;
        this.branchId = branchId;
        this.name = name;
        this.category = category;
        this.originalPrice = originalPrice;
        this.salePrice = salePrice;
        this.priceByUnit = priceByUnit;
        this.priceByCarton = priceByCarton;
        this.stockQuantity = stockQuantity;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }


    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getVendorId() {
        return vendorId;
    }

    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
    }

    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
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

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }


    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", vendorId=" + vendorId +
                ", branchId=" + branchId +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", originalPrice=" + originalPrice +
                ", salePrice=" + salePrice +
                ", priceByUnit=" + priceByUnit +
                ", priceByCarton=" + priceByCarton +
                ", stockQuantity=" + stockQuantity +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return productId == product.productId &&
                vendorId == product.vendorId &&
                branchId == product.branchId &&
                Double.compare(product.originalPrice, originalPrice) == 0 &&
                Double.compare(product.salePrice, salePrice) == 0 &&
                Double.compare(product.priceByUnit, priceByUnit) == 0 &&
                Double.compare(product.priceByCarton, priceByCarton) == 0 &&
                stockQuantity == product.stockQuantity &&
                Objects.equals(name, product.name) &&
                Objects.equals(category, product.category) &&
                Objects.equals(createdAt, product.createdAt) &&
                Objects.equals(updatedAt, product.updatedAt);
    }

    public int hashCode() {
        return Objects.hash(productId, vendorId, branchId, name, category, originalPrice,
                salePrice, priceByUnit, priceByCarton, stockQuantity, createdAt, updatedAt);
    }
}

