package SCD.ui.BranchManager.ManageCashier;

import SCD.ui.Common.NavBar;
import SCD.ui.BranchManager.BranchSidebar;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ViewCashiersPage extends JFrame {

    private BranchSidebar sidebar;
    private NavBar navBar;
    private DefaultTableModel tableModel;
    private JTable cashierTable;

    public ViewCashiersPage() {
        setTitle("View Cashiers");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        sidebar = new BranchSidebar();
        add(sidebar, BorderLayout.WEST);

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(contentPanel, BorderLayout.CENTER);

        navBar = new NavBar();
        navBar.setTitle("View Cashiers");
        contentPanel.add(navBar, BorderLayout.NORTH);

        JPanel tablePanel = new JPanel(new BorderLayout());
        String[] columnNames = {"Cashier ID", "Name", "Email", "Branch Code"};
        tableModel = new DefaultTableModel(columnNames, 0);
        cashierTable = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(cashierTable);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        contentPanel.add(tablePanel, BorderLayout.CENTER);

        setLocationRelativeTo(null);

        loadSampleData();
    }

    public void loadSampleData() {
        tableModel.addRow(new Object[]{"CM-1234", "John Doe", "john.doe@example.com", "BR-1234"});
        tableModel.addRow(new Object[]{"CM-5678", "Jane Smith", "jane.smith@example.com", "BR-5678"});
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public JTable getCashierTable() {
        return cashierTable;
    }


}
