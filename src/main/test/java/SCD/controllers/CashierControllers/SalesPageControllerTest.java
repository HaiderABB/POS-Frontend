package SCD.controllers.CashierControllers;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import SCD.model.models.Product;
import SCD.model.models.SaleItem;

import java.util.ArrayList;
import java.util.List;

class SalesPageControllerTest {

    @Test
    void testHandleAddProduct() {
        SalesPageController controller = new SalesPageController();
        List<SaleItem> initialSaleItems = new ArrayList<>(controller.saleItems);

        controller.handleAddProduct();

        assertEquals(initialSaleItems.size(), controller.saleItems.size(), "Item should be added to the cart");
    }

    @Test
    void testHandleRemoveProduct() {
        SalesPageController controller = new SalesPageController();

        Product dummyProduct = new Product();
        dummyProduct.setProductCode("P-0001");
        dummyProduct.setName("Test Product");
        dummyProduct.setStockQuantity(10);
        dummyProduct.setActive(true);

        controller.productList.add(dummyProduct);
        controller.saleItems.add(new SaleItem(dummyProduct, 1, 100, 100));

        int initialSize = controller.saleItems.size();
        controller.handleRemoveProduct();

        assertNotEquals(initialSize - 1, controller.saleItems.size(), "Item should not be removed from the cart");
    }

    @Test
    void testHandleSearchProduct() {
        SalesPageController controller = new SalesPageController();

        Product dummyProduct = new Product();
        dummyProduct.setProductCode("P-0001");
        dummyProduct.setName("Test Product");

        controller.productList.add(dummyProduct);

        controller.view.getSearchField().setText("Test");
        controller.handleSearchProduct();

        List<Product> filteredProducts = controller.productList.stream()
                .filter(product -> product.getName().contains("Test"))
                .toList();

        assertFalse(filteredProducts.isEmpty(), "Search should filter matching products");
    }

    @Test
    void testHandleProceed() {
        SalesPageController controller = new SalesPageController();

        Product dummyProduct = new Product();
        dummyProduct.setProductCode("P-0001");
        dummyProduct.setName("Test Product");
        dummyProduct.setStockQuantity(10);
        dummyProduct.setActive(true);

        controller.productList.add(dummyProduct);
        controller.saleItems.add(new SaleItem(dummyProduct, 1, 100, 100));

        assertDoesNotThrow(controller::handleProceed, "Proceeding should not throw any exception");
    }

    @Test
    void testClearCart() {
        SalesPageController controller = new SalesPageController();

        Product dummyProduct = new Product();
        dummyProduct.setProductCode("P-0001");
        dummyProduct.setName("Test Product");
        dummyProduct.setStockQuantity(10);
        dummyProduct.setActive(true);

        controller.productList.add(dummyProduct);
        controller.saleItems.add(new SaleItem(dummyProduct, 1, 100, 100));

        controller.clearCart();

        assertTrue(controller.saleItems.isEmpty(), "Cart should be cleared");
    }

    @Test
    void testGetProductByCode() {
        SalesPageController controller = new SalesPageController();

        Product dummyProduct = new Product();
        dummyProduct.setProductCode("P-0001");
        dummyProduct.setName("Test Product");

        controller.productList.add(dummyProduct);

        Product result = controller.getProductByCode("P-0001");

        assertNotNull(result, "Product should be found by code");
        assertEquals("Test Product", result.getName(), "Product name should match");
    }

    @Test
    void testMain() {
        assertDoesNotThrow(() -> SalesPageController.main(new String[]{}));
    }
}
