package SCD.model.service.CashierService;

import java.util.List;

import SCD.model.crud.ProductDAO;
import SCD.model.crud.SaleDAO;
import SCD.model.crud.SaleItemDAO;
import SCD.model.models.Branch;
import SCD.model.models.Employee;
import SCD.model.models.Product;
import SCD.model.models.Sale;
import SCD.model.models.SaleItem;
import SCD.model.service.AddResponseClass;

public class CashierService {

  ProductDAO productDAO;
  SaleDAO saleDAO;
  SaleItemDAO saleItemDAO;

  public CashierService() {
    productDAO = ProductDAO.getInstance();
    saleDAO = SaleDAO.getInstance();
    saleItemDAO = SaleItemDAO.getInstance();
  }

  public double GenerateBill(List<SaleItem> saleItems) {

    double totalSaleAmount = 0.00;

    for (SaleItem sl : saleItems) {
      totalSaleAmount += sl.getTotalPrice();
    }

    return totalSaleAmount;

  }

  public AddResponseClass proceedPayment(List<SaleItem> saleItems, Employee cashier, Branch branch,
      double totalAmount) {

    for (SaleItem sl : saleItems) {
      productDAO.decrementStockQuantity(sl.getProduct().getProductCode(), sl.getQuantity());
    }

    Sale sale = saleDAO.addSale(cashier.getEmployeeCode(), branch.getBranchCode(), totalAmount);

    if (sale == null) {
      return new AddResponseClass("Error Creating Sale", false);
    }

    for (SaleItem sl : saleItems) {
      sl.setSale(sale);

    }

    boolean result = saleItemDAO.addSaleItems(saleItems);
    if (result) {
      return new AddResponseClass("Sale Created Successfully", true);
    } else {
      return new AddResponseClass("Error Creating Sale", false);
    }

  }

  public Product getProduct(String code) {
    return productDAO.getProductByCode(code);
  }

}
