package SCD.model.service.DataEntryOperatorService;

import SCD.model.crud.ProductDAO;
import SCD.model.crud.VendorDAO;
import SCD.model.models.Vendor;

public class DataEntryOperatorService {

  VendorDAO vendorDAO;
  ProductDAO productDAO;

  DataEntryOperatorService() {
    vendorDAO = new VendorDAO();
    productDAO = new ProductDAO();

  }

  public void addVendor(Vendor vendor) {

  }

}
