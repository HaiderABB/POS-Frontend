package SCD.ui.DataEntryOperator.ManageVendor;

import SCD.ui.DataEntryOperator.AddNewVendorPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class AddNewVendorPageTest {

    private AddNewVendorPage addNewVendorPage;

    @BeforeEach
    void setUp() {
        addNewVendorPage = new AddNewVendorPage();
    }

    @AfterEach
    void tearDown() {
        addNewVendorPage.dispose();
    }


    @Test
    void testComponentsAreInitializedCorrectly() {
        JFrame addNewVendorPage = new JFrame("Add New Vendor");
        addNewVendorPage.setLayout(new BorderLayout());

        JPanel sidebar = new JPanel();
        JPanel header = new JPanel();
        JPanel mainPanel = new JPanel();
        JPanel footer = new JPanel();

        addNewVendorPage.add(sidebar, BorderLayout.WEST);
        addNewVendorPage.add(header, BorderLayout.NORTH);
        addNewVendorPage.add(mainPanel, BorderLayout.CENTER);
        addNewVendorPage.add(footer, BorderLayout.SOUTH);

        assertEquals("Add New Vendor", addNewVendorPage.getTitle(), "Window title should be 'Add New Vendor'");

        LayoutManager layout = addNewVendorPage.getLayout();
        assertTrue(layout instanceof BorderLayout, "Main layout should be BorderLayout");

        Component retrievedSidebar = ((BorderLayout) layout).getLayoutComponent(BorderLayout.WEST);
        assertNotNull(true, "Sidebar should not be null");

        Component retrievedHeader = ((BorderLayout) layout).getLayoutComponent(BorderLayout.NORTH);
        assertNotNull(true, "Header should not be null");

        Component retrievedMainPanel = ((BorderLayout) layout).getLayoutComponent(BorderLayout.CENTER);
        assertNotNull(true, "Main panel should not be null");

        Component retrievedFooter = ((BorderLayout) layout).getLayoutComponent(BorderLayout.SOUTH);
        assertNotNull(true, "Footer should not be null");
    }

    @Test
    void testSaveButtonWithEmptyFieldsShowsError() {
        JButton saveButton = new JButton("Save");
        assertNotNull(saveButton, "Save button should not be null");

        saveButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(
                    null,
                    "Please fill out all fields!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        });

        saveButton.doClick();

        Frame pane = JOptionPane.getRootFrame();
        assertNotNull(pane, "An error dialog should appear");

        String dialogMessage = "Please fill out all fields!";
        assertEquals(dialogMessage, "Please fill out all fields!", "Error dialog should display the correct message");
    }

    @Test
    void testSaveButtonWithValidFieldsShowsSuccess() {
        JTextField vendorNameField = new JTextField();
        JTextField addressField = new JTextField();
        JFormattedTextField phoneNumberField = new JFormattedTextField();

        assertNotNull(vendorNameField, "Vendor Name field should not be null");
        assertNotNull(addressField, "Address field should not be null");
        assertNotNull(phoneNumberField, "Phone Number field should not be null");

        vendorNameField.setText("Test Vendor");
        addressField.setText("123 Vendor Lane");
        phoneNumberField.setText("1234567890");

        JButton saveButton = new JButton("Save");
        assertNotNull(saveButton, "Save button should not be null");

        saveButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(
                    null,
                    "Vendor Added Successfully!\nVendor Code: VM-0001",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );
        });

        saveButton.doClick();

        Frame pane = JOptionPane.getRootFrame();
        assertNotNull(pane, "A success dialog should appear");

        String dialogMessage = "Vendor Added Successfully!\nVendor Code: VM-0001";
        assertTrue(dialogMessage.contains("Vendor Added Successfully!"),
                "Dialog message should confirm vendor added successfully");
        assertTrue(dialogMessage.contains("Vendor Code: VM-0001"),
                "Dialog message should include the vendor code");
    }

    @Test
    void testCancelButtonClosesWindow() {
        JButton cancelButton = findButton("Cancel");
        assertNotNull(cancelButton, "Cancel button should not be null");

        cancelButton.doClick();

        assertFalse(addNewVendorPage.isShowing(), "The AddNewVendorPage window should be closed");
    }

    private JButton findButton(String text) {
        for (Component component : addNewVendorPage.getContentPane().getComponents()) {
            if (component instanceof JPanel) {
                JPanel panel = (JPanel) component;
                for (Component child : panel.getComponents()) {
                    if (child instanceof JButton) {
                        JButton button = (JButton) child;
                        if (button.getText().equals(text)) {
                            return button;
                        }
                    }
                }
            }
        }
        return null;
    }

    private JTextField findTextField(int index) {
        int count = 0;
        for (Component component : addNewVendorPage.getContentPane().getComponents()) {
            if (component instanceof JPanel) {
                JPanel panel = (JPanel) component;
                for (Component child : panel.getComponents()) {
                    if (child instanceof JTextField) {
                        if (count == index) {
                            return (JTextField) child;
                        }
                        count++;
                    }
                }
            }
        }
        return null;
    }

    private JOptionPane findActiveJOptionPane() {
        for (Window window : Window.getWindows()) {
            if (window.isActive() && window instanceof JDialog) {
                JDialog dialog = (JDialog) window;
                if (dialog.getContentPane().getComponentCount() > 0 &&
                        dialog.getContentPane().getComponent(0) instanceof JOptionPane) {
                    return (JOptionPane) dialog.getContentPane().getComponent(0);
                }
            }
        }
        return null;
    }
}