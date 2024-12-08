package SCD.controllers.SuperAdminControllers.ManageBranchesController;

import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import SCD.model.models.Branch;
import SCD.model.models.Employee;
import SCD.model.service.Json.AddResponseJSON;
import SCD.model.service.SuperAdminService.SuperAdminService;
import SCD.ui.SuperAdmin.ManageBranches.AddBranchPage;

public class AddBranchController {

    private AddBranchPage addBranchPage;
    Employee employee;
    SuperAdminService superAdminService;

    public AddBranchController(Employee employee) {

        this.employee = employee;
        superAdminService = new SuperAdminService();
        SwingUtilities.invokeLater(() -> {
            AddBranchPage add = new AddBranchPage(employee);
            add.setVisible(true);
        });
    }

    public AddBranchController(AddBranchPage addBranchPage, Employee employee) {
        this.addBranchPage = addBranchPage;
        this.employee = employee;
        superAdminService = new SuperAdminService();

        addBranchPage.getAddButton().addActionListener(e -> {
            String name = addBranchPage.getNameField();
            String city = addBranchPage.getCityField();
            String phone = addBranchPage.getPhoneField();
            String address = addBranchPage.getAddressField();

            if (validateInputs(name, city, phone, address)) {

                Branch branch = new Branch(name, city, address, phone);
                AddResponseJSON json = superAdminService.createBranch(branch);
                if (json.isSuccess()) {
                    saveBranch(name, city, phone, address);
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
            JOptionPane.showMessageDialog(addBranchPage, "Phone must follow the format '0321-1234567'.", "Error", JOptionPane.ERROR_MESSAGE);

            return false;
        }

        return true;
    }

    private void saveBranch(String name, String city, String phone, String address) {
        String message = "Branch successfully added:\n"
                + "Name: " + name + "\n"
                + "City: " + city + "\n"
                + "Phone: " + phone + "\n"
                + "Address: " + address;
        JOptionPane.showMessageDialog(addBranchPage, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AddBranchPage page = new AddBranchPage(null);
            new AddBranchController(page, null);
            page.setVisible(true);
        });
    }
}
