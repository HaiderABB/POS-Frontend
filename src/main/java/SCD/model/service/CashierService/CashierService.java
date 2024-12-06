package SCD.model.service.CashierService;

import java.util.List;

import SCD.model.crud.ProductDAO;
import SCD.model.crud.SaleDAO;
import SCD.model.models.Branch;
import SCD.model.models.Employee;
import SCD.model.models.Product;
import SCD.model.models.SaleItem;

public class CashierService {

  ProductDAO productDAO;
  SaleDAO saleDAO;

  public CashierService() {
    productDAO = ProductDAO.getInstance();
    saleDAO = SaleDAO.getInstance();
  }

  public double GenerateBill(List<SaleItem> saleItems) {

    double total_amount = 0.00;

    for (SaleItem sl : saleItems) {

      total_amount = (sl.getProduct().getSalePrice() * sl.getQuantity()) + total_amount;

    }

    return total_amount;

  }

  public void proceedPayment(List<SaleItem> saleItems, Employee cashier, Branch branch, double totalAmount) {

    for (SaleItem sl : saleItems) {
      productDAO.decrementStockQuantity(sl.getProduct().getProductCode(), sl.getQuantity());
    }

    saleDAO.addSale(cashier.getEmployeeCode(), branch.getBranchCode(), totalAmount);

  }

  public Product getProduct(String code) {
    return productDAO.getProductByCode(code);
  }

}
