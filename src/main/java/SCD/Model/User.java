package SCD.Model;
import java.util.Date;
import java.util.Objects;

public class User {
    private int userId;
    private String username;
    private String password;
    private String role;
    private Integer branchId;
    private String empNo;
    private String email;
    private double salary;
    private boolean isFirstLogin;
    private Date createdAt;
    private Date updatedAt;


    public User() {
        this.createdAt = new Date();
        this.isFirstLogin = true;
    }


    public User(int userId, String username, String password, String role, Integer branchId, String empNo,
                String email, double salary, boolean isFirstLogin, Date createdAt, Date updatedAt) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.role = role;
        this.branchId = branchId;
        this.empNo = empNo;
        this.email = email;
        this.salary = salary;
        this.isFirstLogin = isFirstLogin;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public Integer getBranchId() {
        return branchId;
    }

    public void setBranchId(Integer branchId) {
        this.branchId = branchId;
    }

    public String getEmpNo() {
        return empNo;
    }

    public void setEmpNo(String empNo) {
        this.empNo = empNo;
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

    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", role='" + role + '\'' +
                ", branchId=" + branchId +
                ", empNo='" + empNo + '\'' +
                ", email='" + email + '\'' +
                ", salary=" + salary +
                ", isFirstLogin=" + isFirstLogin +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId &&
                Double.compare(user.salary, salary) == 0 &&
                isFirstLogin == user.isFirstLogin &&
                Objects.equals(username, user.username) &&
                Objects.equals(password, user.password) &&
                Objects.equals(role, user.role) &&
                Objects.equals(branchId, user.branchId) &&
                Objects.equals(empNo, user.empNo) &&
                Objects.equals(email, user.email) &&
                Objects.equals(createdAt, user.createdAt) &&
                Objects.equals(updatedAt, user.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, username, password, role, branchId, empNo, email, salary, isFirstLogin, createdAt, updatedAt);
    }
}

