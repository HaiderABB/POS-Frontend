package SCD.controllers.SuperAdminControllers;

import SCD.model.models.Employee;
import SCD.ui.SuperAdmin.SuperAdminDashboard;

import javax.swing.*;

public class SuperAdminDashboardController {

    private final SuperAdminDashboard view;
    private Employee employee;

    public SuperAdminDashboardController(SuperAdminDashboard view, Employee employee) {
        this.view = view;
        this.employee = employee;

    }

    public void showDashboard() {
        SuperAdminDashboard dashboard = new SuperAdminDashboard();
        dashboard.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SuperAdminDashboard view = new SuperAdminDashboard();
            new SuperAdminDashboardController(view, null);
            view.setVisible(true);
        });
    }
}
