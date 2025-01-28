package SCD.controllers.CashierControllers;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import SCD.controllers.cache.Cache;
import SCD.model.models.Employee;
import SCD.model.models.SaleItem;
import SCD.model.service.CashierService.CashierService;
import SCD.model.service.Common.CommonServices;
import SCD.model.service.Json.AddResponseJSON;
import SCD.ui.Cashier.PaymentsPage;

public class PaymentsController {

    PaymentsPage paymentsPage;
    CashierService cashierService = new CashierService();
    List<SaleItem> saleItems;
    double totalAmount;

    public PaymentsController(List<SaleItem> saleItems, double totalBill) {
        this.saleItems = saleItems;
        this.totalAmount = totalBill;
        paymentsPage = new PaymentsPage(saleItems, totalBill);
        initialize();
        paymentsPage.setVisible(true);
    }

    private void initialize() {
        paymentsPage.payButton.addActionListener(e -> handlePayButton());
    }

    void handlePayButton() {

        if (saleItems != null && saleItems.size() > 0) {

            Cache cache = new Cache();
            String cashier = cache.getCache();

            CommonServices commonServices = new CommonServices();
            Employee employee = commonServices.getEmployeeByEmployeeCode(cashier);

            AddResponseJSON json = cashierService.proceedPayment(saleItems, employee, employee.getBranch(),
                    totalAmount);

            if (json.isSuccess()) {
                JOptionPane.showMessageDialog(paymentsPage, "Payment successful!", "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                paymentsPage.dispose();
            } else {
                JOptionPane.showMessageDialog(paymentsPage, "Payment failed!", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } else {
            JOptionPane.showMessageDialog(paymentsPage, "No items in the cart!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void updateTotalBill(double newTotal) {
        paymentsPage.setTotalBill(newTotal);
    }

    public void showPage() {
        paymentsPage.setVisible(true);
    }

    public static void main(String[] args) {

        List<SaleItem> saleItems = new ArrayList<>();
        double totalBill = 123.45;

        PaymentsController paymentsController = new PaymentsController(saleItems, totalBill);
        paymentsController.showPage();
    }
}
