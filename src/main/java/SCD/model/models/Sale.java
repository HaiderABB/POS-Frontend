package SCD.model.models;

import java.util.Date;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "sales")
public class Sale {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) // Primary key auto-incremented by the database
  @Column(name = "sale_id")
  private int saleId;

  @ManyToOne
  @JoinColumn(name = "cashier_code", referencedColumnName = "employee_code", nullable = false)
  private Employee cashier; // Links to the Employee entity (users/employees table)

  @ManyToOne
  @JoinColumn(name = "branch_code", referencedColumnName = "branch_code", nullable = false)
  private Branch branch; // Links to the Branch entity (branch table)

  @Column(name = "total_amount", nullable = false)
  private double totalAmount;

  @Column(name = "sale_date", nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  private Date saleDate;

  public Sale() {
  }

  public Sale(Employee cashier, Branch branch, double totalAmount, Date saleDate) {
    this.cashier = cashier;
    this.branch = branch;
    this.totalAmount = totalAmount;
    this.saleDate = saleDate;
  }

  // Getters and Setters

  public int getSaleId() {
    return saleId;
  }

  public void setSaleId(int saleId) {
    this.saleId = saleId;
  }

  public Employee getCashier() {
    return cashier;
  }

  public void setCashier(Employee cashier) {
    this.cashier = cashier;
  }

  public Branch getBranch() {
    return branch;
  }

  public void setBranch(Branch branch) {
    this.branch = branch;
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

  @Override
  public String toString() {
    return "Sale{" +
        "saleId=" + saleId +
        ", cashier=" + (cashier != null ? cashier.getEmployeeCode() : "Unknown") +
        ", branch=" + (branch != null ? branch.getBranchCode() : "Unknown") +
        ", totalAmount=" + totalAmount +
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
    return saleId == sale.saleId &&
        Double.compare(sale.totalAmount, totalAmount) == 0 &&
        Objects.equals(cashier, sale.cashier) &&
        Objects.equals(branch, sale.branch) &&
        saleDate.equals(sale.saleDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(saleId, cashier, branch, totalAmount, saleDate);
  }
}
