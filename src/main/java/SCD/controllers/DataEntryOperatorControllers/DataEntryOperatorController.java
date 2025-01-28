package SCD.controllers.DataEntryOperatorControllers;

import SCD.ui.DataEntryOperator.DataEntryOperatorDashboard;

import javax.swing.*;

public class DataEntryOperatorController {

    private DataEntryOperatorDashboard dashboard;

    public DataEntryOperatorController() {
        dashboard = new DataEntryOperatorDashboard();
        initialize();
    }

    private void initialize() {
        dashboard.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(DataEntryOperatorController::new);
    }
}
