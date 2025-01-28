package SCD.model.service.DataEntryOperatorService;

import java.util.List;

import SCD.model.db.local.CodesDAO;
import SCD.model.db.local.ProductDAO;
import SCD.model.db.local.SyncTableDAO;
import SCD.model.db.local.VendorDAO;
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

    String empcode = codesDAO.getCodeByTableName("VENDORS");

    empcode = incrementCode(empcode);
    String temp = "VM-" + empcode;
    vendor.setVendorCode(temp);

    res = codesDAO.updateCodeByTableName("VENDORS", empcode);
    if (!res) {
      return new AddResponseJSON("Could not update code", false, null);
    }

    SyncTable st2 = new SyncTable("CODES", "UPDATE", "VENDORS");
    syncTableDAO.addSyncTable(st2);

    res = vendorDAO.addVendor(vendor);

    if (!res) {
      return new AddResponseJSON("Could not add Vendor", false, null);
    }

    SyncTable st = new SyncTable("VENDORS", "INSERT", temp);
    syncTableDAO.addSyncTable(st);

    return new AddResponseJSON("Added Successfully", true, temp);

  }

  public AddResponseJSON addProduct(Product product) {
    boolean res;

    String empcode = codesDAO.getCodeByTableName("PRODUCTS");

    empcode = incrementCode(empcode);
    String temp = "PM-" + empcode;
    product.setProductCode(temp);

    res = codesDAO.updateCodeByTableName("PRODUCTS", empcode);
    if (!res) {
      return new AddResponseJSON("Could not update code", false, null);
    }
    SyncTable st2 = new SyncTable("CODES", "UPDATE", "PRODUCTS");
    syncTableDAO.addSyncTable(st2);
    res = productDAO.addProduct(product);
    if (!res) {
      return new AddResponseJSON("Error Adding product", true, null);
    }
    SyncTable st = new SyncTable("PRODUCTS", "INSERT", temp);
    syncTableDAO.addSyncTable(st);

    return new AddResponseJSON("Added Successfully", true, temp);

  }

  public String incrementCode(String code) {

    int numericCode = Integer.parseInt(code);

    numericCode++;

    return String.format("%04d", numericCode);
  }

  public GetResponseJSON<Vendor> getVendors() {
    List<Vendor> vendors = vendorDAO.getAllActiveVendors();

    if (vendors == null) {
      return new GetResponseJSON<>("No Vendors", vendors);
    }
    return new GetResponseJSON<>("Found Vendors", vendors);
  }

  public GetResponseJSON<Product> getProducts() {
    List<Product> products = productDAO.getAllActiveProducts();

    if (products == null) {
      return new GetResponseJSON<>("No Products", null);
    }
    return new GetResponseJSON<>("Found Products", products);
  }

  public AddResponseJSON removeProduct(String code) {

    boolean res = productDAO.deactivateProduct(code);

    if (!res) {
      return new AddResponseJSON("Could not deactivate product", false, null);
    }

    SyncTable st = new SyncTable("PRODUCTS", "UPDATE", code);
    syncTableDAO.addSyncTable(st);

    return new AddResponseJSON("product removed", true, null);

  }

  public Product getProductByCode(String code) {
    return productDAO.getActiveProductByCode(code);
  }

  public Vendor getVendorByCode(String code) {
    return vendorDAO.getVendorByCode(code);
  }

  public AddResponseJSON removeVendor(String code) {

    boolean res = vendorDAO.deactivateVendor(code);
    if (!res) {
      return new AddResponseJSON("Could not deactivate vendor", false, null);
    }
    SyncTable st = new SyncTable("VENDORS", "UPDATE", code);
    syncTableDAO.addSyncTable(st);

    List<Product> products = productDAO.getProductsByVendorCode(code);

    if (!res) {
      return new AddResponseJSON("Could not deactivate products by vendors", false, null);
    }

    if (products != null) {
      for (Product p : products) {
        res = productDAO.deactivateProduct(p.getProductCode());
        if (!res) {
          return new AddResponseJSON("Could not deactivate products by vendors", false, null);
        }
        syncTableDAO.addSyncTable(new SyncTable("PRODUCTS", "UPDATE", p.getProductCode()));

      }
    }

    return new AddResponseJSON("Vendor removed", true, null);

  }

  public boolean checkPhoneNumber(String phoneNumber) {
    return vendorDAO.vendorExistsWithPhoneNumberAndActiveStatus(phoneNumber);
  }

  public AddResponseJSON updateVendor(Vendor vendor) {

    Vendor ven = vendorDAO.getVendorByCode(vendor.getVendorCode());
    if (ven == null) {
      return new AddResponseJSON("Vendor does not exist", false, vendor.getVendorCode());
    }
    boolean res = vendorDAO.updateVendor(vendor);

    if (res) {
      SyncTable st = new SyncTable("VENDORS", "UPDATE", vendor.getVendorCode());
      syncTableDAO.addSyncTable(st);
      return new AddResponseJSON("Vendor Updated", true, vendor.getVendorCode());
    }

    return new AddResponseJSON("Vendor not updated", false, vendor.getVendorCode());
  }

  public AddResponseJSON updateProduct(Product product) {

    boolean res = productDAO.updateProduct(product);
    if (res) {
      SyncTable st = new SyncTable("PRODUCTS", "UPDATE", product.getProductCode());
      syncTableDAO.addSyncTable(st);
      return new AddResponseJSON("Product Updated", true, product.getProductCode());

    }

    return new AddResponseJSON("Product not updated", false, product.getProductCode());

  }

  public GetResponseJSON<Product> getProductsByVendorCode(String vendorCode) {
    List<Product> products = productDAO.getProductsByVendorCode(vendorCode);
    System.out.println(products == null);
    if (products == null || products.isEmpty()) {
      return new GetResponseJSON<>("No Products", null);
    }
    return new GetResponseJSON<>("Found Products", products);
  }

}
