package SCD.model.models;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "branches")
public class Branch {

  @Id
  @Column(name = "branch_code", nullable = false, unique = true)
  private String branchCode;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "city", nullable = false)
  private String city;

  @OneToMany(mappedBy = "branch", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
  private List<Employee> employees;

  @OneToMany(mappedBy = "branch", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
  private List<Sale> sales;

  @Column(name = "address", nullable = false)
  private String address;

  @Column(name = "phone", nullable = false)
  private String phone;

  @Column(name = "total_employees", nullable = false)
  private int totalEmployees = 0;

  @Column(name = "is_active", nullable = false)
  private boolean isActive = true;

  @Column(name = "created_at", nullable = false)
  private LocalDateTime createdAt;

  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  public Branch() {

    this.createdAt = LocalDateTime.now();
    this.updatedAt = LocalDateTime.now();
  }

  public Branch(String name, String city, String address, String phone) {
    this.name = name;
    this.city = city;
    this.address = address;
    this.phone = phone;
    this.createdAt = LocalDateTime.now();
    this.updatedAt = LocalDateTime.now();
  }



    public String getBranchCode() {
    return branchCode;
  }

  public void setBranchCode(String branchCode) {
    this.branchCode = branchCode;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public int getTotalEmployees() {
    return totalEmployees;
  }

  public void setTotalEmployees(int totalEmployees) {
    this.totalEmployees = totalEmployees;
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
    return "Branch{" +
        "branchCode='" + branchCode + '\'' +
        ", name='" + name + '\'' +
        ", city='" + city + '\'' +
        ", address='" + address + '\'' +
        ", phone='" + phone + '\'' +
        ", totalEmployees=" + totalEmployees +
        ", isActive=" + isActive +
        ", createdAt=" + createdAt +
        ", updatedAt=" + updatedAt +
        '}';
  }

  public List<Employee> getEmployees() {
    return employees;
  }

  public void setEmployees(List<Employee> employees) {
    this.employees = employees;
  }

  public List<Sale> getSales() {
    return sales;
  }

  public void setSales(List<Sale> sales) {
    this.sales = sales;
  }

}
