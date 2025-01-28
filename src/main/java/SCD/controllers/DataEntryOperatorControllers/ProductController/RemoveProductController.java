package SCD.controllers.DataEntryOperatorControllers.ProductController;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import SCD.model.service.DataEntryOperatorService.DataEntryOperatorService;
import SCD.model.service.Json.AddResponseJSON;
import SCD.ui.DataEntryOperator.Product.RemoveProductPage;

public class RemoveProductController {

    private final RemoveProductPage view;
    DataEntryOperatorService dataEntryOperatorServices = new DataEntryOperatorService();

    public RemoveProductController() {
        view = new RemoveProductPage();
        initController();
        view.setVisible(true); // scd- proj initEnsure the frame is visible after initialization
    }

    private void initController() {
        view.getRemoveButton().addActionListener(e -> handleRemoveProduct());
    }

    private void handleRemoveProduct() {
        String productId = view.getProductIdField().getText().trim();

        if (!validateProductId(productId)) {
            return;
        }

        AddResponseJSON json = dataEntryOperatorServices.removeProduct(productId);

        if (!json.isSuccess()) {
            JOptionPane.showMessageDialog(view, "Product not found!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        removeProduct(productId);
        view.getProductIdField().setText("");
    }

    boolean validateProductId(String productId) {
        if (productId.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Please enter a Product Code!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!productId.matches("PM-\\d{4}")) {
            JOptionPane.showMessageDialog(view, "Product ID must follow the format 'PM-XXXX'.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    boolean checkProductExists(String productId) {

        return "PM-0001".equals(productId);
    }

    private void removeProduct(String productId) {
        JOptionPane.showMessageDialog(view, "Product with ID " + productId + " removed successfully!", "Success",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(RemoveProductController::new);
    }
}
