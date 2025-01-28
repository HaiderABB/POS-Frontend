package SCD.ui.DataEntryOperator.Vendor;

import SCD.controllers.DataEntryOperatorControllers.DEOSidebarController;
import SCD.ui.Common.NavBar;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import SCD.ui.Common.Props;

public class ViewVendorsPage extends JFrame implements Props {

    private DefaultTableModel tableModel;
    private JTable vendorTable;

    public ViewVendorsPage() {
        setTitle("View Vendors");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // scd- proj initSidebar
        DEOSidebarController sidebarController = new DEOSidebarController(this);
        JPanel sidebar = sidebarController.getView();
        add(sidebar, BorderLayout.WEST);

        // scd- proj initContent Panel
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(contentPanel, BorderLayout.CENTER);

        // scd- proj initNavBar
        NavBar navBar = new NavBar();
        navBar.setTitle("View Vendors");
        contentPanel.add(navBar, BorderLayout.NORTH);

        // scd- proj initTable Panel
        JPanel tablePanel = new JPanel(new BorderLayout());
        String[] columnNames = { "Vendor Code", "Vendor Name", "Phone Number", "Address" };
        tableModel = new DefaultTableModel(columnNames, 0);
        vendorTable = new JTable(tableModel);
        vendorTable.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(vendorTable);
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        contentPanel.add(tablePanel, BorderLayout.CENTER);

        setLocationRelativeTo(null);
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public JTable getVendorTable() {
        return vendorTable;
    }

    public void populateTable(List<Object[]> vendorData) {
        tableModel.setRowCount(0);
        for (Object[] row : vendorData) {
            tableModel.addRow(row);
        }
    }
}
