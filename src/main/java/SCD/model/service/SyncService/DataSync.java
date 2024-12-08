package SCD.model.service.SyncService;

import java.util.List;

import SCD.model.crud.local.BranchesDAO;
import SCD.model.crud.local.CodesDAO;
import SCD.model.crud.local.EmployeeDAO;
import SCD.model.crud.local.ProductDAO;
import SCD.model.crud.local.SaleDAO;
import SCD.model.crud.local.SaleItemDAO;
import SCD.model.crud.local.SyncTableDAO;
import SCD.model.crud.local.VendorDAO;
import SCD.model.crud.remote.DAO;
import SCD.model.models.Branch;
import SCD.model.models.Codes;
import SCD.model.models.Employee;
import SCD.model.models.Product;
import SCD.model.models.Sale;
import SCD.model.models.SaleItem;
import SCD.model.models.SyncTable;
import SCD.model.models.Vendor;

public class DataSync {

  SyncTableDAO syncTableDAO;
  EmployeeDAO employeeDAO;
  CodesDAO codesDAO;
  SaleItemDAO saleItemDAO;
  SaleDAO saleDAO;
  VendorDAO vendorDAO;
  ProductDAO productDAO;
  BranchesDAO branchDAO;
  DAO remoteDAO;

  public DataSync() {
    syncTableDAO = SyncTableDAO.getInstance();
    employeeDAO = EmployeeDAO.getInstance();
    codesDAO = CodesDAO.getInstance();
    saleItemDAO = SaleItemDAO.getInstance();
    saleDAO = SaleDAO.getInstance();
    vendorDAO = VendorDAO.getInstance();
    productDAO = ProductDAO.getInstance();
    branchDAO = BranchesDAO.getInstance();
    remoteDAO = DAO.getInstance();

  }

  public void syncData() {

    List<SyncTable> syncTables = syncTableDAO.getAllSyncTableEntries();

    if (syncTables.isEmpty()) {
      return;
    }
    for (SyncTable syncTable : syncTables) {

      if (syncTable.getOperationType().equals("INSERT") && syncTable.getTableName().equals("EMPLOYEES")) {
        Employee emp = employeeDAO.getEmployeeByEmployeeCode(syncTable.getKeyColumn());
        remoteDAO.addEmployee(emp);

      } else if (syncTable.getOperationType().equals("UPDATE") && syncTable.getTableName().equals("EMPLOYEES")) {
        Employee emp = employeeDAO.getEmployeeByEmployeeCode(syncTable.getKeyColumn());
        remoteDAO.updateEmployeeIfExists(emp);
      } else if (syncTable.getOperationType().equals("UPDATE") && syncTable.getTableName().equals("CODES")) {
        String code = codesDAO.getCodeByTableName(syncTable.getKeyColumn());
        remoteDAO.updateCodeByTableName(syncTable.getKeyColumn(), code);
      } else if (syncTable.getOperationType().equals("INSERT") && syncTable.getTableName().equals("BRANCHES")) {
        Branch br = branchDAO.getBranchByCode(syncTable.getKeyColumn());
        remoteDAO.addBranch(br);
      } else if (syncTable.getOperationType().equals("UPDATE") && syncTable.getTableName().equals("BRANCHES")) {
        Branch br = branchDAO.getBranchByCode(syncTable.getKeyColumn());
        remoteDAO.updateBranch(br);
      } else if (syncTable.getOperationType().equals("INSERT") && syncTable.getTableName().equals("SALE_ITEMS")) {
        SaleItem saleItem = saleItemDAO.getSaleItemById(Long.valueOf(syncTable.getKeyColumn()));
        remoteDAO.addSaleItem(saleItem);
      } else if (syncTable.getOperationType().equals("INSERT") && syncTable.getTableName().equals("SALES")) {
        Sale sale = saleDAO.getSaleById(Long.valueOf(syncTable.getKeyColumn()));
        remoteDAO.addSale(sale);
      } else if (syncTable.getOperationType().equals("INSERT") && syncTable.getTableName().equals("VENDORS")) {
        Vendor vendor = vendorDAO.getVendorByCode(syncTable.getKeyColumn());
        remoteDAO.addVendor(vendor);
      } else if (syncTable.getOperationType().equals("UPDATE") && syncTable.getTableName().equals("VENDORS")) {
        Vendor vendor = vendorDAO.getVendorByCode(syncTable.getKeyColumn());
        remoteDAO.updateVendor(vendor);
      } else if (syncTable.getOperationType().equals("INSERT") && syncTable.getTableName().equals("PRODUCTS")) {
        Product product = productDAO.getProductByCode(syncTable.getKeyColumn());
        remoteDAO.addProduct(product);
      } else if (syncTable.getOperationType().equals("UPDATE") && syncTable.getTableName().equals("PRODUCTS")) {
        Product product = productDAO.getProductByCode(syncTable.getKeyColumn());
        remoteDAO.updateProduct(product);
      }

    }

    syncTableDAO.removeAllSyncTableEntries();

  }

  public static void main(String[] args) {

    CodesDAO codesDAO = CodesDAO.getInstance();
    SyncTableDAO syncTableDAO = SyncTableDAO.getInstance();
    DAO remoteDAO = DAO.getInstance();

    for (SyncTable syncTable : syncTableDAO.getAllSyncTableEntries()) {

      if (syncTable.getOperationType().equals("UPDATE") && syncTable.getTableName().equals("CODES")) {

        String code = codesDAO.getCodeByTableName(syncTable.getKeyColumn());
        System.out.println(code);
        Codes code1 = remoteDAO.test(syncTable.getKeyColumn(), code);
        System.out.println(code1);
      }
    }
  }

}