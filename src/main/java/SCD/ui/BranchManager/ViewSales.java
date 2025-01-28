package SCD.ui.BranchManager;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import SCD.ui.Common.Props;

import SCD.ui.Cashier.cashierSidebar;
import SCD.ui.Common.NavBar;

public class ViewSales extends JFrame implements Props {

    private cashierSidebar sidebar;
    private NavBar navBar;
    private DefaultTableModel tableModel;
    private JTable salesTable;

    public ViewSales() {
        setTitle("View Sales");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        BranchSidebar sidebar = new BranchSidebar();
        add(sidebar, BorderLayout.WEST);

        // scd- proj initContent Panel
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(contentPanel, BorderLayout.CENTER);

        // scd- proj initNavBar
        navBar = new NavBar();
        navBar.setTitle("View Sales");
        contentPanel.add(navBar, BorderLayout.NORTH);

        // scd- proj initTable Panel
        JPanel tablePanel = new JPanel(new BorderLayout());
        String[] columnNames = { "Date", "Sale ID", "Amount", "Profit" };
        tableModel = new DefaultTableModel(columnNames, 0);
        salesTable = new JTable(tableModel);
        salesTable.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(salesTable);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        contentPanel.add(tablePanel, BorderLayout.CENTER);

        setLocationRelativeTo(null);

        // scd- proj initLoad Sample Data
        // scd- proj initloadSampleData();
    }

    public void loadSampleData() {
        // scd- proj initaddRow(new Object[]{"2023-10-01", "TXN12345", "$100.00"});
        // scd- proj initaddRow(new Object[]{"2023-10-02", "TXN12346", "$150.00"});
        // scd- proj initaddRow(new Object[]{"2023-10-03", "TXN12347", "$200.00"});
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public JTable getSalesTable() {
        return salesTable;
    }

    public void clearTable() {
        tableModel.setRowCount(0);
    }

    public void addRow(Object[] rowData) {
        tableModel.addRow(rowData);
    }

}
