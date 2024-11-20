package SCD.ui;

import javax.swing.*;
import java.awt.*;
import SCD.controllers.LoginPageController;

public class SuperAdminLoginPage extends LoginPage {

    public SuperAdminLoginPage() {
        setTitle("Super Admin Login");

        // Customize the login panel for Super Admin
        JLabel superAdminHeading = new JLabel("Super Admin Login", JLabel.CENTER);
        superAdminHeading.setBounds(250, 150, 300, 30);
        superAdminHeading.setFont(new Font("Arial", Font.BOLD, 22));
        superAdminHeading.setForeground(new Color(255, 102, 102));
        getContentPane().add(superAdminHeading);

        JLabel instructionText = new JLabel("Please enter your credentials", JLabel.CENTER);
        instructionText.setBounds(250, 190, 300, 20);
        instructionText.setFont(new Font("Arial", Font.PLAIN, 16));
        instructionText.setForeground(Color.GRAY);
        getContentPane().add(instructionText);
    }

    public static void main(String[] args) {
        UserModel model = new UserModel("", "");
        SuperAdminLoginPage view = new SuperAdminLoginPage();
        LoginPageController controller = new LoginPageController(model, view);
        view.setVisible(true);
    }
}