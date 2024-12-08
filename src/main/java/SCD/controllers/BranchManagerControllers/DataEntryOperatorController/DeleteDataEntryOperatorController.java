package SCD.controllers.BranchManagerControllers.DataEntryOperatorController;

import SCD.ui.BranchManager.ManageDataEntryOperator.DeleteDataEntryOperatorPage;

import javax.swing.*;

public class DeleteDataEntryOperatorController {
    private  DeleteDataEntryOperatorPage view;

    public DeleteDataEntryOperatorController() {
        SwingUtilities.invokeLater(() -> {
            DeleteDataEntryOperatorPage page = new DeleteDataEntryOperatorPage();
            page.setVisible(true);
        });
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

        deleteOperator(operatorCode);
        view.clearFields();
    }

    public boolean validateOperatorCode(String operatorCode) {
        if (operatorCode.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Please enter a Data Entry Operator ID!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!operatorCode.matches("DM-\\d{4}")) {
            JOptionPane.showMessageDialog(view, "Data Entry Operator ID must follow the format 'DM-XXXX'.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    private void deleteOperator(String operatorCode) {
        JOptionPane.showMessageDialog(view, "Data Entry Operator with ID " + operatorCode + " deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DeleteDataEntryOperatorPage page = new DeleteDataEntryOperatorPage();
            new DeleteDataEntryOperatorController(page);
            page.setVisible(true);
        });
    }
}
