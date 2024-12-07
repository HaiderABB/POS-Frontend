//package SCD.ui.Cashier;
//
//import SCD.ui.Common.ButtonFactory;
//
//import javax.swing.*;
//import java.awt.*;
//
//public class RefundFrame extends JFrame {
//
//    public RefundFrame() {
//        setTitle("Refund");
////        setSize(800, 400);
//        setSize(1000, 700);
//
//        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        setLayout(new BorderLayout());
//
//        // Sidebar
//        cashierSidebar sidebar = new cashierSidebar();
//        add(sidebar, BorderLayout.WEST);
//
//        // Header with title
//        JPanel headerPanel = new JPanel();
//        headerPanel.setBackground(new Color(255, 255, 255));
//        JLabel titleLabel = new JLabel("Refund");
//        titleLabel.setForeground(Color.black);
//        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
//        headerPanel.add(titleLabel);
//        add(headerPanel, BorderLayout.NORTH);
//
//        // Main form panel
//        JPanel mainPanel = new JPanel();
//        mainPanel.setLayout(new GridLayout(4, 2, 10, 10));
//        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
//        mainPanel.setBackground(Color.WHITE);
//
//        JLabel transactionIdLabel = new JLabel("Transaction ID:");
//        JTextField transactionIdField = new JTextField();
//
//        JLabel amountLabel = new JLabel("Amount:");
//        JTextField amountField = new JTextField();
//
//        JLabel reasonLabel = new JLabel("Reason:");
//        JTextField reasonField = new JTextField();
//
//        // Add components to the form panel
//        mainPanel.add(transactionIdLabel);
//        mainPanel.add(transactionIdField);
//        mainPanel.add(amountLabel);
//        mainPanel.add(amountField);
//        mainPanel.add(reasonLabel);
//        mainPanel.add(reasonField);
//
//        add(mainPanel, BorderLayout.CENTER);
//
//        // Footer with action buttons
//        JPanel footerPanel = new JPanel();
//        footerPanel.setBackground(new Color(255, 255, 255));
//        footerPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
//
//        JButton processButton = ButtonFactory.createStyledButton("Process Refund");
//        processButton.addActionListener(e -> {
//            // Implement refund processing logic here
//            JOptionPane.showMessageDialog(this, "Refund Processed Successfully!");
//        });
//
//        JButton cancelButton = ButtonFactory.createStyledButton("Cancel");
//        cancelButton.addActionListener(e -> dispose());
//
//        footerPanel.add(processButton);
//        footerPanel.add(cancelButton);
//        add(footerPanel, BorderLayout.SOUTH);
//
//        setLocationRelativeTo(null); // Center window on screen
//    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            RefundFrame frame = new RefundFrame();
//            frame.setVisible(true);
//        });
//    }
//}