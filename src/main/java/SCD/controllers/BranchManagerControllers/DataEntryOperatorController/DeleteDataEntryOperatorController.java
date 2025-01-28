package SCD.controllers.BranchManagerControllers.DataEntryOperatorController;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import SCD.controllers.cache.Cache;
import SCD.model.models.Employee;
import SCD.model.service.Common.CommonServices;
import SCD.model.service.Json.AddResponseJSON;
import SCD.ui.BranchManager.ManageDataEntryOperator.DeleteDataEntryOperatorPage;

public class DeleteDataEntryOperatorController {
    private DeleteDataEntryOperatorPage view;
    CommonServices commonServices = new CommonServices();

    public DeleteDataEntryOperatorController() {

        view = new DeleteDataEntryOperatorPage();
        view.setVisible(true);
        initController();

    }

    public DeleteDataEntryOperatorController(DeleteDataEntryOperatorPage view) {
        this.view = view;
        initController();
    }

    private void initController() {
        view.getDeleteButton().addActionListener(e -> handleDeleteOperator());
    }

    private void handleDeleteOperator() {
        String operatorCode = view.getOperatorCodeField().getText().trim();

        if (!validateOperatorCode(operatorCode)) {
            return;
        }

        boolean res = commonServices.checkEmployeeExists(operatorCode);
        if (!res) {
            JOptionPane.showMessageDialog(view, "Data Entry Operator does not exist!", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        Cache cache = new Cache();
        String emp_code = cache.getCache();

        Employee manager = commonServices.getEmployeeByEmployeeCode(emp_code);
        Employee deo = commonServices.getEmployeeByEmployeeCode(operatorCode);

        if (manager.getBranch().getBranchCode().equals(deo.getBranch().getBranchCode())) {
            AddResponseJSON json = commonServices.RemoveEmployee(operatorCode);

            if (json.isSuccess()) {

                deleteOperator(operatorCode);
                view.getOperatorCodeField().setText("");
            } else {
                JOptionPane.showMessageDialog(view, "Failed to delete Data Entry Operator !", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
            return;
        } else {
            JOptionPane.showMessageDialog(view, "You cannot delete a Data Entry Operator  from another branch!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean validateOperatorCode(String operatorCode) {
        if (operatorCode.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Please enter a Data Entry Operator ID!", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!operatorCode.matches("DM-\\d{4}")) {
            JOptionPane.showMessageDialog(view, "Data Entry Operator ID must follow the format 'DM-XXXX'.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    private void deleteOperator(String operatorCode) {
        JOptionPane.showMessageDialog(view, "Data Entry Operator with ID " + operatorCode + " deleted successfully!",
                "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DeleteDataEntryOperatorPage page = new DeleteDataEntryOperatorPage();
            new DeleteDataEntryOperatorController(page);
            page.setVisible(true);
        });
    }
}
