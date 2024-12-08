package SCD.model.service.DataEntryOperatorService;

import java.util.List;

import SCD.model.crud.local.CodesDAO;
import SCD.model.crud.local.ProductDAO;
import SCD.model.crud.local.SyncTableDAO;
import SCD.model.crud.local.VendorDAO;
import SCD.model.models.Product;
import SCD.model.models.SyncTable;
import SCD.model.models.Vendor;
import SCD.model.service.Json.AddResponseJSON;
import SCD.model.service.Json.GetResponseJSON;

public class DataEntryOperatorService {

  VendorDAO vendorDAO;
  ProductDAO productDAO;
  CodesDAO codesDAO;
  SyncTableDAO syncTableDAO;

  public DataEntryOperatorService() {
    vendorDAO = VendorDAO.getInstance();
    productDAO = ProductDAO.getInstance();
    codesDAO = CodesDAO.getInstance();
    syncTableDAO = SyncTableDAO.getInstance();

  }

  public AddResponseJSON addVendor(Vendor vendor) {
    boolean res;
    res = vendorDAO.vendorExistsWithPhoneNumberAndActiveStatus(vendor.getPhoneNumber());

    if (res) {
      return new AddResponseJSON("Phone Number Exists", false);
    }

    String empcode = codesDAO.getCodeByTableName("VENDORS");

    empcode = incrementCode(empcode);
    String temp = "VM-" + empcode;
    vendor.setVendorCode(temp);

    res = codesDAO.updateCodeByTableName("VENDORS", empcode);
    if (!res) {
      return new AddResponseJSON("Could not update code", false);
    }

    SyncTable st2 = new SyncTable("CODES", "UPDATE", "VENDORS");
    syncTableDAO.addSyncTable(st2);

    res = vendorDAO.addVendor(vendor);

    if (!res) {
      return new AddResponseJSON("Could not add Vendor", false);
    }

    SyncTable st = new SyncTable("VENDORS", "INSERT", temp);
    syncTableDAO.addSyncTable(st);

    return new AddResponseJSON("Added Successfully", true);

  }

  public AddResponseJSON addProduct(Product product) {
    boolean res;
    Vendor ven;
    ven = vendorDAO.getVendorByCode(product.getVendor().getVendorCode());

    if (ven == null) {
      return new AddResponseJSON("Could not find Vendor", false);
    }

    String empcode = codesDAO.getCodeByTableName("PRODUCTS");

    empcode = incrementCode(empcode);
    String temp = "PM-" + empcode;
    product.setProductCode(temp);

    res = codesDAO.updateCodeByTableName("PRODUCTS", empcode);
    if (!res) {
      return new AddResponseJSON("Could not update code", false);
    }
    SyncTable st2 = new SyncTable("CODES", "UPDATE", "PRODUCTS");
    syncTableDAO.addSyncTable(st2);
    res = productDAO.addProduct(product);
    if (!res) {
      return new AddResponseJSON("Error Adding product", true);
    }
    SyncTable st = new SyncTable("PRODUCTS", "INSERT", temp);
    syncTableDAO.addSyncTable(st);

    return new AddResponseJSON("Added Successfully", true);

  }

  public String incrementCode(String code) {

    int numericCode = Integer.parseInt(code);

    // Increment the numeric part
    numericCode++;

    return String.format("%04d", numericCode);
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

    boolean res = productDAO.deactivateProduct(code);

    if (!res) {
      return new AddResponseJSON("Could not deactivate product", false);
    }

    SyncTable st = new SyncTable("PRODUCTS", "UPDATE", code);
    syncTableDAO.addSyncTable(st);

    return new AddResponseJSON("product removed", true);

  }

  public AddResponseJSON removeVendor(String code) {

    Vendor ven = vendorDAO.getVendorByCode(code);

    if (ven == null) {
      return new AddResponseJSON("Could not find vendor", false);
    }

    boolean res = vendorDAO.deactivateVendor(code);
    if (!res) {
      return new AddResponseJSON("Could not deactivate vendor", false);
    }
    SyncTable st = new SyncTable("VENDORS", "UPDATE", code);
    syncTableDAO.addSyncTable(st);

    res = productDAO.deactivateProductsByVendor(code);

    List<Product> products = productDAO.getProductsByVendorCode(code);

    if (!res) {
      return new AddResponseJSON("Could not deactivate products by vendors", false);
    }

    for (Product p : products) {
      syncTableDAO.addSyncTable(new SyncTable("PRODUCTS", "UPDATE", p.getProductCode()));
    }

    return new AddResponseJSON("Vendor removed", true);

  }

  public AddResponseJSON updateVendor(Vendor vendor) {

    Vendor ven = vendorDAO.getVendorByCode(vendor.getVendorCode());
    if (ven == null) {
      return new AddResponseJSON("Vendor does not exist", false);
    }
    boolean res = vendorDAO.updateVendor(vendor);

    if (res) {
      SyncTable st = new SyncTable("VENDORS", "UPDATE", vendor.getVendorCode());
      syncTableDAO.addSyncTable(st);
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
      SyncTable st = new SyncTable("PRODUCTS", "UPDATE", product.getProductCode());
      syncTableDAO.addSyncTable(st);
      return new AddResponseJSON("Product Updated", true);

    }

    return new AddResponseJSON("Product not updated", false);

  }

}
