package SCD.controllers.BranchManagerControllers.DataEntryOperatorController;

import SCD.ui.BranchManager.ManageDataEntryOperator.ViewDataEntryOperatorsPage;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ViewDataEntryOperatorsController {

    private  ViewDataEntryOperatorsPage view;

    public ViewDataEntryOperatorsController() {
        SwingUtilities.invokeLater(() -> {
            ViewDataEntryOperatorsPage page = new ViewDataEntryOperatorsPage();
            page.setVisible(true);
        });
    }

    public ViewDataEntryOperatorsController(ViewDataEntryOperatorsPage view) {
        this.view = view;

    }

    private void loadData() {
        DefaultTableModel model = view.getTableModel();

        model.setRowCount(0);
        model.addRow(new Object[]{"DM-1234", "John Doe", "john.doe@example.com", "BR-1234"});
        model.addRow(new Object[]{"DM-5678", "Jane Smith", "jane.smith@example.com", "BR-5678"});
    }

    public static void main(String[] args) {
        ViewDataEntryOperatorsPage page = new ViewDataEntryOperatorsPage();
        new ViewDataEntryOperatorsController(page);
        page.setVisible(true);
    }
}
