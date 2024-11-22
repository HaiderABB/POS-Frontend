package SCD.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import SCD.ui.MainMenu;
import SCD.ui.LoginPage;
import SCD.ui.UserModel;

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
        LoginPage view = new LoginPage();
        LoginPageController controller = new LoginPageController(model, view);
        view.setTitle(userType + " Login");
        view.setVisible(true);
        mainMenu.dispose();
    }

    public static void main(String[] args) {
        MainMenu mainMenu = new MainMenu();
        MainMenuController controller = new MainMenuController(mainMenu);
        mainMenu.setVisible(true);
    }
}