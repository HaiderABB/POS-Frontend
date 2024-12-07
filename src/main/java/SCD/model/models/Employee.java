package SCD.model.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "employees")
public class Employee {

  @Id
  @Column(name = "employee_code")
  private String employeeCode;

  @Column(name = "password", nullable = false)
  private String password = "first1234";

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "role", nullable = false)
  private String role;

  @ManyToOne
  @JoinColumn(name = "branch_code", referencedColumnName = "branch_code", nullable = false)
  private Branch branch;

  @Column(name = "phone_number", nullable = false)
  private String phoneNumber;

  @Column(name = "salary", nullable = false)
  private double salary;

  @Column(name = "is_first_login")
  private boolean isFirstLogin = true;

  @Column(name = "is_active", nullable = false)
  private boolean isActive = true;

  @Column(name = "email", nullable = false, unique = true)
  private String email;

  @Column(name = "created_at", nullable = false)
  private LocalDateTime createdAt;

  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  public Employee() {
  }

  public Employee(String employeeCode, String name, String role, Branch branch, String phoneNumber, double salary,
      String email) {
    this.employeeCode = employeeCode;
    this.name = name;
    this.role = role;
    this.branch = branch;
    this.phoneNumber = phoneNumber;
    this.salary = salary;
    this.email = email;
    this.createdAt = LocalDateTime.now();
    this.updatedAt = LocalDateTime.now();
  }

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

  public boolean isActive() {
    return isActive;
  }

  public void setActive(boolean active) {
    isActive = active;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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
    return "Employee{" +
        "employeeCode='" + employeeCode + '\'' +
        ", name='" + name + '\'' +
        ", role='" + role + '\'' +
        ", branch=" + (branch != null ? branch.getBranchCode() : "null") +
        ", phoneNumber='" + phoneNumber + '\'' +
        ", salary=" + salary +
        ", isFirstLogin=" + isFirstLogin +
        ", isActive=" + isActive +
        ", email='" + email + '\'' +
        ", createdAt=" + createdAt +
        ", updatedAt=" + updatedAt +
        '}';
  }
}
