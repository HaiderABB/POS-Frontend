package SCD.controllers.SuperAdminControllers.ManagesBranchManagerController;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import SCD.model.service.Common.CommonServices;
import SCD.model.service.Json.AddResponseJSON;
import SCD.ui.SuperAdmin.ManageBranchManager.DeleteBranchManagerPage;

public class DeleteBranchManagerController {
    private DeleteBranchManagerPage view;
    CommonServices commonServices = new CommonServices();

    public DeleteBranchManagerController() {

        view = new DeleteBranchManagerPage();
        view.setVisible(true);
        this.view.getDeleteButton().addActionListener(e -> handleDeleteBranchManager());

    }

    public DeleteBranchManagerController(DeleteBranchManagerPage view) {
        this.view = view;

    }

    private void handleDeleteBranchManager() {
        String managerCode = view.getManagerCodeField().getText().trim();

        if (!validateManagerCode(managerCode)) {
            return;
        }

        boolean res = commonServices.checkEmployeeExists(managerCode);
        if (!res) {
            JOptionPane.showMessageDialog(view, "Branch Manager does not exist!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        AddResponseJSON json = commonServices.RemoveEmployee(managerCode);

        if (json.isSuccess()) {

            deleteBranchManager(managerCode);

            view.getManagerCodeField().setText("");
        } else {
            JOptionPane.showMessageDialog(view, "Failed to delete Branch Manager!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean validateManagerCode(String managerCode) {
        if (managerCode.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Please enter a Branch Manager Code!", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!managerCode.matches("BM-\\d{4}")) {
            JOptionPane.showMessageDialog(view, "Branch Manager Code must follow the format 'BM-XXXX'.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    void deleteBranchManager(String managerCode) {
        JOptionPane.showMessageDialog(view, "Branch Manager with ID " + managerCode + " deleted successfully!",
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
