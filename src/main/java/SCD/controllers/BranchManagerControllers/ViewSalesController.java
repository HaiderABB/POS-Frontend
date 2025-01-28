package SCD.controllers.BranchManagerControllers;

import java.time.LocalDate;
import java.util.List;

import javax.swing.JOptionPane;

import SCD.controllers.cache.Cache;
import SCD.model.models.Employee;
import SCD.model.models.Sale;
import SCD.model.service.BranchManagerService.BranchManagerService;
import SCD.model.service.Common.CommonServices;
import SCD.model.service.Json.GetResponseJSON;
import SCD.ui.BranchManager.ViewSales;

public class ViewSalesController {

    private ViewSales view;
    BranchManagerService branchManagerService = new BranchManagerService();
    CommonServices commonServices = new CommonServices();

    public ViewSalesController() {
        Cache c = new Cache();
        String emp_code = c.getCache();
        if (emp_code == null) {
            JOptionPane.showMessageDialog(null, "Please login first.");
            return;
        }

        Employee emp = commonServices.getEmployeeByEmployeeCode(emp_code);

        view = new ViewSales();

        GetResponseJSON<Sale> json = branchManagerService.getSalesByBranch(emp.getBranch().getBranchCode());

        if (json.getData() == null) {
            view.setVisible(true);

            JOptionPane.showMessageDialog(view, "No sales data found for the selected branch.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            view.addRow(new Object[] { "-", "-", "-", "-" });
            return;
        }
        System.out.println(json.getMessage());

        initController(json.getData());
        view.setVisible(true);
        JOptionPane.showMessageDialog(view, "Sales data refreshed!");

    }

    private void initController(List<Sale> sales) {
        loadSalesData(sales);
    }

    private void loadSalesData(List<Sale> sales) {

        view.clearTable();
        LocalDate today = LocalDate.now();

        for (Sale sale : sales) {
            view.addRow(new Object[] {
                    sale.getCreatedAt().toLocalDate(),
                    sale.getSaleId(),
                    sale.getTotalAmount(),
                    sale.getProfit()
            });
        }

    }

    public static void main(String[] args) {
        // scd- proj initnew ViewSalesController();
    }
}
