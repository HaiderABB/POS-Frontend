package SCD.Model;

import java.util.Objects;

public class Vendor {
    private int vendorId;
    private int branchId;
    private String name;
    private String contactInfo;
    private String email;
    private String address;

    public Vendor(int vendorId, int branchId, String name, String contactInfo, String email, String address) {
        this.vendorId = vendorId;
        this.branchId = branchId;
        this.name = name;
        this.contactInfo = contactInfo;
        this.email = email;
        this.address = address;
    }

    public int getVendorId() {
        return vendorId;
    }

    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
    }

    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public String toString() {
        return "Vendor{" +
                "vendorId=" + vendorId +
                ", branchId=" + branchId +
                ", name='" + name + '\'' +
                ", contactInfo='" + contactInfo + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                '}';
    }


    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vendor vendor = (Vendor) o;
        return vendorId == vendor.vendorId &&
                branchId == vendor.branchId &&
                Objects.equals(name, vendor.name) &&
                Objects.equals(contactInfo, vendor.contactInfo) &&
                Objects.equals(email, vendor.email) &&
                Objects.equals(address, vendor.address);
    }

    public int hashCode() {
        return Objects.hash(vendorId, branchId, name, contactInfo, email, address);
    }
}
