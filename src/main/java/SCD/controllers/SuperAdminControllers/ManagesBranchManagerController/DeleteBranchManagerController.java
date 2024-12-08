package SCD.controllers.SuperAdminControllers.ManagesBranchManagerController;

import SCD.ui.SuperAdmin.ManageBranchManager.DeleteBranchManagerPage;

import javax.swing.*;

public class DeleteBranchManagerController {
    private DeleteBranchManagerPage view;

    public DeleteBranchManagerController() {
        SwingUtilities.invokeLater(() -> {
            DeleteBranchManagerPage page = new DeleteBranchManagerPage();

            page.setVisible(true);
        });
    }


    public DeleteBranchManagerController(DeleteBranchManagerPage view) {
        this.view = view;
        this.view.getDeleteButton().addActionListener(e -> handleDeleteBranchManager());
    }

    private void handleDeleteBranchManager() {
        String managerCode = view.getManagerCodeField().getText().trim();

        if (!validateManagerCode(managerCode)) {
            return;
        }

        deleteBranchManager(managerCode);
        view.getManagerCodeField().setText("");
    }

    public boolean validateManagerCode(String managerCode) {
        if (managerCode.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Please enter a Branch Manager Code!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!managerCode.matches("BM-\\d{4}")) {
            JOptionPane.showMessageDialog(view, "Branch Manager Code must follow the format 'BM-XXXX'.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    private void deleteBranchManager(String managerCode) {
        JOptionPane.showMessageDialog(view,
                "Branch Manager with Code " + managerCode + " deleted successfully!",
                "Success",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DeleteBranchManagerPage page = new DeleteBranchManagerPage();
            new DeleteBranchManagerController(page);
            page.setVisible(true);
        });
    }

    public void showPage() {
        new DeleteBranchManagerPage();
    }
}
