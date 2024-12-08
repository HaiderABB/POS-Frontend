package SCD.controllers.SuperAdminControllers.ManageBranchesController;

import SCD.ui.SuperAdmin.ManageBranches.DeleteBranchPage;

import javax.swing.*;
import java.util.regex.Pattern;

public class DeleteBranchController {

    private DeleteBranchPage deleteBranchPage;

    public DeleteBranchController(DeleteBranchPage deleteBranchPage) {
        this.deleteBranchPage = deleteBranchPage;

        deleteBranchPage.getDeleteButton().addActionListener(e -> {
            String branchCode = deleteBranchPage.getBranchCode();

            if (validateBranchCode(branchCode)) {
                deleteBranch(branchCode);
                deleteBranchPage.clearBranchCodeField();
            }
        });
    }

    public boolean validateBranchCode(String branchCode) {
        if (branchCode.isEmpty()) {
            JOptionPane.showMessageDialog(deleteBranchPage, "Please enter a Branch Code!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!Pattern.matches("BR-\\d{4}", branchCode)) {
            JOptionPane.showMessageDialog(deleteBranchPage, "Branch Code must follow the format 'BR-XXXX'.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    private void deleteBranch(String branchCode) {
        JOptionPane.showMessageDialog(deleteBranchPage, "Branch with Code " + branchCode + " deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DeleteBranchPage page = new DeleteBranchPage();
            new DeleteBranchController(page);
            page.setVisible(true);
        });
    }
}
