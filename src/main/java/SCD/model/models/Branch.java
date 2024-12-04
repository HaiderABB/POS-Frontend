package SCD.model.models;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "branches")
public class Branch {

  @Id
  @Column(name = "branch_code", nullable = false, unique = true) // Primary key
  private String branchCode;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "city", nullable = false)
  private String city;

  @Column(name = "address", nullable = false)
  private String address;

  @Column(name = "phone", nullable = false)
  private String phone;

  @Column(name = "total_employees", nullable = false)
  private int totalEmployees = 0; // Default to 0

  @Column(name = "is_active", nullable = false)
  private boolean isActive = true; // Default to true

  @Column(name = "created_at")
  @Temporal(TemporalType.TIMESTAMP)
  private Date createdAt;

  public Branch() {
  }

  public Branch(String branchCode, String name, String city, String address, String phone, int totalEmployees,
      boolean isActive, Date createdAt) {
    this.branchCode = branchCode;
    this.name = name;
    this.city = city;
    this.address = address;
    this.phone = phone;
    this.totalEmployees = totalEmployees;
    this.isActive = isActive;
    this.createdAt = createdAt;
  }

  // Getters and Setters

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

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
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
        '}';
  }
}
