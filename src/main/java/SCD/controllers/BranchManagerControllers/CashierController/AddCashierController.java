package SCD.controllers.BranchManagerControllers.CashierController;

import SCD.ui.BranchManager.ManageCashier.AddCashierPage;

import javax.swing.*;
import java.util.regex.Pattern;

public class AddCashierController {
    private AddCashierPage view;

    public AddCashierController() {
        SwingUtilities.invokeLater(() -> {
            AddCashierPage page = new AddCashierPage();
            page.setVisible(true);
        });
    }

    public AddCashierController(AddCashierPage view) {
        this.view = view;
        initController();
    }

    private void initController() {
        view.getAddButton().addActionListener(e -> handleAddCashier());
    }

    private void handleAddCashier() {
        String name = view.getNameField().getText().trim();
        String email = view.getEmailField().getText().trim();
        String branchCode = view.getBranchCodeField().getText().trim();

        if (!validateInputs(name, email, branchCode)) {
            return;
        }

        saveCashier(name, email, branchCode);
        clearFields();
    }

    public boolean validateInputs(String name, String email, String branchCode) {
        if (name.isEmpty() || email.isEmpty() || branchCode.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Please fill out all fields!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!Pattern.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}", email)) {
            JOptionPane.showMessageDialog(view, "Invalid email format!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!Pattern.matches("BR-\\d{4}", branchCode)) {
            JOptionPane.showMessageDialog(view, "Branch Code must follow the format 'BR-XXXX'!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    private void saveCashier(String name, String email, String branchCode) {
        String message = "Cashier successfully added:\n"
                + "Name: " + name + "\n"
                + "Email: " + email + "\n"
                + "Branch Code: " + branchCode;
        JOptionPane.showMessageDialog(view, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private void clearFields() {
        view.getNameField().setText("");
        view.getEmailField().setText("");
        view.getBranchCodeField().setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AddCashierPage page = new AddCashierPage();
            new AddCashierController(page);
            page.setVisible(true);
        });
    }
}
