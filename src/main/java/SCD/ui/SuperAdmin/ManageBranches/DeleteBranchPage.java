package SCD.ui.SuperAdmin.ManageBranches;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import SCD.ui.Common.Props;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import SCD.ui.Common.ButtonFactory;
import SCD.ui.Common.NavBar;
import SCD.ui.SuperAdmin.Sidebar;

public class DeleteBranchPage extends JFrame implements Props {

    private JTextField branchCodeField;
    private JButton deleteButton;

    public DeleteBranchPage() {
        setTitle("Delete Branch");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // scd- proj initSidebar
        Sidebar sidebar = new Sidebar();
        add(sidebar, BorderLayout.WEST);

        // scd- proj initContent Panel
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(contentPanel, BorderLayout.CENTER);

        // scd- proj initNavBar
        NavBar navBar = new NavBar();
        navBar.setTitle("Delete Branch");
        contentPanel.add(navBar, BorderLayout.NORTH);

        // scd- proj initForm Panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel branchCodePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel branchCodeLabel = new JLabel("Branch Code (BR-XXXX): ");
        branchCodeField = new JTextField();
        branchCodeField.setPreferredSize(new Dimension(200, 25));
        branchCodePanel.add(branchCodeLabel);
        branchCodePanel.add(branchCodeField);

        formPanel.add(branchCodePanel);

        // scd- proj initButton Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        deleteButton = ButtonFactory.createStyledButton("Delete Branch");
        buttonPanel.add(deleteButton);

        // scd- proj initAdd Panels to Content
        contentPanel.add(formPanel, BorderLayout.CENTER);
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
    }

    // scd- proj initGetters for input field and button to connect with the
    // controller
    public String getBranchCode() {
        return branchCodeField.getText().trim();
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public void clearBranchCodeField() {
        branchCodeField.setText("");
    }
}
