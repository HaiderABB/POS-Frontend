package SCD.controllers.BranchManagerControllers.CashierController;

import SCD.ui.BranchManager.ManageCashier.AddCashierPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddCashierControllerTest {

    private AddCashierController controller;
    private AddCashierPage mockView;

    @BeforeEach
    void setUp() {
        mockView = new AddCashierPage() {
            private javax.swing.JTextField nameField = new javax.swing.JTextField();
            private javax.swing.JTextField emailField = new javax.swing.JTextField();
            private javax.swing.JTextField phoneField = new javax.swing.JTextField();
            private javax.swing.JButton addButton = new javax.swing.JButton();

            @Override
            public javax.swing.JTextField getNameField() {
                return nameField;
            }

            @Override
            public javax.swing.JTextField getEmailField() {
                return emailField;
            }

            @Override
            public javax.swing.JTextField getPhoneField() {
                return phoneField;
            }

            @Override
            public javax.swing.JButton getAddButton() {
                return addButton;
            }
        };

        controller = new AddCashierController(mockView);
    }

    @Test
    void handleAddCashier() {
        mockView.getNameField().setText("John Doe");
        mockView.getEmailField().setText("doe@example.com");
        mockView.getPhoneField().setText("03001234567");


    }

    @Test
    void validateInputs() {
        assertTrue(controller.validateInputs("John Doe", "johndoe@example.com", "03001234567"));
        assertFalse(controller.validateInputs("", "johndoe@example.com", "03001234567"));
        assertFalse(controller.validateInputs("John Doe", "", "03001234567"));
        assertFalse(controller.validateInputs("John Doe", "johndoe@example.com", ""));
        assertFalse(controller.validateInputs("John Doe", "invalid-email", "03001234567"));
        assertFalse(controller.validateInputs("John Doe", "johndoe@example.com", "123456789"));
    }

    @Test
    void clearFields() {
        mockView.getNameField().setText("John Doe");
        mockView.getEmailField().setText("johndoe@example.com");
        mockView.getPhoneField().setText("03001234567");

        controller.clearFields();

        assertEquals("", mockView.getNameField().getText());
        assertEquals("", mockView.getEmailField().getText());
        assertEquals("", mockView.getPhoneField().getText());
    }

    @Test
    void main() {
        assertDoesNotThrow(() -> AddCashierController.main(new String[]{}));
    }
}
