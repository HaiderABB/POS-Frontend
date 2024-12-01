package SCD.model.models;

import java.util.Date;
import java.util.Objects;

public class Sale {
    private int sale_id; // incremented in the database
    private String cashier_code; // Foreign Key to users where role = 'Cashier'
    private String branch_code; // Foreign Key to branches
    private double total_amount;
    private Date saleDate;

    public Sale(int sale_id, String cashier_code, String branch_code, double total_amount, Date saleDate) {
        this.sale_id = sale_id;
        this.cashier_code = cashier_code;
        this.branch_code = branch_code;
        this.total_amount = total_amount;
        this.saleDate = saleDate;
    }

    public int getSaleId() {
        return sale_id;
    }

    public void setSaleId(int sale_id) {
        this.sale_id = sale_id;
    }

    public double getTotalAmount() {
        return total_amount;
    }

    public void setTotalAmount(double total_amount) {
        this.total_amount = total_amount;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "sale_id=" + sale_id +
                ", cashier_code=" + cashier_code +
                ", branch_code=" + branch_code +
                ", total_amount=" + total_amount +
                ", saleDate=" + saleDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Sale sale = (Sale) o;
        return sale_id == sale.sale_id &&
                (cashier_code == null ? sale.cashier_code == null : cashier_code.equals(sale.cashier_code)) &&
                (branch_code == null ? sale.branch_code == null : branch_code.equals(sale.branch_code)) &&
                Double.compare(sale.total_amount, total_amount) == 0 &&
                Objects.equals(saleDate, sale.saleDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sale_id, cashier_code, branch_code, total_amount, saleDate);
    }

    public String getCashierCode() {
        return cashier_code;
    }

    public void setCashierCode(String cashier_code) {
        this.cashier_code = cashier_code;
    }

    public String getBranchCode() {
        return branch_code;
    }

    public void setBranchCode(String branch_code) {
        this.branch_code = branch_code;
    }
}
