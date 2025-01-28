package SCD.ui.SuperAdmin.ManageBranchManager;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import SCD.ui.Common.Props;

import SCD.ui.Common.ButtonFactory;
import SCD.ui.Common.NavBar;
import SCD.ui.SuperAdmin.Sidebar;

public class DeleteBranchManagerPage extends JFrame implements Props {
    private Sidebar sidebar;
    private NavBar navBar;
    private JTextField managerCodeField;
    private JButton deleteButton;

    public DeleteBranchManagerPage() {
        setTitle("Delete Branch Manager");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        sidebar = new Sidebar();
        add(sidebar, BorderLayout.WEST);

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(contentPanel, BorderLayout.CENTER);

        navBar = new NavBar();
        navBar.setTitle("Delete Branch Manager");
        contentPanel.add(navBar, BorderLayout.NORTH);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel managerCodePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel managerCodeLabel = new JLabel("Branch Manager Code (BM-XXXX): ");
        managerCodeField = new JTextField();
        managerCodeField.setPreferredSize(new Dimension(200, 25));
        managerCodePanel.add(managerCodeLabel);
        managerCodePanel.add(managerCodeField);

        formPanel.add(managerCodePanel);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        deleteButton = ButtonFactory.createStyledButton("Delete Branch Manager");
        buttonPanel.add(deleteButton);

        contentPanel.add(formPanel, BorderLayout.CENTER);
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
    }

    public JTextField getManagerCodeField() {
        return managerCodeField;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }
}
