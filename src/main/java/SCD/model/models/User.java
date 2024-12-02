package SCD.model.models;

import java.util.Date;
import java.util.Objects;

public class User {
    private int user_id;
    private String username; // U
    private String password; // U
    private String role;
    private String branch_code; // U
    private String employee_code; // U
    private String email; // U
    private String phone_number; // U
    private double salary;
    private boolean isFirstLogin;
    private Date createdAt;
    private Date updatedAt;

    public User() {
        this.createdAt = new Date();
        this.isFirstLogin = true;
    }

    public User(int user_id, String username, String password, String role, String branch_code, String employee_code,
            String email, double salary, boolean isFirstLogin, Date createdAt, Date updatedAt) {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.branch_code = branch_code;
        this.employee_code = employee_code;
        this.email = email;
        this.salary = salary;
        this.isFirstLogin = isFirstLogin;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getuser_id() {
        return user_id;
    }

    public void setuser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getBranchCode() {
        return branch_code;
    }

    public void setBranchCode(String branch_code) {
        this.branch_code = branch_code;
    }

    public String getEmployeeCode() {
        return employee_code;
    }

    public void setEmployeeCode(String employee_code) {
        this.employee_code = employee_code;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getPhoneNumber() {
        return phone_number;
    }

    public void setPhoneNumber(String phone_number) {
        this.phone_number = phone_number;
    }

    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", username='" + username + '\'' +
                ", role='" + role + '\'' +
                ", branch_code=" + branch_code +
                ", employee_code='" + employee_code + '\'' +
                ", email='" + email + '\'' +
                ", salary=" + salary +
                ", isFirstLogin=" + isFirstLogin +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        User user = (User) o;
        return user_id == user.user_id &&
                Double.compare(user.salary, salary) == 0 &&
                isFirstLogin == user.isFirstLogin &&
                Objects.equals(username, user.username) &&
                Objects.equals(password, user.password) &&
                Objects.equals(role, user.role) &&
                Objects.equals(branch_code, user.branch_code) &&
                Objects.equals(employee_code, user.employee_code) &&
                Objects.equals(email, user.email) &&
                Objects.equals(createdAt, user.createdAt) &&
                Objects.equals(updatedAt, user.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_id, username, password, role, branch_code, employee_code, email, salary, isFirstLogin,
                createdAt,
                updatedAt);
    }

}
