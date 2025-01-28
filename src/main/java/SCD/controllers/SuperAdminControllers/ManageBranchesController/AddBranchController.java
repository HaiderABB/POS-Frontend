package SCD.controllers.SuperAdminControllers.ManageBranchesController;

import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import SCD.model.models.Branch;
import SCD.model.service.Json.AddResponseJSON;
import SCD.model.service.SuperAdminService.SuperAdminService;
import SCD.ui.SuperAdmin.ManageBranches.AddBranchPage;

public class AddBranchController {

    private AddBranchPage addBranchPage;
    SuperAdminService superAdminService;

    public AddBranchController() {

        superAdminService = new SuperAdminService();
        addBranchPage = new AddBranchPage();
        addBranchPage.setVisible(true);
        addBranchPage.getAddButton().addActionListener(e -> {
            String name = addBranchPage.getNameField();
            String city = addBranchPage.getCityField();
            String phone = addBranchPage.getPhoneField();
            String address = addBranchPage.getAddressField();

            if (validateInputs(name, city, phone, address)) {

                Branch branch = new Branch(name, city, address, phone);

                boolean res = superAdminService.checkPhoneExists(phone);
                if (res) {
                    JOptionPane.showMessageDialog(addBranchPage, "Phone number already exists!", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                AddResponseJSON json = superAdminService.createBranch(branch);

                if (json.isSuccess()) {
                    saveBranch(name, city, phone, address, json.getCode());
                } else {
                    JOptionPane.showMessageDialog(addBranchPage, "Failed to add branch!", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
                addBranchPage.clearFields();
            }
        });

    }

    public boolean validateInputs(String name, String city, String phone, String address) {
        if (name.isEmpty() || city.isEmpty() || phone.isEmpty() || address.isEmpty()) {
            JOptionPane.showMessageDialog(addBranchPage, "Please fill out all fields!", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!Pattern.matches("[A-Za-z\\s]+", city)) {
            JOptionPane.showMessageDialog(addBranchPage, "City must contain only letters!", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!Pattern.matches("03\\d{9}", phone)) {
            JOptionPane.showMessageDialog(addBranchPage, "Phone must follow the format '03XXXXXXXXX'.", "Error",
                    JOptionPane.ERROR_MESSAGE);

            return false;
        }

        return true;
    }

    void saveBranch(String name, String city, String phone, String address, String code) {
        String message = "Branch successfully added:\n"
                + "Branch Code: " + code + "\n"
                + "Name: " + name + "\n"
                + "City: " + city + "\n"
                + "Phone: " + phone + "\n"
                + "Address: " + address;
        JOptionPane.showMessageDialog(addBranchPage, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        // scd- proj initSwingUtilities.invokeLater(() -> {
        // scd- proj initAddBranchPage page = new AddBranchPage();
        // scd- proj initnew AddBranchController(page);
        // scd- proj initpage.setVisible(true);
        // scd- proj init});
    }
}
