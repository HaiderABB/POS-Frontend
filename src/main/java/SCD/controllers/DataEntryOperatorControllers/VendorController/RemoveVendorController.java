package SCD.controllers.DataEntryOperatorControllers.VendorController;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import SCD.model.models.Vendor;
import SCD.model.service.DataEntryOperatorService.DataEntryOperatorService;
import SCD.model.service.Json.AddResponseJSON;
import SCD.ui.DataEntryOperator.Vendor.RemoveVendorPage;

public class RemoveVendorController {

    private final RemoveVendorPage view;
    DataEntryOperatorService dataEntryOperatorServices = new DataEntryOperatorService();

    public RemoveVendorController() {
        view = new RemoveVendorPage();
        initController();
        view.setVisible(true);
    }

    private void initController() {
        view.getRemoveButton().addActionListener(e -> handleRemoveVendor());
    }

    void handleRemoveVendor() {
        String vendorId = view.getVendorIdField().getText().trim();

        if (!validateVendorId(vendorId)) {
            return;
        }

        Vendor vendor = dataEntryOperatorServices.getVendorByCode(vendorId);

        if (vendor == null) {
            JOptionPane.showMessageDialog(view, "Vendor not found!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        AddResponseJSON json = dataEntryOperatorServices.removeVendor(vendorId);

        if (!json.isSuccess()) {
            JOptionPane.showMessageDialog(view, "Error removing!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        removeVendor(vendorId);
        view.getVendorIdField().setText("");
    }

    boolean validateVendorId(String vendorId) {
        if (vendorId.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Please enter a Vendor ID!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!vendorId.matches("VM-\\d{4}")) {
            JOptionPane.showMessageDialog(view, "Vendor ID must follow the format 'VM-XXXX'.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    boolean checkVendorExists(String vendorId) {
        return "VM-0001".equals(vendorId);
    }

    void removeVendor(String vendorId) {
        JOptionPane.showMessageDialog(view, "Vendor with ID " + vendorId + " removed successfully!", "Success",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(RemoveVendorController::new);
    }
}
