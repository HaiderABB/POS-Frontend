package SCD.controllers.BranchManagerControllers.CashierController;

import SCD.ui.BranchManager.ManageCashier.DeleteCashierPage;

import javax.swing.*;

public class DeleteCashierController {
    private  DeleteCashierPage view;

    public DeleteCashierController() {
        SwingUtilities.invokeLater(() -> {
            DeleteCashierPage page = new DeleteCashierPage();
            page.setVisible(true);
        });
    }

    public DeleteCashierController(DeleteCashierPage view) {
        this.view = view;
        initController();
    }

    private void initController() {
        view.getDeleteButton().addActionListener(e -> handleDeleteCashier());
    }

    private void handleDeleteCashier() {
        String cashierCode = view.getCashierCodeField().getText().trim();

        if (!validateCashierCode(cashierCode)) {
            return;
        }

        deleteCashier(cashierCode);
        view.getCashierCodeField().setText("");
    }

    public boolean validateCashierCode(String cashierCode) {
        if (cashierCode.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Please enter a Cashier ID!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!cashierCode.matches("CM-\\d{4}")) {
            JOptionPane.showMessageDialog(view, "Cashier ID must follow the format 'CM-XXXX'.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    private void deleteCashier(String cashierCode) {
        JOptionPane.showMessageDialog(view, "Cashier with ID " + cashierCode + " deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DeleteCashierPage page = new DeleteCashierPage();
            new DeleteCashierController(page);
            page.setVisible(true);
        });
    }
}
