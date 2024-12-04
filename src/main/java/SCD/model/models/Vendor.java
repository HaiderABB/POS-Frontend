package SCD.model.models;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "vendors")
public class Vendor {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "vendor_id")
  private int vendorId;

  @Column(name = "vendor_code", nullable = false, unique = true)
  private String vendorCode;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "phone_number", nullable = false)
  private String phoneNumber;

  @Column(name = "address")
  private String address;

  public Vendor() {
  }

  public Vendor(int vendorId, String vendorCode, String name, String phoneNumber, String email, String address) {
    this.vendorId = vendorId;
    this.vendorCode = vendorCode;
    this.name = name;
    this.phoneNumber = phoneNumber;
    this.address = address;
  }

  public int getVendorId() {
    return vendorId;
  }

  public void setVendorId(int vendorId) {
    this.vendorId = vendorId;
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

  @Override
  public String toString() {
    return "Vendor{" +
        "vendorId=" + vendorId +
        ", vendorCode='" + vendorCode + '\'' +
        ", name='" + name + '\'' +
        ", phoneNumber='" + phoneNumber + '\'' + '\'' +
        ", address='" + address + '\'' +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Vendor vendor = (Vendor) o;
    return vendorId == vendor.vendorId &&
        Objects.equals(vendorCode, vendor.vendorCode) &&
        Objects.equals(name, vendor.name) &&
        Objects.equals(phoneNumber, vendor.phoneNumber) &&
        Objects.equals(address, vendor.address);
  }

  @Override
  public int hashCode() {
    return Objects.hash(vendorId, vendorCode, name, phoneNumber, address);
  }
}
