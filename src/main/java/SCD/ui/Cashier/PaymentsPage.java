package SCD.ui.Cashier;

import SCD.model.models.SaleItem;
import SCD.ui.Common.ButtonFactory;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentsPage extends JFrame {

    private List<SaleItem> saleItems;
    private double totalBill;

    public PaymentsPage(List<SaleItem> saleItems, double totalBill) {
        this.saleItems = saleItems;
        this.totalBill = totalBill;

        setTitle("Payments Page");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Consistent Sidebar
        JPanel sidebar = new cashierSidebar();
        add(sidebar, BorderLayout.WEST);

        // Content Panel
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        add(contentPanel, BorderLayout.CENTER);

        // Total Bill Label
        JLabel totalBillLabel = new JLabel("Total Bill: $" + String.format("%.2f", totalBill));
        totalBillLabel.setFont(new Font("Arial", Font.BOLD, 24));
        totalBillLabel.setHorizontalAlignment(SwingConstants.CENTER);
        contentPanel.add(totalBillLabel, BorderLayout.CENTER);

        setLocationRelativeTo(null); // Center window on screen
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PaymentsPage paymentsPage = new PaymentsPage(new ArrayList<>(), 0.0);
            paymentsPage.setVisible(true);
        });
    }
}