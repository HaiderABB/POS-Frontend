package SCD.controllers.DataEntryOperatorControllers.VendorController;

import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import SCD.model.models.Vendor;
import SCD.model.service.DataEntryOperatorService.DataEntryOperatorService;
import SCD.model.service.Json.AddResponseJSON;
import SCD.ui.DataEntryOperator.Vendor.AddNewVendorPage;

public class AddNewVendorController {

    private final AddNewVendorPage view;
    DataEntryOperatorService dataEntryOperatorServices = new DataEntryOperatorService();

    public AddNewVendorController() {
        view = new AddNewVendorPage();
        view.setVisible(true);
        initController();
    }

    private void initController() {
        view.getAddButton().addActionListener(e -> handleAddVendor());
    }

    void handleAddVendor() {
        String vendorName = view.getVendorNameField().getText().trim();
        String address = view.getAddressField().getText().trim();
        String phone = view.getPhoneNumberField().getText().trim();

        if (!validateInputs(vendorName, address, phone)) {
            return;
        }

        boolean res = dataEntryOperatorServices.checkPhoneNumber(phone);
        if (res) {
            JOptionPane.showMessageDialog(view, "Phone Number already exists!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        AddResponseJSON json = dataEntryOperatorServices.addVendor(new Vendor(vendorName, phone, address));

        if (!json.isSuccess()) {
            JOptionPane.showMessageDialog(view, "Error Adding Vendor!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        saveVendor(vendorName, address, phone, json.getCode());
        clearFields();
    }

    boolean validateInputs(String vendorName, String address, String phone) {
        if (vendorName.isEmpty() || address.isEmpty() || phone.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Please fill out all fields!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!Pattern.matches("03\\d{9}", phone)) {
            JOptionPane.showMessageDialog(view, "Phone Number must follow the format '03XXXXXXXXX'!", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    public void saveVendor(String vendorName, String address, String phone, String code) {

        String message = "Vendor successfully added:\n"
                + "Code: " + code + "\n"
                + "Name: " + vendorName + "\n"
                + "Address: " + address + "\n"
                + "Phone: " + phone + "\n";
        JOptionPane.showMessageDialog(view, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    void clearFields() {
        view.getVendorNameField().setText("");
        view.getAddressField().setText("");
        view.getPhoneNumberField().setText("");
    }

    String generateVendorCode() {
        return "VM-" + String.format("%04d", (int) (Math.random() * 10000)); // scd- proj initGenerate random vendor
                                                                             // code
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AddNewVendorController::new);
    }
}
