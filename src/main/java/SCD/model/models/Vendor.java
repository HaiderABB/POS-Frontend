package SCD.model.models;

import java.util.Objects;

public class Vendor {
    private int vendorId;
    private String vendor_code;
    private String name;
    private String phone_number;
    private String email;
    private String address;

    public Vendor(int vendorId, int branchId, String name, String phone_number, String email, String address) {
        this.vendorId = vendorId;
        this.name = name;
        this.phone_number = phone_number;
        this.email = email;
        this.address = address;
    }

    public int getVendorId() {
        return vendorId;
    }

    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactInfo() {
        return phone_number;
    }

    public void setContactInfo(String phone_number) {
        this.phone_number = phone_number;
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
                ", name='" + name + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Vendor vendor = (Vendor) o;
        return vendorId == vendor.vendorId &&
                Objects.equals(name, vendor.name) &&
                Objects.equals(phone_number, vendor.phone_number) &&
                Objects.equals(email, vendor.email) &&
                Objects.equals(address, vendor.address);
    }

    public int hashCode() {
        return Objects.hash(vendorId, name, phone_number, email, address);
    }

    public String getVendor_code() {
        return vendor_code;
    }

    public void setVendor_code(String vendor_code) {
        this.vendor_code = vendor_code;
    }
}
