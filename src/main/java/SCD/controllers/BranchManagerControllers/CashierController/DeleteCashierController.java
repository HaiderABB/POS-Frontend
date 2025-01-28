package SCD.controllers.BranchManagerControllers.CashierController;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import SCD.controllers.cache.Cache;
import SCD.model.models.Employee;
import SCD.model.service.Common.CommonServices;
import SCD.model.service.Json.AddResponseJSON;
import SCD.ui.BranchManager.ManageCashier.DeleteCashierPage;

public class DeleteCashierController {
    DeleteCashierPage view;
    CommonServices commonServices = new CommonServices();

    public DeleteCashierController() {

        view = new DeleteCashierPage();
        view.setVisible(true);
        initController();

    }

    public DeleteCashierController(DeleteCashierPage view) {
        this.view = view;
        initController();
    }

    private void initController() {
        view.getDeleteButton().addActionListener(e -> handleDeleteCashier());
    }

    void handleDeleteCashier() {
        String cashierCode = view.getCashierCodeField().getText().trim();

        if (!validateCashierCode(cashierCode)) {
            return;
        }

        boolean res = commonServices.checkEmployeeExists(cashierCode);
        if (!res) {
            JOptionPane.showMessageDialog(view, "Cashier does not exist!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Cache cache = new Cache();
        String emp_code = cache.getCache();

        Employee manager = commonServices.getEmployeeByEmployeeCode(emp_code);
        Employee cashier = commonServices.getEmployeeByEmployeeCode(cashierCode);

        if (manager.getBranch().getBranchCode().equals(cashier.getBranch().getBranchCode())) {
            AddResponseJSON json = commonServices.RemoveEmployee(cashierCode);

            if (json.isSuccess()) {

                deleteCashier(cashierCode);

                view.getCashierCodeField().setText("");
            } else {
                JOptionPane.showMessageDialog(view, "Failed to delete Cashier!", "Error", JOptionPane.ERROR_MESSAGE);
            }
            return;
        } else {
            JOptionPane.showMessageDialog(view, "You cannot delete a cashier from another branch!", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

    }

    public boolean validateCashierCode(String cashierCode) {
        if (cashierCode.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Please enter a Cashier ID!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!cashierCode.matches("CM-\\d{4}")) {
            JOptionPane.showMessageDialog(view, "Cashier ID must follow the format 'CM-XXXX'.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    void deleteCashier(String cashierCode) {
        JOptionPane.showMessageDialog(view, "Cashier with ID " + cashierCode + " deleted successfully!", "Success",
                JOptionPane.INFORMATION_MESSAGE);
    }


}
