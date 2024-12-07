package SCD.ui.DataEntryOperator;

import SCD.ui.Common.ButtonFactory;

import javax.swing.*;
import java.awt.*;

public class DEOSidebar extends JPanel {

    public DEOSidebar() {

        setPreferredSize(new Dimension(240, 800)); // Set the same size as the SuperAdmin sidebar
        setBackground(new Color(255, 102, 102));

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Placeholder for logo or application name
        JLabel logo = new JLabel(new ImageIcon("path/to/logo.png")); // Update with the correct logo path
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);
        logo.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        add(logo);

        // Add buttons with corresponding navigation actions
        add(createButton("Dashboard", this::openDashboard));
        add(createButton("View Vendors", this::openViewVendorsPage));
        add(createButton("Add Vendor", this::openAddVendorPage));
        add(createButton("Remove Vendor", this::openRemoveVendorPage));
        add(createButton("View Products", this::openViewProductsPage));
        add(createButton("Add Product", this::openAddProductPage));
        add(createButton("Remove Product", this::openRemoveProductPage));
        add(createButton("Logout", this::logout));
    }

    private JButton createButton(String label, Runnable action) {
        JButton button = ButtonFactory.createStyledButton(label); // Reusing styled button from ButtonFactory
        button.addActionListener(e -> action.run());
        return button;
    }

    private void openDashboard() {
        navigateToPage(new DataEntryOperatorDashboard());
    }

    private void openViewVendorsPage() {
        navigateToPage(new ViewVendorsPage());
    }

    private void openAddVendorPage() {
        navigateToPage(new AddNewVendorPage());
    }

    private void openViewProductsPage() {
        navigateToPage(new ViewProductsPage());
    }

    private void openAddProductPage() {

        navigateToPage(new AddProductPage());
    }

    private void openRemoveVendorPage() {
        navigateToPage(new RemoveVendorPage());
    }

    private void openRemoveProductPage() {
        navigateToPage(new RemoveProductPage());
    }

    private void logout() {
        JOptionPane.showMessageDialog(this, "Logging out...");
        System.exit(0);
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

}
