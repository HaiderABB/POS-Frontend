package SCD.controllers.SuperAdminControllers;

import SCD.model.models.Employee;
import SCD.ui.SuperAdmin.SuperAdminDashboard;

public class SuperAdminDashboardController {

    private SuperAdminDashboard view;
    private Employee employee;

    public SuperAdminDashboardController() {

        view = new SuperAdminDashboard();
        view.setVisible(true);

    }

    public void showDashboard() {
        SuperAdminDashboard dashboard = new SuperAdminDashboard();
        dashboard.setVisible(true);
    }

    public static void main(String[] args) {

    }

    public void showPage() {
        new SuperAdminDashboard();
    }
}
