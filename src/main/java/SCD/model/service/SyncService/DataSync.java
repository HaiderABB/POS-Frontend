package SCD.model.service.SyncService;

import java.util.List;

import SCD.model.db.local.BranchesDAO;
import SCD.model.db.local.CodesDAO;
import SCD.model.db.local.EmployeeDAO;
import SCD.model.db.local.ProductDAO;
import SCD.model.db.local.SaleDAO;
import SCD.model.db.local.SaleItemDAO;
import SCD.model.db.local.SyncTableDAO;
import SCD.model.db.local.VendorDAO;
import SCD.model.db.remote.DAO;
import SCD.model.models.Branch;
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

      boolean res = false;
      if (syncTable.getOperationType().equals("INSERT") && syncTable.getTableName().equals("EMPLOYEES")) {
        Employee emp = employeeDAO.getEmployeeByEmployeeCode(syncTable.getKeyColumn());
        res = remoteDAO.addEmployee(emp);

      } else if (syncTable.getOperationType().equals("UPDATE") && syncTable.getTableName().equals("EMPLOYEES")) {
        Employee emp = employeeDAO.getEmployeeByEmployeeCode(syncTable.getKeyColumn());
        res = remoteDAO.updateEmployeeIfExists(emp);
      } else if (syncTable.getOperationType().equals("UPDATE") && syncTable.getTableName().equals("CODES")) {
        String code = codesDAO.getCodeByTableName(syncTable.getKeyColumn());
        res = remoteDAO.updateCodeByTableName(syncTable.getKeyColumn(), code);
      } else if (syncTable.getOperationType().equals("INSERT") && syncTable.getTableName().equals("BRANCHES")) {
        Branch br = branchDAO.getBranchByCode(syncTable.getKeyColumn());
        res = remoteDAO.addBranch(br);
      } else if (syncTable.getOperationType().equals("UPDATE") && syncTable.getTableName().equals("BRANCHES")) {
        Branch br = branchDAO.getBranchByCode(syncTable.getKeyColumn());
        res = remoteDAO.updateBranch(br);
      } else if (syncTable.getOperationType().equals("INSERT") && syncTable.getTableName().equals("VENDORS")) {
        Vendor vendor = vendorDAO.getVendorByCode(syncTable.getKeyColumn());
        res = remoteDAO.addVendor(vendor);
      } else if (syncTable.getOperationType().equals("UPDATE") && syncTable.getTableName().equals("VENDORS")) {
        Vendor vendor = vendorDAO.getVendorByCode(syncTable.getKeyColumn());
        res = remoteDAO.updateVendor(vendor);
      } else if (syncTable.getOperationType().equals("INSERT") && syncTable.getTableName().equals("PRODUCTS")) {
        Product product = productDAO.getProductByCode(syncTable.getKeyColumn());
        res = remoteDAO.addProduct(product);
      } else if (syncTable.getOperationType().equals("UPDATE") && syncTable.getTableName().equals("PRODUCTS")) {
        Product product = productDAO.getProductByCode(syncTable.getKeyColumn());
        res = remoteDAO.updateProduct(product);
      }
      if (res) {
        syncTableDAO.removeSyncTableById(syncTable.getId());
      }

    }

    List<SyncTable> syncTables2 = syncTableDAO.getAllSyncTableEntries();

    for (SyncTable syncTable : syncTables2) {

      if (syncTable.getOperationType().equals("INSERT") && syncTable.getTableName().equals("SALES")) {

        Sale sale = saleDAO.getSaleById(Integer.parseInt(syncTable.getKeyColumn()));

        int prevID = sale.getSaleId();

        sale.setSaleId(0);

        Sale newSale;

        newSale = remoteDAO.addSale(sale);
        if (newSale != null) {
          syncTableDAO.removeSyncTableById(syncTable.getId());
        }

        if (newSale != null) {
          for (SyncTable syncTable2 : syncTables2) {
            if (syncTable2.getTableName().equals("SALE_ITEMS")
                && syncTable2.getOperationType().equals("INSERT")) {

              SaleItem saleItem = saleItemDAO.getSaleItemById(Integer.parseInt(syncTable2.getKeyColumn()));
              if (saleItem.getSale().getSaleId() == prevID) {

                saleItem.setSaleItemId(0);
                boolean res = remoteDAO.addSaleItem(saleItem, newSale.getSaleId());
                if (res) {
                  syncTableDAO.removeSyncTableById(syncTable2.getId());
                }
              }

            }
          }
        }

      }

    }

  }

  public static void main(String[] args) {

    SyncTableDAO syncTableDAO = SyncTableDAO.getInstance();
    SaleDAO saleDAO = SaleDAO.getInstance();
    DAO remoteDAO = DAO.getInstance();
    SaleItemDAO saleItemDAO = SaleItemDAO.getInstance();

    List<SyncTable> syncTables2 = syncTableDAO.getAllSyncTableEntries();

    for (SyncTable syncTable : syncTables2) {

      if (syncTable.getOperationType().equals("INSERT") && syncTable.getTableName().equals("SALES")) {

        Sale sale = saleDAO.getSaleById(Integer.parseInt(syncTable.getKeyColumn()));

        int prevID = sale.getSaleId();

        sale.setSaleId(0);

        Sale newSale;

        newSale = remoteDAO.addSale(sale);

        if (newSale.getSaleId() != 0) {
          for (SyncTable syncTable2 : syncTables2) {
            if (syncTable2.getTableName().equals("SALE_ITEMS")
                && syncTable2.getOperationType().equals("INSERT")) {

              SaleItem saleItem = saleItemDAO.getSaleItemById(Integer.parseInt(syncTable2.getKeyColumn()));
              if (saleItem.getSale().getSaleId() == prevID) {

                saleItem.setSaleItemId(0);
                remoteDAO.addSaleItem(saleItem, newSale.getSaleId());
              }

            }
          }
        }

      }

    }

  }
}