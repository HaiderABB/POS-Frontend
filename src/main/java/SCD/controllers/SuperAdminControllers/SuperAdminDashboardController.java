package SCD.controllers.SuperAdminControllers;

import SCD.ui.SuperAdmin.SuperAdminDashboard;

import javax.swing.*;

public class SuperAdminDashboardController {

    private  SuperAdminDashboard view;

    public SuperAdminDashboardController(SuperAdminDashboard view) {
        this.view = view;

    }
    public SuperAdminDashboardController() {
        SwingUtilities.invokeLater(() -> {
            SuperAdminDashboard view = new SuperAdminDashboard();
            view.setVisible(true);
        });
    }
    public void showDashboard() {
        SuperAdminDashboard dashboard = new SuperAdminDashboard();
        dashboard.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SuperAdminDashboard view = new SuperAdminDashboard();
            new SuperAdminDashboardController(view);
            view.setVisible(true);
        });
    }

    public void showPage() {
        new SuperAdminDashboard();
    }
}
