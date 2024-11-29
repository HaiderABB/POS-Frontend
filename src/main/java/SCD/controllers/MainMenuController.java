package SCD.controllers;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import SCD.ui.Common.MainMenu;
import SCD.ui.LoginPage;
import SCD.ui.SuperAdminLoginPage;
import SCD.ui.UserModel;

import javax.swing.*;

public class MainMenuController {
    private MainMenu mainMenu;

    public MainMenuController(MainMenu mainMenu) {
        this.mainMenu = mainMenu;

        this.mainMenu.getSuperAdminButton().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                showLoginPanel("Super Admin");
            }
        });

        this.mainMenu.getAdminButton().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                showLoginPanel("Admin/Branch Manager");
            }
        });

        this.mainMenu.getCashierButton().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                showLoginPanel("Cashier");
            }
        });

        this.mainMenu.getDataEntryButton().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                showLoginPanel("Data Entry Operator");
            }
        });
    }

    private void showLoginPanel(String userType) {
        UserModel model = new UserModel("", "");
        LoginPage view;

        if (userType.equals("Super Admin")) {
            view = new SuperAdminLoginPage();
        } else {
            view = new LoginPage();
        }

        LoginPageController controller = new LoginPageController(model, view);
        view.setTitle(userType + " Login");

        // Add heading to the login page
        JLabel heading = new JLabel(userType + " Login", JLabel.CENTER);
        heading.setBounds(250, 150, 300, 30);
        heading.setFont(new Font("Arial", Font.BOLD, 22));
        heading.setForeground(new Color(255, 102, 102));
        view.getContentPane().add(heading);

        view.setVisible(true);
        mainMenu.dispose();
    }

    public static void main(String[] args) {
        MainMenu mainMenu = new MainMenu();
        MainMenuController controller = new MainMenuController(mainMenu);
        mainMenu.setVisible(true);
    }
}