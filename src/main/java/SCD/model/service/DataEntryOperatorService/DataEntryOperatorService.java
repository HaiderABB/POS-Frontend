package SCD.model.service.DataEntryOperatorService;

import java.util.List;

import SCD.model.crud.ProductDAO;
import SCD.model.crud.VendorDAO;
import SCD.model.models.Product;
import SCD.model.models.Vendor;
import SCD.model.service.AddResponseClass;
import SCD.model.service.GetResponseClass;

public class DataEntryOperatorService {

  VendorDAO vendorDAO;
  ProductDAO productDAO;

  public DataEntryOperatorService() {
    vendorDAO = VendorDAO.getInstance();
    productDAO = ProductDAO.getInstance();

  }

  public AddResponseClass addVendor(Vendor vendor) {
    boolean res;
    res = vendorDAO.vendorExistsWithPhoneNumberAndActiveStatus(vendor.getPhoneNumber());

    if (res) {
      return new AddResponseClass("Phone Number Exists", false);
    }

    vendorDAO.addVendor(vendor);

    return new AddResponseClass("Added Successfully", true);

  }

  public AddResponseClass addProduct(Product product) {

    Vendor ven;
    ven = vendorDAO.getVendorByCode(product.getVendor().getVendorCode());
    System.err.println(ven.getVendorCode());

    if (ven == null) {
      return new AddResponseClass("Could not find Vendor", false);
    }

    productDAO.addProduct(product);
    return new AddResponseClass("Added Successfully", true);

  }

  public GetResponseClass<Vendor> getVendors() {
    List<Vendor> vendors = vendorDAO.getAllActiveVendors();

    if (vendors.isEmpty()) {
      return new GetResponseClass<>("No Vendors", vendors);
    }
    return new GetResponseClass<>("Found Vendors", vendors);
  }

  public GetResponseClass<Product> getProducts() {
    List<Product> products = productDAO.getAllActiveProducts();

    if (products.isEmpty()) {
      return new GetResponseClass<>("No Products", products);
    }
    return new GetResponseClass<>("Found Products", products);
  }

  public AddResponseClass removeProduct(String code) {

    Product prod;

    prod = productDAO.getActiveProductByCode(code);

    if (prod == null) {
      return new AddResponseClass("Could not find product", false);
    }

    productDAO.deactivateProduct(code);
    return new AddResponseClass("product removed", true);

  }

  public AddResponseClass removeVendor(String code) {

    Vendor ven = vendorDAO.getVendorByCode(code);

    if (ven == null) {
      return new AddResponseClass("Could not find vendor", false);
    }

    vendorDAO.deactivateVendor(code);
    productDAO.deactivateProductsByVendor(code);

    return new AddResponseClass("Vendor removed", true);

  }

  public AddResponseClass updateVendor(Vendor vendor) {

    Vendor ven = vendorDAO.getVendorByCode(vendor.getVendorCode());
    if (ven == null) {
      return new AddResponseClass("Vendor does not exist", false);
    }
    boolean res = vendorDAO.updateVendor(vendor);

    if (res) {
      return new AddResponseClass("Vendor Updated", true);
    }
    return new AddResponseClass("Vendor not updated", false);
  }

  public AddResponseClass updateProduct(Product product) {

    Product prod = productDAO.getActiveProductByCode(product.getProductCode());
    if (prod == null) {
      return new AddResponseClass("Product does not exist", false);
    }
    boolean res = productDAO.updateProduct(product);
    if (res) {
      return new AddResponseClass("Product Updated", true);
    }
    return new AddResponseClass("Product not updated", false);

  }

}
