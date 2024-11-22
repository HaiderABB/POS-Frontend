package SCD.Model;

import java.util.Date;
import java.util.Objects;

public class Branch {
    private int branchId;
    private String branchCode;
    private String name;
    private String city;
    private String address;
    private String phone;
    private int noOfEmployees;
    private int createdBy; // SuperAdmin created this branch
    private boolean isActive;
    private Date createdAt;

    public Branch() {
        this.createdAt = new Date();
        this.isActive = true;
    }

    public Branch(int branchId, String branchCode, String name, String city, String address,
                  String phone, int noOfEmployees, int createdBy, boolean isActive, Date createdAt) {
        this.branchId = branchId;
        this.branchCode = branchCode;
        this.name = name;
        this.city = city;
        this.address = address;
        this.phone = phone;
        this.noOfEmployees = noOfEmployees;
        this.createdBy = createdBy;
        this.isActive = isActive;
        this.createdAt = createdAt;
    }


    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
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

    public int getNoOfEmployees() {
        return noOfEmployees;
    }

    public void setNoOfEmployees(int noOfEmployees) {
        this.noOfEmployees = noOfEmployees;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
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
                "branchId=" + branchId +
                ", branchCode='" + branchCode + '\'' +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", noOfEmployees=" + noOfEmployees +
                ", createdBy=" + createdBy +
                ", isActive=" + isActive +
                ", createdAt=" + createdAt +
                '}';
    }

    // Override equals() and hashCode() for comparing objects

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Branch branch = (Branch) o;
        return branchId == branch.branchId &&
                noOfEmployees == branch.noOfEmployees &&
                createdBy == branch.createdBy &&
                isActive == branch.isActive &&
                Objects.equals(branchCode, branch.branchCode) &&
                Objects.equals(name, branch.name) &&
                Objects.equals(city, branch.city) &&
                Objects.equals(address, branch.address) &&
                Objects.equals(phone, branch.phone) &&
                Objects.equals(createdAt, branch.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(branchId, branchCode, name, city, address, phone, noOfEmployees, createdBy, isActive, createdAt);
    }
}

