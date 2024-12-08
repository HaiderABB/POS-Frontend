package SCD.ui.SuperAdmin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import SCD.model.models.Employee;
import SCD.ui.Common.NavBar;

public class SuperAdminDashboard extends JFrame {

    private Sidebar sidebar;
    private NavBar navBar;
    private JPanel mainContent;
    Employee employee;

    public SuperAdminDashboard(Employee employee) {
        this.employee = employee;
        setTitle("Super Admin Dashboard");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        sidebar = new Sidebar(employee);
        add(sidebar, BorderLayout.WEST);

        JPanel contentPanel = new JPanel(new BorderLayout());
        add(contentPanel, BorderLayout.CENTER);

        navBar = new NavBar();
        navBar.setTitle("Dashboard");
        contentPanel.add(navBar, BorderLayout.NORTH);

        mainContent = createMainContent();
        contentPanel.add(mainContent, BorderLayout.CENTER);

        setLocationRelativeTo(null);
    }

    private JPanel createMainContent() {
        JPanel mainContent = new JPanel();
        mainContent.setLayout(new BoxLayout(mainContent, BoxLayout.Y_AXIS));
        mainContent.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        mainContent.setBackground(Color.WHITE);

        JLabel welcomeLabel = new JLabel("Welcome to the Super Admin Dashboard!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextArea responsibilityArea = new JTextArea(
                "As a Super Admin, you are responsible for:\n\n" +
                        "1. Managing branches and their details.\n" +
                        "2. Managing branch managers and their details.\n" +
                        "3. Monitoring reports for better decision-making.\n" +
                        "4. Configuring system settings for password Change.\n\n" +
                        "Navigate through the sidebar to access the respective modules.");
        responsibilityArea.setFont(new Font("Arial", Font.PLAIN, 16));
        responsibilityArea.setEditable(false);
        responsibilityArea.setWrapStyleWord(true);
        responsibilityArea.setLineWrap(true);
        responsibilityArea.setOpaque(false);
        responsibilityArea.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainContent.add(welcomeLabel);
        mainContent.add(Box.createRigidArea(new Dimension(0, 20)));
        mainContent.add(responsibilityArea);

        return mainContent;
    }
}
