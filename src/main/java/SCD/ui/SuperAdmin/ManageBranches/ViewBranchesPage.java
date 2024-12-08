package SCD.ui.SuperAdmin.ManageBranches;

import SCD.ui.Common.NavBar;
import SCD.ui.SuperAdmin.Sidebar;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ViewBranchesPage extends JFrame {
    private Sidebar sidebar;
    private NavBar navBar;
    private DefaultTableModel tableModel;
    private JTable branchTable;

    public ViewBranchesPage() {
        setTitle("View Branches");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());


        sidebar = new Sidebar();
        add(sidebar, BorderLayout.WEST);

        // Content Panel
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(contentPanel, BorderLayout.CENTER);

        // NavBar
        navBar = new NavBar();
        navBar.setTitle("View Branches");
        contentPanel.add(navBar, BorderLayout.NORTH);

        // Table Panel
        JPanel tablePanel = new JPanel(new BorderLayout());
        String[] columnNames = {"Branch ID", "Branch Name", "City", "Phone", "Address", "Active"};
        tableModel = new DefaultTableModel(columnNames, 0);
        branchTable = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(branchTable);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        contentPanel.add(tablePanel, BorderLayout.CENTER);

        setLocationRelativeTo(null);
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public void addRow(Object[] rowData) {
        tableModel.addRow(rowData);
    }
}
