package SCD.model.service.CashierService;

import java.util.List;

import SCD.model.crud.local.ProductDAO;
import SCD.model.crud.local.SaleDAO;
import SCD.model.crud.local.SaleItemDAO;
import SCD.model.crud.local.SyncTableDAO;
import SCD.model.models.Branch;
import SCD.model.models.Employee;
import SCD.model.models.Product;
import SCD.model.models.Sale;
import SCD.model.models.SaleItem;
import SCD.model.models.SyncTable;
import SCD.model.service.Json.AddResponseJSON;
import SCD.model.service.Json.SaleItemJSON;

public class CashierService {

  ProductDAO productDAO;
  SaleDAO saleDAO;
  SaleItemDAO saleItemDAO;
  SyncTableDAO syncTableDAO;

  public CashierService() {
    productDAO = ProductDAO.getInstance();
    saleDAO = SaleDAO.getInstance();
    saleItemDAO = SaleItemDAO.getInstance();
    syncTableDAO = SyncTableDAO.getInstance();
  }

  public double GenerateBill(List<SaleItem> saleItems) {

    double totalSaleAmount = 0.00;

    for (SaleItem sl : saleItems) {
      System.out.println(sl.getProduct().getSalePrice());
      System.out.println(sl.getProduct().getName());
      totalSaleAmount += sl.getTotalPrice();
    }

    return totalSaleAmount;

  }

  public SaleItem addSaleItem(List<SaleItem> items, int quantity, String productCode) {

    Product product = productDAO.getProductByCode(productCode);
    if (product == null) {
      return null;
    }

    for (SaleItem sl : items) {
      if (sl.getProduct().getProductCode().equals(productCode)) {

        if (sl.getQuantity() + quantity > sl.getProduct().getStockQuantity()) {
          return null;
        }

        sl.increment(quantity);
        return sl;
      }
    }

    if (quantity > product.getStockQuantity()) {
      return null;
    }

    SaleItem saleItem = new SaleItem(product, quantity, product.getSalePrice(), product.getOriginalPrice());
    items.add(saleItem);
    return saleItem;

  }

  public SaleItemJSON removeSaleItem(List<SaleItem> items, String productCode, int quantity) {
    Product product = productDAO.getProductByCode(productCode);
    if (product == null) {
      return null;
    }
    for (SaleItem sl : items) {
      if (sl.getProduct().getProductCode().equals(productCode)) {
        if (sl.getQuantity() - quantity < 0) {
          return new SaleItemJSON(null, false, "Quantity cannot be negative");
        }
        if (sl.getQuantity() - quantity == 0) {
          items.remove(sl);
          return null;
        } else {
          sl.decrement(quantity);
          return new SaleItemJSON(sl, true, "Item removed");
        }

      }
    }
    return null;
  }

  public AddResponseJSON proceedPayment(List<SaleItem> saleItems, Employee cashier, Branch branch,
      double totalAmount) {

    for (SaleItem sl : saleItems) {
      productDAO.decrementStockQuantity(sl.getProduct().getProductCode(), sl.getQuantity());
    }

    double profit;

    double actualCost = 0.0;

    for (SaleItem sl : saleItems) {
      actualCost += sl.getActualPrice() * sl.getQuantity();
    }

    profit = totalAmount - actualCost;

    Sale sale = saleDAO.addSale(cashier.getEmployeeCode(), branch.getBranchCode(), totalAmount, profit);

    if (sale == null) {
      return new AddResponseJSON("Error Creating Sale", false);
    }

    SyncTable s1 = new SyncTable("SALES", "INSERT", String.valueOf(sale.getSaleId()));
    syncTableDAO.addSyncTable(s1);

    for (SaleItem sl : saleItems) {
      sl.setSale(sale);
    }

    boolean result = saleItemDAO.addSaleItems(saleItems);
    if (result) {

      for (SaleItem sl : saleItems) {

        SyncTable s2 = new SyncTable("SALE_ITEMS", "INSERT", String.valueOf(sl.getSaleItemId()));
        syncTableDAO.addSyncTable(s2);

      }

      return new AddResponseJSON("Sale Created Successfully", true);
    } else {
      return new AddResponseJSON("Error Creating Sale", false);
    }

  }

  public Product getProduct(String code) {
    return productDAO.getProductByCode(code);
  }

}
