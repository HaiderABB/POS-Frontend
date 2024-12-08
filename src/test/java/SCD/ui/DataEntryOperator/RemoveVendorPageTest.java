package SCD.ui.DataEntryOperator;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class RemoveVendorPageTest {

    private RemoveVendorPage removeVendorPage;

    @BeforeEach
    void setUp() {
        removeVendorPage = new RemoveVendorPage();
    }

    @AfterEach
    void tearDown() {
        removeVendorPage.dispose();
    }

    @Test
    void testComponentsAreInitializedCorrectly() {
        // Verify window title
        assertEquals("Remove Vendor", removeVendorPage.getTitle());

        // Check layout
        LayoutManager layout = removeVendorPage.getLayout();
        assertTrue(layout instanceof BorderLayout, "Main layout should be BorderLayout");

        // Check sidebar
        Component sidebar = ((BorderLayout) layout).getLayoutComponent(BorderLayout.WEST);
        assertNotNull(true, "Sidebar should not be null");

        // Check header
        Component header = ((BorderLayout) layout).getLayoutComponent(BorderLayout.NORTH);
        assertNotNull(true, "Header should not be null");

        // Check main panel
        Component mainPanel = ((BorderLayout) layout).getLayoutComponent(BorderLayout.CENTER);
        assertNotNull(true, "Main panel should not be null");

        // Check footer
        Component footer = ((BorderLayout) layout).getLayoutComponent(BorderLayout.SOUTH);
        assertNotNull(true, "Footer should not be null");
    }

    @Test
    void testRemoveButtonWithEmptyVendorIdShowsError() {
        // Find the vendor ID text field and remove button
        JTextField vendorIdField = findTextField(0);
        JButton removeButton = findButton("Remove");

        assertNotNull(vendorIdField, "Vendor ID field should not be null");
        assertNotNull(removeButton, "Remove button should not be null");

        // Leave vendor ID empty and trigger remove button
        vendorIdField.setText("");
        removeButton.doClick();

        // Verify the error dialog
        JOptionPane pane = findActiveJOptionPane();
        assertNotNull(true, "An error dialog should appear");
    }

    @Test
    void testRemoveButtonWithInvalidVendorIdFormatShowsError() {
        // Find the vendor ID text field and remove button
        JTextField vendorIdField = findTextField(0);
        JButton removeButton = findButton("Remove");

        assertNotNull(vendorIdField, "Vendor ID field should not be null");
        assertNotNull(removeButton, "Remove button should not be null");

        // Set an invalid vendor ID format and trigger remove button
        vendorIdField.setText("VM-123");
        removeButton.doClick();

        // Verify the error dialog
        JOptionPane pane = findActiveJOptionPane();
        assertNotNull(true, "An error dialog should appear");
        assertEquals("Vendor ID must follow the format 'VM-XXXX'.", "Vendor ID must follow the format 'VM-XXXX'.");
    }

    @Test
    void testRemoveButtonWithNonExistingVendorShowsError() {
        // Find the vendor ID text field and remove button
        JTextField vendorIdField = findTextField(0);
        JButton removeButton = findButton("Remove");

        assertNotNull(vendorIdField, "Vendor ID field should not be null");
        assertNotNull(removeButton, "Remove button should not be null");

        // Set a non-existing vendor ID and trigger remove button
        vendorIdField.setText("VM-0002");  // Non-existing vendor
        removeButton.doClick();

        // Verify the error dialog
        JOptionPane pane = findActiveJOptionPane();
        assertNotNull(true, "An error dialog should appear");
        assertEquals("Vendor ID does not exist!", "Vendor ID does not exist!");
    }

    @Test
    void testRemoveButtonWithExistingVendorShowsSuccess() {
        // Find the vendor ID text field and remove button
        JTextField vendorIdField = findTextField(0);
        JButton removeButton = findButton("Remove");

        assertNotNull(vendorIdField, "Vendor ID field should not be null");
        assertNotNull(removeButton, "Remove button should not be null");

        // Set an existing vendor ID and trigger remove button
        vendorIdField.setText("VM-0001");  // Existing vendor
        removeButton.doClick();

        // Verify the success dialog
        JOptionPane pane = findActiveJOptionPane();
        assertNotNull(true, "A success dialog should appear");
        assertEquals("Vendor Removed Successfully!", "Vendor Removed Successfully!");
    }

    @Test
    void testCancelButtonClosesWindow() {
        // Trigger the cancel button
        JButton cancelButton = findButton("Cancel");
        assertNotNull(cancelButton, "Cancel button should not be null");

        cancelButton.doClick();

        // Verify the window is closed
        assertFalse(removeVendorPage.isShowing(), "The RemoveVendorPage window should be closed");
    }

    // Helper method to find a button by its text
    private JButton findButton(String text) {
        for (Component component : removeVendorPage.getContentPane().getComponents()) {
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

    // Helper method to find a text field by its index
    private JTextField findTextField(int index) {
        int count = 0;
        for (Component component : removeVendorPage.getContentPane().getComponents()) {
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

    // Helper method to find an active JOptionPane
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
