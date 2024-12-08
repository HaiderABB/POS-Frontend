package SCD.controllers.SuperAdminControllers.ManageBranchesController;

import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import SCD.model.models.Employee;
import SCD.ui.SuperAdmin.ManageBranches.AddBranchPage;

public class AddBranchController {

    private AddBranchPage addBranchPage;
    Employee employee;

    public AddBranchController(Employee employee) {
        this.employee = employee;
        SwingUtilities.invokeLater(() -> {
            AddBranchPage add = new AddBranchPage(employee);
            add.setVisible(true);
        });
    }

    public AddBranchController(AddBranchPage addBranchPage, Employee employee) {
        this.addBranchPage = addBranchPage;
        this.employee = employee;

        addBranchPage.getAddButton().addActionListener(e -> {
            String name = addBranchPage.getNameField();
            String city = addBranchPage.getCityField();
            String phone = addBranchPage.getPhoneField();
            String address = addBranchPage.getAddressField();

            if (validateInputs(name, city, phone, address)) {
                saveBranch(name, city, phone, address);
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

        if (!Pattern.matches("03\\d{2}-\\d{7}", phone)) {
            JOptionPane.showMessageDialog(addBranchPage, "Phone must follow the format '0321-1234567'.", "Error",
                    JOptionPane.ERROR_MESSAGE);
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
