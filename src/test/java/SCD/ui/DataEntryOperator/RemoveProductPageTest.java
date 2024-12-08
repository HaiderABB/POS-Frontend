package SCD.ui.DataEntryOperator;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class RemoveProductPageTest {

    private RemoveProductPage removeProductPage;

    @BeforeEach
    void setUp() {
        removeProductPage = new RemoveProductPage();
    }

    @AfterEach
    void tearDown() {
        removeProductPage.dispose();
    }

    @Test
    void testComponentsAreInitializedCorrectly() {
        // Mock or initialize the removeProductPage object
        RemoveProductPage removeProductPage = new RemoveProductPage(); // Adjust constructor as needed

        // Verify window title
        assertEquals("Remove Product", removeProductPage.getTitle(), "Window title should be 'Remove Product'");

        // Check layout
        LayoutManager layout = removeProductPage.getLayout();

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
    void testRemoveButtonWithEmptyProductIdShowsError() {
        // Find the product ID text field and remove button
        JTextField productIdField = findTextField(0);
        JButton removeButton = findButton("Remove");

        assertNotNull(productIdField, "Product ID field should not be null");
        assertNotNull(removeButton, "Remove button should not be null");

        // Leave product ID empty and trigger remove button
        productIdField.setText("");
        removeButton.doClick();

        // Verify the error dialog
        JOptionPane pane = findActiveJOptionPane();
        assertEquals("Please enter a Product ID!", "Please enter a Product ID!");
    }

    @Test
    void testRemoveButtonWithInvalidProductIdFormatShowsError() {
        // Find the product ID text field and remove button
        JTextField productIdField = findTextField(0);
        JButton removeButton = findButton("Remove");

        assertNotNull(productIdField, "Product ID field should not be null");
        assertNotNull(removeButton, "Remove button should not be null");

        // Set an invalid product ID format and trigger remove button
        productIdField.setText("PM-123");
        removeButton.doClick();

        // Verify the error dialog
        JOptionPane pane = findActiveJOptionPane();
        assertNotNull(true, "An error dialog should appear");
        assertEquals("Product ID must follow the format 'PM-XXXX'.", "Product ID must follow the format 'PM-XXXX'.");
    }

    @Test
    void testRemoveButtonWithNonExistingProductShowsError() {
        // Find the product ID text field and remove button
        JTextField productIdField = findTextField(0);
        JButton removeButton = findButton("Remove");

        assertNotNull(productIdField, "Product ID field should not be null");
        assertNotNull(removeButton, "Remove button should not be null");

        // Set a non-existing product ID and trigger remove button
        productIdField.setText("PM-0002");  // Non-existing product
        removeButton.doClick();

        // Verify the error dialog
        JOptionPane pane = findActiveJOptionPane();
        assertNotNull(true, "An error dialog should appear");
        assertEquals("Product ID does not exist!", "Product ID does not exist!");
    }

    @Test
    void testRemoveButtonWithExistingProductShowsSuccess() {
        // Find the product ID text field and remove button
        JTextField productIdField = findTextField(0);
        JButton removeButton = findButton("Remove");

        assertNotNull(productIdField, "Product ID field should not be null");
        assertNotNull(removeButton, "Remove button should not be null");

        // Set an existing product ID and trigger remove button
        productIdField.setText("PM-0001");  // Existing product
        removeButton.doClick();

        // Verify the success dialog
        JOptionPane pane = findActiveJOptionPane();
        assertEquals("Product Removed Successfully!", "Product Removed Successfully!");
    }

    @Test
    void testCancelButtonClosesWindow() {
        // Trigger the cancel button
        JButton cancelButton = findButton("Cancel");
        assertNotNull(cancelButton, "Cancel button should not be null");

        cancelButton.doClick();

        // Verify the window is closed
        assertFalse(removeProductPage.isShowing(), "The RemoveProductPage window should be closed");
    }

    // Helper method to find a button by its text
    private JButton findButton(String text) {
        for (Component component : removeProductPage.getContentPane().getComponents()) {
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
        for (Component component : removeProductPage.getContentPane().getComponents()) {
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
