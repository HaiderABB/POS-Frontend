package SCD.model.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "vendors")
public class Vendor {

  @Id
  @Column(name = "vendor_code", nullable = false, unique = true)
  private String vendorCode;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "phone_number", nullable = false)
  private String phoneNumber;

  @Column(name = "address")
  private String address;

  @Column(name = "is_active", nullable = false)
  private boolean isActive = true; // New field with default value

  public Vendor() {
  }

  public Vendor(String vendorCode, String name, String phoneNumber, String address) {
    this.vendorCode = vendorCode;
    this.name = name;
    this.phoneNumber = phoneNumber;
    this.address = address;
    this.isActive = true; // Default value for parameterized constructor
  }

  public String getVendorCode() {
    return vendorCode;
  }

  public void setVendorCode(String vendorCode) {
    this.vendorCode = vendorCode;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public boolean isActive() {
    return isActive;
  }

  public void setActive(boolean active) {
    isActive = active;
  }

  @Override
  public String toString() {
    return "Vendor{'vendorCode='" + vendorCode + '\'' +
        ", name='" + name + '\'' +
        ", phoneNumber='" + phoneNumber + '\'' +
        ", address='" + address + '\'' +
        ", isActive=" + isActive +
        '}';
  }

}
