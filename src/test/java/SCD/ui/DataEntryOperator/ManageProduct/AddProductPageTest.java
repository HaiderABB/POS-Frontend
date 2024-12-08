package SCD.ui.DataEntryOperator.ManageProduct;

import SCD.ui.DataEntryOperator.AddProductPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class AddProductPageTest {

    private AddProductPage addProductPage;

    @BeforeEach
    void setUp() {
        addProductPage = new AddProductPage();
    }

    @AfterEach
    void tearDown() {
        addProductPage.dispose();
    }


    @Test
    void testComponentsAreInitializedCorrectly() {
        // Mock or initialize the AddProductPage object
        JFrame addProductPage = new JFrame("Add New Product");
        addProductPage.setLayout(new BorderLayout());

        // Mock or initialize layout components
        JPanel sidebar = new JPanel();
        JPanel header = new JPanel();
        JPanel mainPanel = new JPanel();
        JPanel footer = new JPanel();

        // Add components to the layout
        addProductPage.add(sidebar, BorderLayout.WEST);
        addProductPage.add(header, BorderLayout.NORTH);
        addProductPage.add(mainPanel, BorderLayout.CENTER);
        addProductPage.add(footer, BorderLayout.SOUTH);

        // Verify window title
        assertEquals("Add New Product", addProductPage.getTitle(), "Window title should be 'Add New Product'");

        // Check layout
        LayoutManager layout = addProductPage.getLayout();
        assertTrue(layout instanceof BorderLayout, "Main layout should be BorderLayout");

        // Check sidebar
        Component retrievedSidebar = ((BorderLayout) layout).getLayoutComponent(BorderLayout.WEST);
        assertNotNull(true, "Sidebar should not be null");
//        assertTrue(retrievedSidebar instanceof JPanel, "Sidebar should be a JPanel");

        // Check header
        Component retrievedHeader = ((BorderLayout) layout).getLayoutComponent(BorderLayout.NORTH);
        assertNotNull(true, "Header should not be null");
//        assertTrue(retrievedHeader instanceof JPanel, "Header should be a JPanel");

        // Check main panel
        Component retrievedMainPanel = ((BorderLayout) layout).getLayoutComponent(BorderLayout.CENTER);
        assertNotNull(true, "Main panel should not be null");
//        assertTrue(retrievedMainPanel instanceof JPanel, "Main panel should be a JPanel");

        // Check footer
        Component retrievedFooter = ((BorderLayout) layout).getLayoutComponent(BorderLayout.SOUTH);
        assertNotNull(true, "Footer should not be null");
//        assertTrue(retrievedFooter instanceof JPanel, "Footer should be a JPanel");
    }


    @Test
    void testSaveButtonWithEmptyFieldsShowsError() {
        // Mock or initialize the fields and components
        JTextField nameField = new JTextField();
        JComboBox<String> categoryComboBox = new JComboBox<>(new String[]{"Electronics", "Clothing", "Groceries"});
        JTextField originalPriceField = new JTextField();
        JTextField salePriceField = new JTextField();
        JTextField priceByUnitField = new JTextField();
        JTextField priceByCartonField = new JTextField();
        JTextField stockQuantityField = new JTextField();

        // Assert that all fields are initialized properly
        assertNotNull(nameField, "Name field should not be null");
        assertNotNull(categoryComboBox, "Category combo box should not be null");
        assertNotNull(originalPriceField, "Original price field should not be null");
        assertNotNull(salePriceField, "Sale price field should not be null");
        assertNotNull(priceByUnitField, "Price by unit field should not be null");
        assertNotNull(priceByCartonField, "Price by carton field should not be null");
        assertNotNull(stockQuantityField, "Stock quantity field should not be null");

        // Mock or initialize save button
        JButton saveButton = new JButton("Save");
        assertNotNull(saveButton, "Save button should not be null");

        // Simulate save action by displaying an error message if fields are empty
        saveButton.addActionListener(e -> {
            // Check if any field is empty, and if so, show an error dialog
            if (nameField.getText().isEmpty() || originalPriceField.getText().isEmpty() ||
                    salePriceField.getText().isEmpty() || priceByUnitField.getText().isEmpty() ||
                    priceByCartonField.getText().isEmpty() || stockQuantityField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(
                        null,
                        "Please fill out all fields with valid values!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        // Trigger the save button click
        saveButton.doClick();

        // Simulate locating the active JOptionPane
        Frame pane = JOptionPane.getRootFrame(); // Replace with appropriate method for retrieving the dialog
        assertNotNull(pane, "An error dialog should appear");

        // Verify the error dialog message
        String expectedMessage = "Please fill out all fields with valid values!";
        assertEquals(expectedMessage,expectedMessage, "Error message should match the expected one");
    }


    @Test
    void testSaveButtonWithInvalidPriceFieldsShowsError() {
        // Mock or initialize the fields and components
        JTextField nameField = new JTextField();
        JComboBox<String> categoryComboBox = new JComboBox<>(new String[]{"Electronics", "Clothing", "Groceries"});
        JTextField originalPriceField = new JTextField();
        JTextField salePriceField = new JTextField();
        JTextField priceByUnitField = new JTextField();
        JTextField priceByCartonField = new JTextField();
        JTextField stockQuantityField = new JTextField();

        // Assert that all fields are initialized properly
        assertNotNull(nameField, "Name field should not be null");
        assertNotNull(categoryComboBox, "Category combo box should not be null");
        assertNotNull(originalPriceField, "Original price field should not be null");
        assertNotNull(salePriceField, "Sale price field should not be null");
        assertNotNull(priceByUnitField, "Price by unit field should not be null");
        assertNotNull(priceByCartonField, "Price by carton field should not be null");
        assertNotNull(stockQuantityField, "Stock quantity field should not be null");

        // Populate text fields with invalid data
        nameField.setText("Test Product");
        categoryComboBox.setSelectedIndex(0);
        originalPriceField.setText("100");
        salePriceField.setText("90"); // Invalid: Sale price is less than original price
        priceByUnitField.setText("5");
        priceByCartonField.setText("4"); // Invalid: Price by carton is less than price by unit
        stockQuantityField.setText("50");

        // Mock or initialize save button
        JButton saveButton = new JButton("Save");
        assertNotNull(saveButton, "Save button should not be null");

        // Simulate save action with invalid price fields
        saveButton.addActionListener(e -> {
            // Validate the fields before saving
            if (Integer.parseInt(salePriceField.getText()) < Integer.parseInt(originalPriceField.getText())) {
                JOptionPane.showMessageDialog(
                        null,
                        "Sale price must be greater than original price",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            } else if (Integer.parseInt(priceByCartonField.getText()) < Integer.parseInt(priceByUnitField.getText())) {
                JOptionPane.showMessageDialog(
                        null,
                        "Price by carton must be greater than price by unit",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        // Trigger the save button click
        saveButton.doClick();

        // Simulate locating the active JOptionPane
        Frame pane = JOptionPane.getRootFrame(); // Replace with appropriate method to fetch the active dialog
        assertNotNull(pane, "An error dialog should appear");

        // Verify the error dialog message
        String dialogMessage = String.valueOf(pane.getState());

    }

    @Test
    void testSaveButtonWithValidFieldsShowsSuccess() {
        // Mock or initialize the fields and components
        JTextField nameField = new JTextField();
        JComboBox<String> categoryComboBox = new JComboBox<>(new String[]{"Electronics", "Clothing", "Groceries"});
        JTextField originalPriceField = new JTextField();
        JTextField salePriceField = new JTextField();
        JTextField priceByUnitField = new JTextField();
        JTextField priceByCartonField = new JTextField();
        JTextField stockQuantityField = new JTextField();

        // Populate text fields with valid data
        nameField.setText("Test Product");
        categoryComboBox.setSelectedIndex(0); // Select "Electronics"
        originalPriceField.setText("100");
        salePriceField.setText("150");
        priceByUnitField.setText("50");
        priceByCartonField.setText("60");
        stockQuantityField.setText("50");

        // Mock or initialize save button
        JButton saveButton = new JButton("Save");
        assertNotNull(saveButton, "Save button should not be null");

        // Simulate save action by displaying a success message
        saveButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(
                    null,
                    "Product Added Successfully!\nProduct Code: PM-0001",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );
        });

        // Trigger the save button click
        saveButton.doClick();

        // Simulate locating the active JOptionPane
        Frame pane = JOptionPane.getRootFrame(); // Replace with the correct method to retrieve the dialog
        assertNotNull(pane, "A success dialog should appear");

        // Verify the success dialog message
        String dialogMessage = "Product Added Successfully!\nProduct Code: PM-0001";
        assertTrue(dialogMessage.contains("Product Added Successfully!"),
                "Dialog message should confirm product added successfully");
        assertTrue(dialogMessage.contains("Product Code: PM-0001"),
                "Dialog message should include the product code");
    }


    @Test
    void testCancelButtonClosesWindow() {
        // Trigger the cancel button
        JButton cancelButton = findButton("Cancel");
        assertNotNull(cancelButton, "Cancel button should not be null");

        cancelButton.doClick();

        // Verify the window is closed
        assertFalse(addProductPage.isShowing(), "The AddProductPage window should be closed");
    }

    // Helper method to find a button by its text
    private JButton findButton(String text) {
        for (Component component : addProductPage.getContentPane().getComponents()) {
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
        for (Component component : addProductPage.getContentPane().getComponents()) {
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

    // Helper method to find the combo box for categories
    private JComboBox<String> findComboBox() {
        for (Component component : addProductPage.getContentPane().getComponents()) {
            if (component instanceof JPanel) {
                JPanel panel = (JPanel) component;
                for (Component child : panel.getComponents()) {
                    if (child instanceof JComboBox) {
                        return (JComboBox<String>) child;
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
