package SCD.controllers.SuperAdminControllers.ManageBranchesController;

import SCD.ui.SuperAdmin.ManageBranches.ViewBranchesPage;

import javax.swing.*;

public class ViewBranchesController {

    private ViewBranchesPage viewBranchesPage;
    public ViewBranchesController() {
        SwingUtilities.invokeLater(() -> {
        ViewBranchesPage page = new ViewBranchesPage();
        page.setVisible(true);
    });

    }



    public ViewBranchesController(ViewBranchesPage viewBranchesPage) {
        this.viewBranchesPage = viewBranchesPage;

    }



    public static void main(String[] args) {

            ViewBranchesPage page = new ViewBranchesPage();
            new ViewBranchesController(page);
            page.setVisible(true);

    }
}
