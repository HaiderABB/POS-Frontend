package SCD.model.service.CashierService;

import java.util.ArrayList;
import java.util.List;

import SCD.model.db.local.ProductDAO;
import SCD.model.db.local.SaleDAO;
import SCD.model.db.local.SaleItemDAO;
import SCD.model.db.local.SyncTableDAO;
import SCD.model.models.Branch;
import SCD.model.models.Employee;
import SCD.model.models.Product;
import SCD.model.models.Sale;
import SCD.model.models.SaleItem;
import SCD.model.models.SyncTable;
import SCD.model.models.Vendor;
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

  public SaleItem addSaleItem(List<SaleItem> items, int quantity, Product product) {

    if (product == null) {
      return null;
    }

    for (SaleItem sl : items) {
      if (sl.getProduct().getProductCode().equals(product.getProductCode())) {

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

    for (SaleItem sl : items) {
      if (sl.getProduct().getProductCode().equals(productCode)) {
        if (sl.getQuantity() - quantity < 0) {
          return new SaleItemJSON(sl, false, "Quantity cannot be negative");
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
      syncTableDAO.addSyncTable(new SyncTable("PRODUCTS", "UPDATE", sl.getProduct().getProductCode()));
    }

    double profit;

    double actualCost = 0.0;

    for (SaleItem sl : saleItems) {
      actualCost += sl.getActualPrice() * sl.getQuantity();
    }

    profit = totalAmount - actualCost;

    Sale sale = saleDAO.addSale(cashier.getEmployeeCode(), branch.getBranchCode(), totalAmount, profit);

    if (sale == null) {
      return new AddResponseJSON("Error Creating Sale", false, null);
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

      return new AddResponseJSON("Sale Created Successfully", true, Integer.toString(sale.getSaleId()));
    } else {
      return new AddResponseJSON("Error Creating Sale", false, null);
    }

  }

  public List<Product> getProducts() {
    return productDAO.getAllActiveProducts();
  }

  public Product getProduct(String code) {
    return productDAO.getProductByCode(code);
  }

  public static void main(String[] args) {

    CashierService cs = new CashierService();
    Vendor vendor = new Vendor("ABC Electronics", "03121234567", "123 Main Street");
    Vendor vendor2 = new Vendor("XYZ Electronics", "03121234789", "456 Elm Street");

    vendor.setVendorCode("VM-0001");
    vendor2.setVendorCode("VM-0002");

    Product product1 = new Product(vendor, "Product One", "Category A", 100.0, 90.0, 10.0, 90.0,
        50);
    Product product2 = new Product(vendor, "Product Two", "Category A", 120.0, 110.0, 12.0, 108.0,
        40);
    Product product3 = new Product(vendor, "Product Three", "Category B", 200.0, 180.0, 20.0,
        180.0, 30);
    Product product4 = new Product(vendor, "Product Four", "Category B", 80.0, 70.0, 8.0, 72.0,
        25);
    Product product5 = new Product(vendor, "Product Five", "Category C", 150.0, 140.0, 15.0, 135.0,
        60);

    Product product6 = new Product(vendor2, "Product Six", "Category A", 100.0, 95.0, 9.5, 90.0,
        80);
    Product product7 = new Product(vendor2, "Product Seven", "Category A", 110.0, 100.0, 11.0, 99.0,
        70);
    Product product8 = new Product(vendor2, "Product Eight", "Category B", 90.0, 85.0, 8.5, 88.0,
        45);
    Product product9 = new Product(vendor2, "Product Nine", "Category B", 140.0, 130.0, 13.0, 126.0,
        35);
    Product product10 = new Product(vendor2, "Product Ten", "Category C", 160.0, 150.0, 16.0, 144.0,
        55);

    product1.setProductCode("PM-0001");
    product2.setProductCode("PM-0002");
    product3.setProductCode("PM-0003");
    product4.setProductCode("PM-0004");
    product5.setProductCode("PM-0005");
    product6.setProductCode("PM-0006");
    product7.setProductCode("PM-0007");
    product8.setProductCode("PM-0008");
    product9.setProductCode("PM-0009");
    product10.setProductCode("PM-0010");

    SaleItem saleItem1 = new SaleItem(product1, 2, product1.getSalePrice(), product1.getOriginalPrice());
    SaleItem saleItem2 = new SaleItem(product2, 3, product2.getSalePrice(), product2.getOriginalPrice());
    SaleItem saleItem3 = new SaleItem(product3, 1, product3.getSalePrice(), product3.getOriginalPrice());
    SaleItem saleItem4 = new SaleItem(product4, 5, product4.getSalePrice(), product4.getOriginalPrice());
    SaleItem saleItem5 = new SaleItem(product5, 4, product5.getSalePrice(), product5.getOriginalPrice());

    List<SaleItem> saleItems = new ArrayList<>();
    saleItems.add(saleItem1);
    saleItems.add(saleItem2);
    saleItems.add(saleItem3);
    saleItems.add(saleItem4);
    saleItems.add(saleItem5);

    double totalBill = cs.GenerateBill(saleItems);
    System.out.println("Total Bill: " + totalBill);

    double profit;

    double actualCost = 0.0;

    for (SaleItem sl : saleItems) {
      actualCost += sl.getActualPrice() * sl.getQuantity();
    }

    profit = totalBill - actualCost;
    System.out.println("Actual Cost: " + actualCost);
    System.out.println("Profit: " + profit);

  }

}
