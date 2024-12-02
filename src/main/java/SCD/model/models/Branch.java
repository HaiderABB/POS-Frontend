package SCD.model.models;

import java.util.Date;
import java.util.Objects;

public class Branch {
    private int branch_id;
    private String branch_code; // U
    private String name; // U
    private String city; // U
    private String address; // U
    private String phone; // U
    private int total_employees;
    private int created_by;
    private boolean isActive;
    private Date createdAt;

    public Branch() {
        this.createdAt = new Date();
        this.isActive = true;
    }

    public Branch(int branch_id, String branch_code, String name, String city, String address,
            String phone, int total_employees, int created_by, boolean isActive, Date createdAt) {
        this.branch_id = branch_id;
        this.branch_code = branch_code;
        this.name = name;
        this.city = city;
        this.address = address;
        this.phone = phone;
        this.total_employees = total_employees;
        this.created_by = created_by;
        this.isActive = isActive;
        this.createdAt = createdAt;
    }

    public int getbranch_id() {
        return branch_id;
    }

    public void setbranch_id(int branch_id) {
        this.branch_id = branch_id;
    }

    public String getbranch_code() {
        return branch_code;
    }

    public void setbranch_code(String branch_code) {
        this.branch_code = branch_code;
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

    public int gettotal_employees() {
        return total_employees;
    }

    public void settotal_employees(int total_employees) {
        this.total_employees = total_employees;
    }

    public int getcreated_by() {
        return created_by;
    }

    public void setcreated_by(int created_by) {
        this.created_by = created_by;
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

    public String toString() {
        return "Branch{" +
                "branch_id=" + branch_id +
                ", branch_code='" + branch_code + '\'' +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", total_employees=" + total_employees +
                ", created_by=" + created_by +
                ", isActive=" + isActive +
                ", createdAt=" + createdAt +
                '}';
    }

    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Branch branch = (Branch) o;
        return branch_id == branch.branch_id &&
                total_employees == branch.total_employees &&
                created_by == branch.created_by &&
                isActive == branch.isActive &&
                Objects.equals(branch_code, branch.branch_code) &&
                Objects.equals(name, branch.name) &&
                Objects.equals(city, branch.city) &&
                Objects.equals(address, branch.address) &&
                Objects.equals(phone, branch.phone) &&
                Objects.equals(createdAt, branch.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(branch_id, branch_code, name, city, address, phone, total_employees, created_by, isActive,
                createdAt);
    }
}
