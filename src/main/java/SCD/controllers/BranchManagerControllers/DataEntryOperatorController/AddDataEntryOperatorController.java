package SCD.controllers.BranchManagerControllers.DataEntryOperatorController;

import SCD.ui.BranchManager.ManageDataEntryOperator.AddDataEntryOperatorPage;

import javax.swing.*;
import java.util.regex.Pattern;

public class AddDataEntryOperatorController {
    private final AddDataEntryOperatorPage view;

    public AddDataEntryOperatorController(AddDataEntryOperatorPage view) {
        this.view = view;
        initController();
    }

    private void initController() {
        view.getAddButton().addActionListener(e -> handleAddOperator());
    }

    private void handleAddOperator() {
        String name = view.getNameField().getText().trim();
        String email = view.getEmailField().getText().trim();
        String branchCode = view.getBranchCodeField().getText().trim();

        if (!validateInputs(name, email, branchCode)) {
            return;
        }

        saveOperator(name, email, branchCode);
        view.clearFields();
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

    private void saveOperator(String name, String email, String branchCode) {
        String message = "Data Entry Operator successfully added:\n"
                + "Name: " + name + "\n"
                + "Email: " + email + "\n"
                + "Branch Code: " + branchCode;
        JOptionPane.showMessageDialog(view, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AddDataEntryOperatorPage page = new AddDataEntryOperatorPage();
            new AddDataEntryOperatorController(page);
            page.setVisible(true);
        });
    }
}
