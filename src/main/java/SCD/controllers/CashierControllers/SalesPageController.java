package SCD.controllers.CashierControllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import SCD.model.models.Product;
import SCD.model.models.SaleItem;
import SCD.model.service.CashierService.CashierService;
import SCD.model.service.Json.SaleItemJSON;
import SCD.ui.Cashier.SalesPage;

public class SalesPageController {

    final SalesPage view;
    final List<SaleItem> saleItems;
    final List<Product> productList;
    private double totalBill;
    CashierService cashierService = new CashierService();

    public SalesPageController() {
        this.view = new SalesPage();
        this.saleItems = new ArrayList<>();
        this.totalBill = 0.0;

        productList = cashierService.getProducts();

        initController();
        view.setVisible(true);
    }

    private void initController() {
        view.getAddButton().addActionListener(e -> handleAddProduct());
        view.getRemoveButton().addActionListener(e -> handleRemoveProduct());
        view.getSearchButton().addActionListener(e -> handleSearchProduct());
        view.getProceedButton().addActionListener(e -> handleProceed());
        view.getCancelButton().addActionListener(e -> handleCancel());
        view.getSearchField().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                handleSearchProduct();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                handleSearchProduct();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                handleSearchProduct();
            }
        });
        view.populateProductTable(productList); // scd- proj initPopulate all products initially

    }

    void handleAddProduct() {
        String productCode = view.getProductCodeField().getText().trim();
        String quantityText = view.getQuantityField().getText().trim();

        if (!view.validateProductCode(productCode)) {
            JOptionPane.showMessageDialog(view, "Invalid Product Code!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!view.validateQuantity(quantityText)) {
            JOptionPane.showMessageDialog(view, "Invalid Quantity!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int quantity = Integer.parseInt(quantityText);

        Product product = getProductByCode(productCode);

        if (product == null) {
            JOptionPane.showMessageDialog(view, "Product not found!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (product.isActive() == false) {
            JOptionPane.showMessageDialog(view, "Product not found!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        SaleItem sl = cashierService.addSaleItem(saleItems, quantity, product);

        if (sl != null) {
            updateView();
        } else {
            JOptionPane.showMessageDialog(view, "Low Stock!", "Error", JOptionPane.ERROR_MESSAGE);

        }

    }

    void handleRemoveProduct() {
        String productCode = view.getProductCodeField().getText().trim();
        String quantityText = view.getQuantityField().getText().trim();

        if (!view.validateProductCode(productCode)) {
            JOptionPane.showMessageDialog(view, "Invalid Product Code!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!view.validateQuantity(quantityText)) {
            JOptionPane.showMessageDialog(view, "Invalid Quantity!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Product product = getProductByCode(productCode);

        if (product == null) {
            JOptionPane.showMessageDialog(view, "Product not found!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (product.isActive() == false) {
            JOptionPane.showMessageDialog(view, "Product not found!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int quantity = Integer.parseInt(quantityText);

        SaleItemJSON json = cashierService.removeSaleItem(saleItems, productCode, quantity);

        if (json == null || json.isSuccess()) {
            JOptionPane.showMessageDialog(view,
                    "Item " + product.getName() + " Removed Successfully!", "Success",
                    JOptionPane.INFORMATION_MESSAGE);
            updateView();
        } else {
            JOptionPane.showMessageDialog(view, "Error in quantity!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    void handleSearchProduct() {
        String query = view.getSearchField().getText().trim().toLowerCase();
        List<Product> filteredProducts = productList.stream()
                .filter(product -> product.getProductCode().toLowerCase().contains(query)
                        || product.getName().toLowerCase().contains(query))
                .collect(Collectors.toList());
        view.populateProductTable(filteredProducts);
    }

    void handleProceed() {

        if (saleItems == null) {
            JOptionPane.showMessageDialog(view, "No Items in Cart!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (saleItems.isEmpty()) {
            JOptionPane.showMessageDialog(view, "No Items in Cart!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(view, "Proceeding to Payment\nTotal Bill: PKR" + String.format("%.2f", totalBill),
                "Information", JOptionPane.INFORMATION_MESSAGE);

        view.sidebarController.openDashboard();

        PaymentsController paymController = new PaymentsController(saleItems, totalBill);
    }

    public void clearCart() {
        saleItems.clear();
        updateView();
    }

    private void handleCancel() {
        int confirm = JOptionPane.showConfirmDialog(view, "Are you sure you want to cancel?", "Confirm",
                JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            view.dispose();
        }
    }

    private void updateView() {
        totalBill = cashierService.GenerateBill(saleItems);
        view.updateSalesTable(saleItems);
        view.updateTotalBill(totalBill);
        view.clearInputFields();
    }

    Product getProductByCode(String productCode) {
        return productList.stream()
                .filter(product -> product.getProductCode().equals(productCode))
                .findFirst()
                .orElse(null);
    }

    public static void main(String[] args) {
        new SalesPageController();
    }
}
