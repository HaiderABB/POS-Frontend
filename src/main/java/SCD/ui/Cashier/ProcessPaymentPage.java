package SCD.ui.Cashier;

import SCD.ui.Common.ButtonFactory;

import javax.swing.*;
import java.awt.*;

public class ProcessPaymentPage extends JFrame {

    public ProcessPaymentPage() {
        setTitle("Process Payment");
//        setSize(800, 600); // Dimension adjusted to fit sidebar
        setSize(1000, 700);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Reuse CashierSidebar
        JPanel sidebar = new cashierSidebar();
        add(sidebar, BorderLayout.WEST);

        // Header with title
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(255, 255, 255));
        JLabel titleLabel = new JLabel("Process Payment");
        titleLabel.setForeground(Color.black);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerPanel.add(titleLabel);
        add(headerPanel, BorderLayout.NORTH);

        // Main form panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(5, 2, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(Color.white);

        JLabel amountLabel = new JLabel("Amount:");
        JTextField amountField = new JTextField();

        JLabel cardNumberLabel = new JLabel("Card Number:");
        JTextField cardNumberField = new JTextField();

        JLabel expiryDateLabel = new JLabel("Expiry Date (MM/YY):");
        JTextField expiryDateField = new JTextField();

        JLabel cvvLabel = new JLabel("CVV:");
        JTextField cvvField = new JTextField();

        // Add components to the form panel
        mainPanel.add(amountLabel);
        mainPanel.add(amountField);
        mainPanel.add(cardNumberLabel);
        mainPanel.add(cardNumberField);
        mainPanel.add(expiryDateLabel);
        mainPanel.add(expiryDateField);
        mainPanel.add(cvvLabel);
        mainPanel.add(cvvField);

        add(mainPanel, BorderLayout.CENTER);

        // Footer with action buttons
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(new Color(255, 255, 255));
        footerPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        JButton processButton = ButtonFactory.createStyledButton("Process Payment");
        processButton.addActionListener(e -> {
            // Implement payment processing logic here
            JOptionPane.showMessageDialog(this, "Payment Processed Successfully!");
        });

        JButton cancelButton = ButtonFactory.createStyledButton("Cancel");
        cancelButton.addActionListener(e -> navigateToPage(new CashierDashboard()));

        footerPanel.add(processButton);
        footerPanel.add(cancelButton);
        add(footerPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null); // Center window on screen
    }

    private void navigateToPage(JFrame page) {
        SwingUtilities.invokeLater(() -> {
            Window topLevelWindow = SwingUtilities.getWindowAncestor(this);
            if (topLevelWindow instanceof JFrame) {
                ((JFrame) topLevelWindow).dispose();
            }
            page.setVisible(true);
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ProcessPaymentPage page = new ProcessPaymentPage();
            page.setVisible(true);
        });
    }
}
