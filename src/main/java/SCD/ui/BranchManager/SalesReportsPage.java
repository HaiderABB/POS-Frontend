package SCD.ui.BranchManager;



import javax.swing.*;

public class SalesReportsPage extends JFrame {
    public SalesReportsPage() {
        setTitle("Sales Reports Page");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel label = new JLabel("Sales Reports Page", JLabel.CENTER);
        label.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 24));
        add(label);

        setLocationRelativeTo(null);
        setVisible(true);
    }
}

