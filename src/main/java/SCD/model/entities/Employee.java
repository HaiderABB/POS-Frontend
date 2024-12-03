package SCD.model.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "employees")
public class Employee {

  @Id
  @Column(name = "employee_code")
  private String employeeCode;

  @Column(name = "password", nullable = false)
  private String password = "first1234";

  @Column(name = "role", nullable = false)
  private String role;

  @ManyToOne
  @JoinColumn(name = "branch_code", referencedColumnName = "branch_code", nullable = false)
  private Branch branch; // Foreign key referring to Branch table

  @Column(name = "email", nullable = false)
  private String email;

  @Column(name = "phone_number", nullable = false)
  private String phoneNumber;

  @Column(name = "salary", nullable = false)
  private double salary;

  @Column(name = "is_first_login")
  private boolean isFirstLogin = true;

  @Column(name = "created_at")
  @Temporal(TemporalType.TIMESTAMP)
  private Date createdAt;

  @Column(name = "updated_at")
  @Temporal(TemporalType.TIMESTAMP)
  private Date updatedAt;

  // Getters and Setters

  public String getEmployeeCode() {
    return employeeCode;
  }

  public void setEmployeeCode(String employeeCode) {
    this.employeeCode = employeeCode;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public Branch getBranch() {
    return branch;
  }

  public void setBranch(Branch branch) {
    this.branch = branch;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public double getSalary() {
    return salary;
  }

  public void setSalary(double salary) {
    this.salary = salary;
  }

  public boolean isFirstLogin() {
    return isFirstLogin;
  }

  public void setFirstLogin(boolean firstLogin) {
    isFirstLogin = firstLogin;
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
}
