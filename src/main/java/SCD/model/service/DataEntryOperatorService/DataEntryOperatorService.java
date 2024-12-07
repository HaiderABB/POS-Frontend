package SCD.model.service.DataEntryOperatorService;

import java.util.List;

import SCD.model.crud.ProductDAO;
import SCD.model.crud.VendorDAO;
import SCD.model.models.Product;
import SCD.model.models.Vendor;
import SCD.model.service.AddResponseJSON;
import SCD.model.service.GetResponseJSON;

public class DataEntryOperatorService {

  VendorDAO vendorDAO;
  ProductDAO productDAO;

  public DataEntryOperatorService() {
    vendorDAO = VendorDAO.getInstance();
    productDAO = ProductDAO.getInstance();

  }

  public AddResponseJSON addVendor(Vendor vendor) {
    boolean res;
    res = vendorDAO.vendorExistsWithPhoneNumberAndActiveStatus(vendor.getPhoneNumber());

    if (res) {
      return new AddResponseJSON("Phone Number Exists", false);
    }

    vendorDAO.addVendor(vendor);

    return new AddResponseJSON("Added Successfully", true);

  }

  public AddResponseJSON addProduct(Product product) {

    Vendor ven;
    ven = vendorDAO.getVendorByCode(product.getVendor().getVendorCode());
    System.err.println(ven.getVendorCode());

    if (ven == null) {
      return new AddResponseJSON("Could not find Vendor", false);
    }

    productDAO.addProduct(product);
    return new AddResponseJSON("Added Successfully", true);

  }

  public GetResponseJSON<Vendor> getVendors() {
    List<Vendor> vendors = vendorDAO.getAllActiveVendors();

    if (vendors.isEmpty()) {
      return new GetResponseJSON<>("No Vendors", vendors);
    }
    return new GetResponseJSON<>("Found Vendors", vendors);
  }

  public GetResponseJSON<Product> getProducts() {
    List<Product> products = productDAO.getAllActiveProducts();

    if (products.isEmpty()) {
      return new GetResponseJSON<>("No Products", products);
    }
    return new GetResponseJSON<>("Found Products", products);
  }

  public AddResponseJSON removeProduct(String code) {

    Product prod;

    prod = productDAO.getActiveProductByCode(code);

    if (prod == null) {
      return new AddResponseJSON("Could not find product", false);
    }

    productDAO.deactivateProduct(code);
    return new AddResponseJSON("product removed", true);

  }

  public AddResponseJSON removeVendor(String code) {

    Vendor ven = vendorDAO.getVendorByCode(code);

    if (ven == null) {
      return new AddResponseJSON("Could not find vendor", false);
    }

    vendorDAO.deactivateVendor(code);
    productDAO.deactivateProductsByVendor(code);

    return new AddResponseJSON("Vendor removed", true);

  }

  public AddResponseJSON updateVendor(Vendor vendor) {

    Vendor ven = vendorDAO.getVendorByCode(vendor.getVendorCode());
    if (ven == null) {
      return new AddResponseJSON("Vendor does not exist", false);
    }
    boolean res = vendorDAO.updateVendor(vendor);

    if (res) {
      return new AddResponseJSON("Vendor Updated", true);
    }
    return new AddResponseJSON("Vendor not updated", false);
  }

  public AddResponseJSON updateProduct(Product product) {

    Product prod = productDAO.getActiveProductByCode(product.getProductCode());
    if (prod == null) {
      return new AddResponseJSON("Product does not exist", false);
    }
    boolean res = productDAO.updateProduct(product);
    if (res) {
      return new AddResponseJSON("Product Updated", true);
    }
    return new AddResponseJSON("Product not updated", false);

  }

}
