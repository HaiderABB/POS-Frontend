package SCD.controllers.DataEntryOperatorControllers.ProductController;

import javax.swing.JOptionPane;

import SCD.model.models.Product;
import SCD.model.models.Vendor;
import SCD.model.service.DataEntryOperatorService.DataEntryOperatorService;
import SCD.model.service.Json.GetResponseJSON;
import SCD.ui.DataEntryOperator.Product.ViewProductsByVendorPage;

public class ViewProductsByVendorController {

    private ViewProductsByVendorPage view;
    DataEntryOperatorService dataEntryOperatorService = new DataEntryOperatorService();

    public ViewProductsByVendorController() {

        view = new ViewProductsByVendorPage();
        initController();
        view.setVisible(true);

    }

    private void initController() {
        view.getSearchButton().addActionListener(e -> handleSearch());
    }

    private void handleSearch() {
        String vendorCode = view.getVendorCodeField().getText().trim();

        if (!validateVendorCode(vendorCode)) {
            JOptionPane.showMessageDialog(view, "Invalid Vendor Code! Vendor Code must follow the format 'VM-XXXX'.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Vendor vendor = dataEntryOperatorService.getVendorByCode(vendorCode);
        if (vendor == null) {
            JOptionPane.showMessageDialog(view, "Vendor not found!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (vendor.isActive() == false) {
            JOptionPane.showMessageDialog(view, "Vendor is inactive!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        GetResponseJSON<Product> response = dataEntryOperatorService.getProductsByVendorCode(vendorCode);

        if (response.getData() == null) {
            JOptionPane.showMessageDialog(view, "Failed to fetch products!", "Error", JOptionPane.ERROR_MESSAGE);
            view.getTableModel().addRow(new Object[] { "-", "-", "-", "-", "-", "-", "-", "-" });
            return;
        }

        view.populateTable(response.getData());
    }

    boolean validateVendorCode(String vendorCode) {
        return vendorCode.matches("VM-\\d{4}");
    }

    public static void main(String[] args) {
        new ViewProductsByVendorController();
    }
}
