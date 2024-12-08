//package SCD.ui.Cashier;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class CashierDashboard extends JFrame {
//
//    private cashierSidebar sidebar;
//    private JPanel mainContent;
//    private boolean isSidebarVisible = true;
//
//    public CashierDashboard() {
//        setTitle("Cashier Dashboard");
//        setSize(1000, 700);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLayout(new BorderLayout());
//        sidebar = new cashierSidebar();
//        add(sidebar, BorderLayout.WEST);
//
//        JPanel contentPanel = new JPanel();
//        contentPanel.setLayout(new BorderLayout());
//        add(contentPanel, BorderLayout.CENTER);
//
//        JPanel toggleBar = createToggleBar();
//        contentPanel.add(toggleBar, BorderLayout.NORTH);
//
//        mainContent = createMainContent();
//        contentPanel.add(mainContent, BorderLayout.CENTER);
//
//        setLocationRelativeTo(null);
//    }
//
//    private JPanel createToggleBar() {
//        JPanel toggleBar = new JPanel(new BorderLayout());
//        toggleBar.setPreferredSize(new Dimension(1000, 50));
//        toggleBar.setBackground(new Color(240, 240, 240));
//
//        JButton toggleButton = new JButton("☰");
//        toggleButton.setFont(new Font("Arial", Font.BOLD, 18));
//        toggleButton.setFocusPainted(false);
//        toggleButton.setBackground(Color.WHITE);
//        toggleButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
//        toggleButton.addActionListener(this::toggleSidebar);
//
//        toggleBar.add(toggleButton, BorderLayout.WEST);
//
//        return toggleBar;
//    }
//
//    private void toggleSidebar(ActionEvent e) {
//        isSidebarVisible = !isSidebarVisible;
//        sidebar.setVisible(isSidebarVisible);
//        revalidate();
//        repaint();
//    }
//
//    private JPanel createMainContent() {
//        JPanel mainContent = new JPanel();
//        mainContent.setLayout(new GridLayout(2, 2, 20, 20));
//        mainContent.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
//        mainContent.setBackground(Color.WHITE);
//
//        mainContent.add(createCard("Sales Page", "C:\\Users\\AMMAR\\Desktop\\icons\\sales.png", this::openSalesPage));
//        mainContent.add(createCard("Settings", "C:\\Users\\AMMAR\\Desktop\\icons\\settings.png", this::openSettings));
//
//        return mainContent;
//    }
//
//    private JPanel createCard(String title, String iconPath, ActionListener action) {
//        JPanel card = new JPanel();
//        card.setBackground(Color.WHITE);
//        card.setLayout(new BorderLayout());
//        card.setBorder(BorderFactory.createCompoundBorder(
//                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
//                BorderFactory.createEmptyBorder(10, 10, 10, 10)
//        ));
//
//        JLabel icon = new JLabel(new ImageIcon(iconPath));
//        icon.setHorizontalAlignment(SwingConstants.CENTER);
//        card.add(icon, BorderLayout.CENTER);
//
//        JLabel cardTitle = new JLabel(title, JLabel.CENTER);
//        cardTitle.setFont(new Font("Arial", Font.BOLD, 16));
//        cardTitle.setForeground(new Color(0, 0, 0));
//        card.add(cardTitle, BorderLayout.SOUTH);
//
//        if (action != null) {
//            card.addMouseListener(new java.awt.event.MouseAdapter() {
//                public void mouseClicked(java.awt.event.MouseEvent evt) {
//                    card.setBackground(new Color(184, 184, 184, 255)); // Change background color on click
//                    action.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
//                }
//            });
//        }
//
//        return card;
//    }
//
//    private void openSalesPage(ActionEvent e) {
//        SwingUtilities.invokeLater(() -> {
//            SalesPage salesPage = new SalesPage();
//            salesPage.setVisible(true);
//            dispose();
//        });
//    }
//
//    private void openSettings(ActionEvent e) {
//        SwingUtilities.invokeLater(() -> {
//            SettingsPage settingsPage = new SettingsPage();
//            settingsPage.setVisible(true);
//            dispose();
//        });
//    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            CashierDashboard dashboard = new CashierDashboard();
//            dashboard.setVisible(true);
//        });
//    }
//}