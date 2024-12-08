package SCD.controllers.SuperAdminControllers;

import javax.swing.SwingUtilities;

import SCD.model.models.Employee;
import SCD.ui.SuperAdmin.SuperAdminDashboard;

public class SuperAdminDashboardController {

    private SuperAdminDashboard view;
    private Employee employee;

    public SuperAdminDashboardController(SuperAdminDashboard view, Employee employee) {
        this.view = view;
        this.employee = employee;
    }

    public SuperAdminDashboardController(Employee employee) {
        this.employee = employee;
        SwingUtilities.invokeLater(() -> {
            SuperAdminDashboard view = new SuperAdminDashboard(employee);
            view.setVisible(true);
        });
    }

    public void showDashboard() {
        SuperAdminDashboard dashboard = new SuperAdminDashboard(employee);
        dashboard.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SuperAdminDashboard view = new SuperAdminDashboard(null);
            new SuperAdminDashboardController(view, null);
            view.setVisible(true);
        });
    }

    public void showPage() {
        new SuperAdminDashboard(employee);
    }
}
