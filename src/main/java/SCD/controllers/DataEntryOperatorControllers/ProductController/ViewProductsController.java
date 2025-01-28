package SCD.controllers.DataEntryOperatorControllers.ProductController;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import SCD.model.models.Product;
import SCD.model.service.DataEntryOperatorService.DataEntryOperatorService;
import SCD.model.service.Json.GetResponseJSON;
import SCD.ui.DataEntryOperator.Product.ViewProductsPage;

public class ViewProductsController {

    private ViewProductsPage view;
    DataEntryOperatorService dataEntryOperatorService = new DataEntryOperatorService();

    public ViewProductsController() {

        view = new ViewProductsPage();
        initController();
        GetResponseJSON<Product> response = dataEntryOperatorService.getProducts();

        if (response.getData() != null) {
            List<Product> products = response.getData();
            DefaultTableModel model = view.getTableModel();
            model.setRowCount(0);
            for (Product product : products) {
                model.addRow(new Object[] {
                        product.getProductCode(),
                        product.getName(),
                        product.getCategory(),
                        product.getOriginalPrice(),
                        product.getSalePrice(),
                        product.getPriceByUnit(),
                        product.getPriceByCarton(),
                        product.getStockQuantity()
                });
            }
        } else {
            view.getTableModel().addRow(new Object[] { "-", "-", "-", "-", "-", "-", "-", "-" });
        }

        view.setVisible(true);

    }

    private void initController() {

    }

    public static void main(String[] args) {
        new ViewProductsController();
    }
}
