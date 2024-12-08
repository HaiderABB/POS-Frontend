package SCD.controllers.SuperAdminControllers.ManagesBranchManagerController;

import SCD.ui.SuperAdmin.ManageBranchManager.AddBranchManagerPage;

import javax.swing.*;
import java.util.regex.Pattern;

public class AddBranchManagerController {
    private AddBranchManagerPage view;
    public AddBranchManagerController() {
        SwingUtilities.invokeLater(() -> {
            AddBranchManagerPage page = new AddBranchManagerPage();
            page.setVisible(true);
        });

    }

    public AddBranchManagerController(AddBranchManagerPage view) {
        this.view = view;
        this.view.getAddButton().addActionListener(e -> handleAddBranchManager());
    }

    private void handleAddBranchManager() {
        String name = view.getNameField().getText().trim();
        String email = view.getEmailField().getText().trim();
        String branchCode = view.getBranchCodeField().getText().trim();

        if (!validateInputs(name, email, branchCode)) {
            return;
        }

        saveBranchManager(name, email, branchCode);
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

    private void saveBranchManager(String name, String email, String branchCode) {
        JOptionPane.showMessageDialog(view,
                "Branch Manager successfully added:\n" +
                        "Name: " + name + "\n" +
                        "Email: " + email + "\n" +
                        "Branch Code: " + branchCode,
                "Success",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void clearFields() {
        view.getNameField().setText("");
        view.getEmailField().setText("");
        view.getBranchCodeField().setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AddBranchManagerPage page = new AddBranchManagerPage();
            new AddBranchManagerController(page);
            page.setVisible(true);
        });
    }

    public void showPage() {
        new AddBranchManagerPage().setVisible(true);
    }
}
