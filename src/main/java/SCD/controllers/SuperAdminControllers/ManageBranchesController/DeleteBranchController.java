package SCD.controllers.SuperAdminControllers.ManageBranchesController;

import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import SCD.model.models.Branch;
import SCD.model.service.Json.AddResponseJSON;
import SCD.model.service.SuperAdminService.SuperAdminService;
import SCD.ui.SuperAdmin.ManageBranches.DeleteBranchPage;

public class DeleteBranchController {

    private DeleteBranchPage deleteBranchPage;
    SuperAdminService superAdminService = new SuperAdminService();

    public DeleteBranchController() {

        deleteBranchPage = new DeleteBranchPage();
        deleteBranchPage.setVisible(true);

        deleteBranchPage.getDeleteButton().addActionListener(e -> {
            String branchCode = deleteBranchPage.getBranchCode();

            if (validateBranchCode(branchCode)) {

                if (branchCode.equals("BR-0001")) {

                    JOptionPane.showMessageDialog(deleteBranchPage, "Cannot Delete the default branch!", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;

                }

                Branch br = superAdminService.getBranchByCode(branchCode);
                if (br == null) {
                    JOptionPane.showMessageDialog(deleteBranchPage, "Branch not found!", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                AddResponseJSON json = superAdminService.deleteBranch(branchCode);
                if (json.isSuccess()) {

                    deleteBranch(branchCode);

                } else {
                    JOptionPane.showMessageDialog(deleteBranchPage, "Failed to delete branch!", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }

                deleteBranchPage.clearBranchCodeField();
            }
        });

    }

    public DeleteBranchController(DeleteBranchPage deleteBranchPage) {
        this.deleteBranchPage = deleteBranchPage;

    }

    public boolean validateBranchCode(String branchCode) {
        if (branchCode.isEmpty()) {
            JOptionPane.showMessageDialog(deleteBranchPage, "Please enter a Branch Code!", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!Pattern.matches("BR-\\d{4}", branchCode)) {
            JOptionPane.showMessageDialog(deleteBranchPage, "Branch Code must follow the format 'BR-XXXX'.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    void deleteBranch(String branchCode) {
        JOptionPane.showMessageDialog(deleteBranchPage, "Branch with Code " + branchCode + " deleted successfully!",
                "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DeleteBranchPage page = new DeleteBranchPage();
            new DeleteBranchController(page);
            page.setVisible(true);
        });
    }
}
