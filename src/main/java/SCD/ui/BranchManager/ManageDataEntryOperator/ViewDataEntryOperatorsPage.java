package SCD.ui.BranchManager.ManageDataEntryOperator;

import SCD.ui.Common.NavBar;
import SCD.ui.BranchManager.BranchSidebar;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ViewDataEntryOperatorsPage extends JFrame {

    private BranchSidebar sidebar;
    private NavBar navBar;
    private DefaultTableModel tableModel;

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
        JTable operatorTable = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(operatorTable);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        contentPanel.add(tablePanel, BorderLayout.CENTER);

        setLocationRelativeTo(null);

        loadSampleData();
    }

    private void loadSampleData() {
        tableModel.addRow(new Object[]{"DM-1234", "John Doe", "john.doe@example.com", "BH-1234"});
        tableModel.addRow(new Object[]{"DM-5678", "Jane Smith", "jane.smith@example.com", "BH-5678"});
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ViewDataEntryOperatorsPage frame = new ViewDataEntryOperatorsPage();
            frame.setVisible(true);
        });
    }
}
