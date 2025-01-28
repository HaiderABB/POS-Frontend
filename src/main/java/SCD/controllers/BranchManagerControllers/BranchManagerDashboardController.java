package SCD.controllers.BranchManagerControllers;

import javax.swing.SwingUtilities;

import SCD.ui.BranchManager.BranchManagerDashboard;

public class BranchManagerDashboardController {

    private BranchManagerDashboard view;

    public BranchManagerDashboardController() {

        view = new BranchManagerDashboard();
        view.setVisible(true);

    }

    public BranchManagerDashboardController(BranchManagerDashboard view) {
        this.view = view;

    }

    public void start() {
        BranchManagerDashboard dashboard = new BranchManagerDashboard();
        dashboard.setVisible(true);
    }

    public void showDashboard() {
        BranchManagerDashboard dashboard = new BranchManagerDashboard();
        dashboard.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BranchManagerDashboard dashboard = new BranchManagerDashboard();
            BranchManagerDashboardController controller = new BranchManagerDashboardController(dashboard);
            controller.start();
        });
    }
}
