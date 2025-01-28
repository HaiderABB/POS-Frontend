package SCD.controllers.DataEntryOperatorControllers.ProductController;

import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import SCD.model.models.Product;
import SCD.model.service.DataEntryOperatorService.DataEntryOperatorService;
import SCD.model.service.Json.AddResponseJSON;
import SCD.ui.DataEntryOperator.Product.UpdateProductPage;

public class UpdateProductController {

    private UpdateProductPage view;
    DataEntryOperatorService dataEntryOperatorService = new DataEntryOperatorService();

    public UpdateProductController() {
        view = new UpdateProductPage();
        view.setVisible(true);
        initController();
    }

    private void initController() {
        view.getValidateButton().addActionListener(e -> handleValidation());
        view.getUpdateButton().addActionListener(e -> handleUpdate());
    }

    private void handleValidation() {
        String selectedField = (String) view.getFieldComboBox().getSelectedItem();
        String newValue = view.getNewValueField().getText().trim();

        if (newValue.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Please enter a new value!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean isValid = validateField(selectedField, newValue);

        if (isValid) {
            JOptionPane.showMessageDialog(view, "Valid " + selectedField + "!", "Success",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(view, "Invalid " + selectedField + "!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleUpdate() {
        String productCode = view.getProductCodeField().getText().trim();
        String selectedField = (String) view.getFieldComboBox().getSelectedItem();
        String newValue = view.getNewValueField().getText().trim();

        if (!validateProductCode(productCode)) {
            JOptionPane.showMessageDialog(view, "Invalid Product Code! Product Code must follow the format 'PM-XXXX'.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (newValue.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Please enter a new value for the selected field!", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!validateField(selectedField, newValue)) {
            return;
        }

        Product prod = dataEntryOperatorService.getProductByCode(productCode);

        if (prod == null) {
            JOptionPane.showMessageDialog(view, "Product with Code " + productCode + " not found!", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (selectedField.equals("Original Price") || selectedField.equals("Price per Unit")
                || selectedField.equals("Price per Carton")) {
            double originalPrice = prod.getOriginalPrice();
            double salePrice = prod.getSalePrice();
            double unitPrice = prod.getPriceByUnit();
            double cartonPrice = prod.getPriceByCarton();

            if (!validatePriceRules(originalPrice, salePrice, unitPrice, cartonPrice, selectedField,
                    Double.parseDouble(newValue))) {
                return;
            }
        }

        AddResponseJSON json = dataEntryOperatorService.updateProduct(prod);

        if (json.isSuccess()) {
            updateProduct(productCode, selectedField, newValue);
            view.getProductCodeField().setText("");
            view.getNewValueField().setText("");
        } else {
            JOptionPane.showMessageDialog(view, "Failed to update product!", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    boolean validateProductCode(String productCode) {
        return productCode.matches("PM-\\d{4}");
    }

    boolean validateField(String field, String value) {
        switch (field) {
            case "Name":
                return validateName(value);
            case "Price":
            case "Original Price":
            case "Price per Unit":
            case "Price per Carton":
                return validatePrice(value);
            case "Category":
                return validateCategory(value);
            case "Stock":
                return validateStock(value);
            default:
                JOptionPane.showMessageDialog(view, "Unknown Field Selected!", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
        }
    }

    private boolean validateName(String name) {
        return name != null && Pattern.matches("[A-Za-z\\s]+", name);
    }

    private boolean validatePrice(String price) {
        try {
            double priceValue = Double.parseDouble(price);
            return priceValue > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean validateCategory(String category) {
        return category != null && !category.trim().isEmpty();
    }

    private boolean validateStock(String stock) {
        try {
            int stockValue = Integer.parseInt(stock);
            return stockValue >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    boolean validatePriceRules(double originalPrice, double salePrice, double unitPrice, double cartonPrice,
            String field, double newValue) {
        // scd- proj initUpdate value for validation
        switch (field) {
            case "Original Price":
                originalPrice = newValue;
                break;
            case "Price":
                salePrice = newValue;
                break;
            case "Price per Unit":
                unitPrice = newValue;
                break;
            case "Price per Carton":
                cartonPrice = newValue;
                break;
        }

        if (originalPrice > salePrice) {
            JOptionPane.showMessageDialog(view, "Original price cannot be greater than the sale price!", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (unitPrice < salePrice) {
            JOptionPane.showMessageDialog(view, "Price per unit must be greater than or equal to the sale price!",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (cartonPrice <= unitPrice || cartonPrice <= salePrice || cartonPrice <= originalPrice) {
            JOptionPane.showMessageDialog(view, "Price per carton must be the greatest among all prices!", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    double getCurrentValue(String fieldName) {
        // scd- proj initPlaceholder for actual database
        // scd- proj initdummy values
        switch (fieldName) {
            case "Original Price":
                return 100.0;
            case "Price":
                return 120.0;
            case "Price per Unit":
                return 110.0;
            case "Price per Carton":
                return 150.0;
            default:
                return 0.0;
        }
    }

    private void updateProduct(String productCode, String field, String newValue) {
        JOptionPane.showMessageDialog(view,
                "Product with Code " + productCode + " updated successfully!\n" +
                        "Updated Field: " + field + "\n" +
                        "New Value: " + newValue,
                "Success",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(UpdateProductController::new);
    }
}
