package SCD.ui.Cashier;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import SCD.model.models.SaleItem;
import SCD.ui.Common.Props;

public class PaymentsPage extends JFrame implements Props {

    private List<SaleItem> saleItems;
    private double totalBill;
    private JLabel totalBillLabel;
    public JButton payButton;
    public JButton cancelButton;
    DefaultTableModel salesTableModel;
    JTable salesTable;

    public PaymentsPage(List<SaleItem> saleItems, double totalBill) {
        this.saleItems = saleItems;
        this.totalBill = totalBill;

        setTitle("Payments Page");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // scd- proj initContent Panel
        // scd- proj initJPanel contentPanel = new JPanel();
        // scd- proj initcontentPanel.setLayout(new BorderLayout());
        // scd- proj initadd(contentPanel, BorderLayout.CENTER);

        // scd- proj initTotal Bill Label
        totalBillLabel = new JLabel("Total Bill: $" + String.format("%.2f", totalBill));
        totalBillLabel.setFont(new Font("Arial", Font.BOLD, 24));
        totalBillLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(totalBillLabel, BorderLayout.NORTH);

        // scd- proj initCreate and Add Tables Panel
        createTablesPanel();

        JPanel buttoPanel = new JPanel();
        buttoPanel.setLayout(new FlowLayout());
        // scd- proj initAdd Confirm Button
        payButton = new JButton("Confirm");
        payButton.setFont(new Font("Arial", Font.BOLD, 18));

        cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("Arial", Font.BOLD, 18));
        buttoPanel.add(payButton);
        buttoPanel.add(cancelButton);

        add(buttoPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null); // scd- proj initCenter window on screen
    }

    private void createTablesPanel() {
        salesTableModel = new DefaultTableModel(new Object[] { "Product Code", "Quantity", "Sale Price", "Total" }, 0);

        for (SaleItem saleItem : saleItems) {
            Object[] rowData = {
                    saleItem.getProduct().getProductCode(),
                    saleItem.getQuantity(),
                    saleItem.getSalePrice(),
                    saleItem.getTotalPrice()
            };
            salesTableModel.addRow(rowData);
        }

        salesTable = new JTable(salesTableModel);

        JScrollPane salesScrollPane = new JScrollPane(salesTable);

        // scd- proj initCreate a panel for the table and add it to the content panel
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.add(salesScrollPane, BorderLayout.CENTER);

        add(tablePanel, BorderLayout.CENTER);
    }

    public void setTotalBill(double totalBill) {
        this.totalBill = totalBill;
        totalBillLabel.setText("Total Bill: $" + String.format("%.2f", totalBill));
    }

    public static void main(String[] args) {

        // scd- proj initCreate dummy products
        // scd- proj initProduct product1 = new Product("P001", "Product 1", 10.0);
        // scd- proj initProduct product2 = new Product("P002", "Product 2", 20.0);
        // scd- proj initProduct product3 = new Product("P003", "Product 3", 15.0);

        // scd- proj initCreate dummy sale items
        List<SaleItem> saleItems = new ArrayList<>();
        // scd- proj initsaleItems.add(new SaleItem(product1, 2, product1.getPrice()));
        // scd- proj initsaleItems.add(new SaleItem(product2, 1, product2.getPrice()));
        // scd- proj initsaleItems.add(new SaleItem(product3, 3, product3.getPrice()));

        // scd- proj initCalculate total bill
        double totalBill = 0.0;

        // scd- proj initShow the Payments Page
        PaymentsPage paymentsPage = new PaymentsPage(saleItems, totalBill);
        paymentsPage.setVisible(true);

    }

}
