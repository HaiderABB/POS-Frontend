package SCD.ui.BranchManager.ManageDataEntryOperator;

import SCD.ui.Common.NavBar;
import SCD.ui.BranchManager.BranchSidebar;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ViewDataEntryOperatorsPage extends JFrame {

    private final BranchSidebar sidebar;
    private final NavBar navBar;
    private final DefaultTableModel tableModel;
    private final JTable operatorTable;

    public ViewDataEntryOperatorsPage() {
        setTitle("View Data Entry Operators");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        sidebar = new BranchSidebar();
        add(sidebar, BorderLayout.WEST);

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(contentPanel, BorderLayout.CENTER);

        navBar = new NavBar();
        navBar.setTitle("View Data Entry Operators");
        contentPanel.add(navBar, BorderLayout.NORTH);

        JPanel tablePanel = new JPanel(new BorderLayout());
        String[] columnNames = {"Operator ID", "Name", "Email", "Branch Code"};
        tableModel = new DefaultTableModel(columnNames, 0);
        operatorTable = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(operatorTable);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        contentPanel.add(tablePanel, BorderLayout.CENTER);

        setLocationRelativeTo(null);
    }

    public JTable getOperatorTable() {
        return operatorTable;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }
}
