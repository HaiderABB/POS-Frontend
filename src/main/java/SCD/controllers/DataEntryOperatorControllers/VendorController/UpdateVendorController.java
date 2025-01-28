package SCD.controllers.DataEntryOperatorControllers.VendorController;

import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import SCD.model.models.Vendor;
import SCD.model.service.DataEntryOperatorService.DataEntryOperatorService;
import SCD.model.service.Json.AddResponseJSON;
import SCD.ui.DataEntryOperator.Vendor.UpdateVendorPage;

public class UpdateVendorController {

    private UpdateVendorPage view;
    DataEntryOperatorService dataEntryOperatorServices = new DataEntryOperatorService();

    public UpdateVendorController() {
        view = new UpdateVendorPage();
        view.setVisible(true);
        initController();
    }

    private void initController() {
        view.getValidateButton().addActionListener(e -> handleValidation());
        view.getUpdateButton().addActionListener(e -> handleUpdate());
    }

    void handleValidation() {
        String selectedField = (String) view.getFieldComboBox().getSelectedItem();
        String newValue = view.getNewValueField().getText().trim();

        if (newValue.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Please enter a new value!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean isValid = validateField(selectedField, newValue);

        if (isValid) {
            JOptionPane.showMessageDialog(view, "Valid " + selectedField + "!", "Success",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(view, "Invalid " + selectedField + "!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    void handleUpdate() {
        String vendorCode = view.getVendorCodeField().getText().trim();
        String selectedField = (String) view.getFieldComboBox().getSelectedItem();
        String newValue = view.getNewValueField().getText().trim();

        if (!validateVendorCode(vendorCode)) {
            JOptionPane.showMessageDialog(view, "Invalid Vendor Code! Vendor Code must follow the format 'VM-XXXX'.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (newValue.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Please enter a new value for the selected field!", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!validateField(selectedField, newValue)) {
            return;
        }

        Vendor vendor = dataEntryOperatorServices.getVendorByCode(vendorCode);

        if (vendor == null) {
            JOptionPane.showMessageDialog(view, "Vendor not found!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (vendor.isActive() == false) {
            JOptionPane.showMessageDialog(view, "Vendor is not active!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String temp;

        if (selectedField.equals("Name")) {
            temp = vendor.getName();
            vendor.setName(newValue);
        } else if (selectedField.equals("Phone")) {
            temp = vendor.getPhoneNumber();
            boolean res = dataEntryOperatorServices.checkPhoneNumber(newValue);
            if (res) {
                JOptionPane.showMessageDialog(view, "Phone Number already exists!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            vendor.setPhoneNumber(newValue);
        } else {
            temp = vendor.getAddress();
            vendor.setAddress(newValue);
        }

        AddResponseJSON json = dataEntryOperatorServices.updateVendor(vendor);

        if (json.isSuccess()) {
            updateVendor(vendorCode, selectedField, newValue);
            view.getVendorCodeField().setText("");
            view.getNewValueField().setText("");
        } else {
            JOptionPane.showMessageDialog(view, "Update failed!", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    boolean validateVendorCode(String vendorCode) {
        return vendorCode.matches("VM-\\d{4}");
    }

    boolean validateField(String field, String value) {
        switch (field) {
            case "Name":
                return validateName(value);
            case "Address":
                return validateAddress(value);
            case "Phone":
                return validatePhone(value);
            default:
                JOptionPane.showMessageDialog(view, "Unknown Field Selected!", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
        }
    }

    boolean validateName(String name) {
        return name != null && Pattern.matches("[A-Za-z\\s]+", name);
    }

    boolean validateAddress(String address) {
        return address != null && !address.trim().isEmpty();
    }

    boolean validatePhone(String phone) {
        return phone != null && phone.matches("\\d{10,15}");
    }

    void updateVendor(String vendorCode, String field, String newValue) {
        JOptionPane.showMessageDialog(view,
                "Vendor with Code " + vendorCode + " updated successfully!\n" +
                        "Updated Field: " + field + "\n" +
                        "New Value: " + newValue,
                "Success",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(UpdateVendorController::new);
    }
}
