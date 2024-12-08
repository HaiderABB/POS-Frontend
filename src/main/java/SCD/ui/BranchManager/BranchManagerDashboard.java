package SCD.ui.BranchManager;

import SCD.ui.Common.NavBar;

import javax.swing.*;
import java.awt.*;

public class BranchManagerDashboard extends JFrame {

    private BranchSidebar sidebar;
    private JPanel mainContent;
    private NavBar navBar;

    public BranchManagerDashboard() {
        setTitle("Branch Manager Dashboard");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        sidebar = new BranchSidebar();
        add(sidebar, BorderLayout.WEST);

        JPanel contentPanel = new JPanel(new BorderLayout());
        add(contentPanel, BorderLayout.CENTER);

        navBar = new NavBar();
        contentPanel.add(navBar, BorderLayout.NORTH);
        navBar.setTitle("Branch Manager Dashboard");

        mainContent = createMainContent();
        contentPanel.add(mainContent, BorderLayout.CENTER);

        setLocationRelativeTo(null);
    }

    private JPanel createMainContent() {
        JPanel mainContent = new JPanel();
        mainContent.setLayout(new BoxLayout(mainContent, BoxLayout.Y_AXIS));
        mainContent.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainContent.setBackground(Color.WHITE);

        JLabel welcomeLabel = new JLabel("Welcome to the Branch Manager Dashboard");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextArea responsibilityArea = new JTextArea(
                "Responsibilities of the Branch Manager:\n\n" +
                        "1. Manage cashiers within the branch.\n" +
                        "2. Oversee data entry operators for accurate record maintenance.\n" +
                        "3. Ensure proper branch operations and data integrity.\n" +
                        "4. Collaborate with the central team to meet operational goals.\n" +
                        "5. Handle any branch-level issues and ensure smooth workflows."
        );
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
