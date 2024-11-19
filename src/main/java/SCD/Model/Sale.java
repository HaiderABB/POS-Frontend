package SCD.Model;

import java.util.Date;
import java.util.Objects;

public class Sale {
    private int saleId;
    private int cashierId; // Foreign Key to users where role = 'Cashier'
    private int branchId;
    private double totalAmount;
    private Date saleDate;



    public Sale(int saleId, int cashierId, int branchId, double totalAmount, Date saleDate) {
        this.saleId = saleId;
        this.cashierId = cashierId;
        this.branchId = branchId;
        this.totalAmount = totalAmount;
        this.saleDate = saleDate;
    }

    public int getSaleId() {
        return saleId;
    }

    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }

    public int getCashierId() {
        return cashierId;
    }

    public void setCashierId(int cashierId) {
        this.cashierId = cashierId;
    }

    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public String toString() {
        return "Sale{" +
                "saleId=" + saleId +
                ", cashierId=" + cashierId +
                ", branchId=" + branchId +
                ", totalAmount=" + totalAmount +
                ", saleDate=" + saleDate +
                '}';
    }


    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sale sale = (Sale) o;
        return saleId == sale.saleId &&
                cashierId == sale.cashierId &&
                branchId == sale.branchId &&
                Double.compare(sale.totalAmount, totalAmount) == 0 &&
                Objects.equals(saleDate, sale.saleDate);
    }

    public int hashCode() {
        return Objects.hash(saleId, cashierId, branchId, totalAmount, saleDate);
    }
}

