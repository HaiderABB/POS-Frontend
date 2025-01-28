package SCD.controllers.DataEntryOperatorControllers.ProductController;

import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import SCD.model.models.Product;
import SCD.model.models.Vendor;
import SCD.model.service.DataEntryOperatorService.DataEntryOperatorService;
import SCD.model.service.Json.AddResponseJSON;
import SCD.ui.DataEntryOperator.Product.AddProductPage;

public class AddProductController {
    private final AddProductPage view;

    DataEntryOperatorService dataEntryOperatorService = new DataEntryOperatorService();

    private static final double MIN_PRICE = 0.01;
    private static final double MAX_PRICE = 10000.0;
    private static final int MIN_STOCK = 1;
    private static final int MAX_STOCK = 10000;

    public AddProductController() {
        view = new AddProductPage();
        view.setVisible(true);
        initController();
    }

    private void initController() {
        view.getSaveButton().addActionListener(e -> handleAddProduct());
    }

    private void handleAddProduct() {
        String name = view.getNameField().getText().trim();
        String category = (String) view.getCategoryComboBox().getSelectedItem();
        String originalPriceText = view.getOriginalPriceField().getText().trim();
        String salePriceText = view.getSalePriceField().getText().trim();
        String priceByUnitText = view.getPriceByUnitField().getText().trim();
        String priceByCartonText = view.getPriceByCartonField().getText().trim();
        String stockQuantityText = view.getStockQuantityField().getText().trim();
        String vendorCode = view.getVendorCodeField().getText().trim();

        if (!validateInputs(name, vendorCode, originalPriceText, salePriceText, priceByUnitText, priceByCartonText,
                stockQuantityText)) {
            return;
        }

        double originalPrice = Double.parseDouble(originalPriceText);
        double salePrice = Double.parseDouble(salePriceText);
        double priceByUnit = Double.parseDouble(priceByUnitText);
        double priceByCarton = Double.parseDouble(priceByCartonText);
        int stockQuantity = Integer.parseInt(stockQuantityText);

        if (!validateBusinessRules(originalPrice, salePrice, priceByUnit, priceByCarton, stockQuantity)) {
            return;
        }

        Vendor ven = dataEntryOperatorService.getVendorByCode(vendorCode);
        if (ven == null) {
            JOptionPane.showMessageDialog(view, "Vendor code does not exist!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Product product = new Product(ven, name, category, originalPrice, salePrice, priceByUnit, priceByCarton,
                stockQuantity);

        AddResponseJSON json = dataEntryOperatorService.addProduct(product);
        if (json.isSuccess()) {
            saveProduct(name, category, vendorCode, originalPrice, salePrice, priceByUnit, priceByCarton,
                    stockQuantity);
            clearFields();
        } else {
            JOptionPane.showMessageDialog(view, "Failed to add product: " + json.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);

        }

    }

    boolean validateInputs(String name, String vendorCode, String originalPrice, String salePrice,
                           String priceByUnit, String priceByCarton, String stockQuantity) {
        if (name.isEmpty() || vendorCode.isEmpty() || originalPrice.isEmpty() || salePrice.isEmpty()
                || priceByUnit.isEmpty() || priceByCarton.isEmpty() || stockQuantity.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Please fill out all fields!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!Pattern.matches("VM-\\d{4}", vendorCode)) {
            JOptionPane.showMessageDialog(view, "Vendor Code must follow the format 'VM-XXXX'.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        try {
            double original = Double.parseDouble(originalPrice);
            double sale = Double.parseDouble(salePrice);
            double unit = Double.parseDouble(priceByUnit);
            double carton = Double.parseDouble(priceByCarton);
            int stock = Integer.parseInt(stockQuantity);

            if (!checkLimits(original, sale, unit, carton, stock)) {
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Numeric values are required for prices and stock quantity!", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    boolean checkLimits(double original, double sale, double unit, double carton, int stock) {
        if (original < MIN_PRICE || original > MAX_PRICE) {
            JOptionPane.showMessageDialog(view,
                    "Original price must be between " + MIN_PRICE + " and " + MAX_PRICE + "!", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (sale < MIN_PRICE || sale > MAX_PRICE) {
            JOptionPane.showMessageDialog(view, "Sale price must be between " + MIN_PRICE + " and " + MAX_PRICE + "!",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (unit < MIN_PRICE || unit > MAX_PRICE) {
            JOptionPane.showMessageDialog(view,
                    "Price per unit must be between " + MIN_PRICE + " and " + MAX_PRICE + "!", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (carton < MIN_PRICE || carton > MAX_PRICE) {
            JOptionPane.showMessageDialog(view,
                    "Price per carton must be between " + MIN_PRICE + " and " + MAX_PRICE + "!", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (stock < MIN_STOCK || stock > MAX_STOCK) {
            JOptionPane.showMessageDialog(view,
                    "Stock quantity must be between " + MIN_STOCK + " and " + MAX_STOCK + "!", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    boolean validateBusinessRules(double originalPrice, double salePrice, double priceByUnit,
                                  double priceByCarton, int stockQuantity) {
        if (salePrice <= originalPrice) {
            JOptionPane.showMessageDialog(view, "Sale price must be greater than original price!", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (priceByCarton <= priceByUnit || priceByCarton <= salePrice || priceByCarton <= originalPrice) {
            JOptionPane.showMessageDialog(view, "Price per carton must be greater than all other prices!", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (priceByUnit < salePrice) {
            JOptionPane.showMessageDialog(view, "Price per unit must be greater than or equal to sale price!", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    private void saveProduct(String name, String category, String vendorCode, double originalPrice, double salePrice,
            double priceByUnit, double priceByCarton, int stockQuantity) {
        JOptionPane.showMessageDialog(view, "Product successfully added:\n"
                + "Name: " + name + "\n"
                + "Category: " + category + "\n"
                + "Vendor Code: " + vendorCode + "\n"
                + "Original Price: " + originalPrice + "\n"
                + "Sale Price: " + salePrice + "\n"
                + "Price per Unit: " + priceByUnit + "\n"
                + "Price per Carton: " + priceByCarton + "\n"
                + "Stock Quantity: " + stockQuantity, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    void clearFields() {
        view.getNameField().setText("");
        view.getOriginalPriceField().setText("");
        view.getSalePriceField().setText("");
        view.getPriceByUnitField().setText("");
        view.getPriceByCartonField().setText("");
        view.getStockQuantityField().setText("");
        view.getVendorCodeField().setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AddProductController::new);
    }
}
