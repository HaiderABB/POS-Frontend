package SCD.ui.DataEntryOperator;

import SCD.ui.Common.ButtonFactory;

import javax.swing.*;
import java.awt.*;
import SCD.ui.Common.Props;

public class DEOSidebar extends JPanel implements Props {

    private JButton dashboardButton;
    private JButton viewVendorsButton;
    private JButton addVendorButton;
    private JButton updateVendorButton;
    private JButton removeVendorButton;
    private JButton viewProductsButton;
    private JButton addProductButton;
    private JButton updateProductButton;
    private JButton removeProductButton;
    private JButton logoutButton;
    private JButton viewProductsByVendorButton;

    public DEOSidebar() {
        setPreferredSize(new Dimension(200, 700));
        setBackground(Props.bg);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel logo = new JLabel(new ImageIcon("path/to/logo.png")); // scd- proj initUpdate with correct logo path
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);
        logo.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        add(logo);

        dashboardButton = createButton("Dashboard");
        viewVendorsButton = createButton("View Vendors");
        addVendorButton = createButton("Add Vendor");
        updateVendorButton = createButton("Update Vendor");
        removeVendorButton = createButton("Remove Vendor");
        viewProductsButton = createButton("View Products");
        addProductButton = createButton("Add Product");
        updateProductButton = createButton("Update Product");
        removeProductButton = createButton("Remove Product");
        viewProductsByVendorButton = createButton("View Vendor Products");
        logoutButton = createButton("Log Out");

        add(dashboardButton);
        add(addVendorButton);
        add(updateVendorButton);
        add(removeVendorButton);
        add(viewVendorsButton);
        add(viewProductsButton);
        add(addProductButton);
        add(updateProductButton);
        add(removeProductButton);
        add(viewProductsButton);
        add(viewProductsByVendorButton);
        add(logoutButton);
    }

    private JButton createButton(String label) {
        return ButtonFactory.createStyledButton(label);
    }

    public JButton getDashboardButton() {
        return dashboardButton;
    }

    public JButton getViewVendorsButton() {
        return viewVendorsButton;
    }

    public JButton getAddVendorButton() {
        return addVendorButton;
    }

    public JButton getUpdateVendorButton() {
        return updateVendorButton;
    }

    public JButton getRemoveVendorButton() {
        return removeVendorButton;
    }

    public JButton getViewProductsButton() {
        return viewProductsButton;
    }

    public JButton getAddProductButton() {
        return addProductButton;
    }

    public JButton getUpdateProductButton() {
        return updateProductButton;
    }

    public JButton getRemoveProductButton() {
        return removeProductButton;
    }

    public JButton getLogoutButton() {
        return logoutButton;
    }

    public JButton getViewProductsByVendorButton() {
        return viewProductsByVendorButton;
    }
}
