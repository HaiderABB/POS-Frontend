package SCD.ui.DataEntryOperator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DataEntryOperatorDashboard extends JFrame {

    private DEOSidebar sidebar;
    private JPanel mainContent;
    private boolean isSidebarVisible = true;

    public DataEntryOperatorDashboard() {
        setTitle("Data Entry Operator Dashboard");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        sidebar = new DEOSidebar();
        add(sidebar, BorderLayout.WEST);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        add(contentPanel, BorderLayout.CENTER);

        JPanel toggleBar = createToggleBar();
        contentPanel.add(toggleBar, BorderLayout.NORTH);

        mainContent = createMainContent();
        contentPanel.add(mainContent, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel createToggleBar() {
        JPanel toggleBar = new JPanel(new BorderLayout());
        toggleBar.setPreferredSize(new Dimension(1000, 50));
        toggleBar.setBackground(new Color(240, 240, 240));

        JButton toggleButton = new JButton("☰");
        toggleButton.setFont(new Font("Arial", Font.BOLD, 18));
        toggleButton.setFocusPainted(false);
        toggleButton.setBackground(Color.WHITE);
        toggleButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        toggleButton.addActionListener(this::toggleSidebar);

        toggleBar.add(toggleButton, BorderLayout.WEST);

        return toggleBar;
    }

    private void toggleSidebar(ActionEvent e) {
        isSidebarVisible = !isSidebarVisible;
        sidebar.setVisible(isSidebarVisible);
        revalidate();
        repaint();
    }

    private JPanel createMainContent() {
        JPanel mainContent = new JPanel();
        mainContent.setLayout(new BoxLayout(mainContent, BoxLayout.Y_AXIS));
        mainContent.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainContent.setBackground(Color.WHITE);

        JLabel welcomeLabel = new JLabel("Welcome to the Data Entry Operator Dashboard!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextArea responsibilityArea = new JTextArea(
                "As a Data Entry Operator, you are responsible for:\n\n" +
                        "1. Viewing and managing vendor information.\n" +
                        "2. Adding new vendors and products.\n" +
                        "3. Viewing and removing products and vendors.\n\n" +
                        "Navigate through the sidebar to access the respective modules."
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DataEntryOperatorDashboard dashboard = new DataEntryOperatorDashboard();
            dashboard.setVisible(true);
        });
    }
}