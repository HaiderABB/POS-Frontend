package SCD.ui.BranchManager.ManageDataEntryOperator;

import SCD.ui.Common.ButtonFactory;
import SCD.ui.Common.NavBar;
import SCD.ui.BranchManager.BranchSidebar;

import javax.swing.*;
import java.awt.*;

public class DeleteDataEntryOperatorPage extends JFrame {

    private BranchSidebar sidebar;
    private NavBar navBar;

    public DeleteDataEntryOperatorPage() {
        setTitle("Delete Data Entry Operator");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        sidebar = new BranchSidebar();
        add(sidebar, BorderLayout.WEST);

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(contentPanel, BorderLayout.CENTER);


        navBar = new NavBar();
        navBar.setTitle("Delete Data Entry Operator");
        contentPanel.add(navBar, BorderLayout.NORTH);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel operatorCodePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel operatorCodeLabel = new JLabel("Data Entry Operator ID (DM-XXXX): ");
        JTextField operatorCodeField = new JTextField();
        operatorCodeField.setPreferredSize(new Dimension(200, 25));
        operatorCodePanel.add(operatorCodeLabel);
        operatorCodePanel.add(operatorCodeField);

        formPanel.add(operatorCodePanel);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton deleteButton = ButtonFactory.createStyledButton("Delete Operator");
        buttonPanel.add(deleteButton);

        contentPanel.add(formPanel, BorderLayout.CENTER);
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);

        deleteButton.addActionListener(e -> {
            String operatorCode = operatorCodeField.getText().trim();

            if (!validateOperatorCode(operatorCode)) {
                return;
            }

            deleteOperator(operatorCode);
            operatorCodeField.setText("");
        });
    }

    private boolean validateOperatorCode(String operatorCode) {
        if (operatorCode.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a Data Entry Operator ID!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!operatorCode.matches("DM-\\d{4}")) {
            JOptionPane.showMessageDialog(this, "Data Entry Operator ID must follow the format 'DM-XXXX'.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    private void deleteOperator(String operatorCode) {
        JOptionPane.showMessageDialog(this, "Data Entry Operator with ID " + operatorCode + " deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DeleteDataEntryOperatorPage frame = new DeleteDataEntryOperatorPage();
            frame.setVisible(true);
        });
    }
}
