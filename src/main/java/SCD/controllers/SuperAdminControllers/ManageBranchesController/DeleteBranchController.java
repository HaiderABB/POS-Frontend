package SCD.controllers.SuperAdminControllers.ManageBranchesController;

import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import SCD.model.models.Branch;
import SCD.model.models.Employee;
import SCD.model.service.Json.AddResponseJSON;
import SCD.model.service.SuperAdminService.SuperAdminService;
import SCD.ui.SuperAdmin.ManageBranches.DeleteBranchPage;

public class DeleteBranchController {

    private DeleteBranchPage deleteBranchPage;
    Employee employee;
    SuperAdminService superAdminService = new SuperAdminService();

    public DeleteBranchController(Employee employee) {
        this.employee = employee;
        SwingUtilities.invokeLater(() -> {
            DeleteBranchPage page = new DeleteBranchPage(employee);
            new DeleteBranchController(page, employee);
            page.setVisible(true);
        });
    }

    public DeleteBranchController(DeleteBranchPage deleteBranchPage, Employee employee) {
        this.employee = employee;
        this.deleteBranchPage = deleteBranchPage;

        deleteBranchPage.getDeleteButton().addActionListener(e -> {
            String branchCode = deleteBranchPage.getBranchCode();

            if (validateBranchCode(branchCode)) {
                Branch br = superAdminService.getBranchByCode(branchCode);
                if (br == null) {
                    JOptionPane.showMessageDialog(deleteBranchPage, "Branch not found!", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                AddResponseJSON json = superAdminService.deleteBranch(branchCode);
                if (json.isSuccess()) {
                    JOptionPane.showMessageDialog(deleteBranchPage, "Branch deleted successfully!", "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(deleteBranchPage, "Failed to delete branch!", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }

                deleteBranchPage.clearBranchCodeField();
            }
        });
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

    private void deleteBranch(String branchCode) {
        JOptionPane.showMessageDialog(deleteBranchPage, "Branch with Code " + branchCode + " deleted successfully!",
                "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DeleteBranchPage page = new DeleteBranchPage(null);
            new DeleteBranchController(page, null);
            page.setVisible(true);
        });
    }
}
