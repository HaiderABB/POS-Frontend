package SCD.model.service.Json;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import SCD.model.models.SaleItem;
import SCD.model.models.Product;

class SaleItemJSONTest {

    private SaleItemJSON saleItemJSON;

    @BeforeEach
    void setUp() {
        Product product = new Product("PM-0001", null, "Product A", "Category A", 100.0, 90.0, 10.0, 90.0, 50);
        SaleItem saleItem = new SaleItem(product, 2, product.getSalePrice(), product.getOriginalPrice());
        saleItemJSON = new SaleItemJSON(saleItem, true, "Sale item created successfully");
    }

    @Test
    void testGetSaleItem() {
        SaleItem saleItem = saleItemJSON.getSaleItem();
        assertNotNull(saleItem, "SaleItem should not be null");
        assertEquals(2, saleItem.getQuantity(), "SaleItem quantity should match");
    }

    @Test
    void testSetSaleItem() {
        Product newProduct = new Product("PM-0002", null, "Product B", "Category B", 200.0, 180.0, 20.0, 180.0, 30);
        SaleItem newSaleItem = new SaleItem(newProduct, 3, newProduct.getSalePrice(), newProduct.getOriginalPrice());
        saleItemJSON.setSaleItem(newSaleItem);

        assertNotNull(saleItemJSON.getSaleItem(), "SaleItem should be updated");
        assertEquals("PM-0002", saleItemJSON.getSaleItem().getProduct().getProductCode(), "Product code should match");
    }

    @Test
    void testIsSuccess() {
        assertTrue(saleItemJSON.isSuccess(), "isSuccess should return true");
        saleItemJSON.setSuccess(false);
        assertFalse(saleItemJSON.isSuccess(), "isSuccess should return false after being set");
    }

    @Test
    void testSetSuccess() {
        saleItemJSON.setSuccess(false);
        assertFalse(saleItemJSON.isSuccess(), "Success flag should be updated to false");

        saleItemJSON.setSuccess(true);
        assertTrue(saleItemJSON.isSuccess(), "Success flag should be updated to true");
    }

    @Test
    void testGetMessage() {
        assertEquals("Sale item created successfully", saleItemJSON.getMessage(), "Message should match the initialized value");
    }

    @Test
    void testSetMessage() {
        saleItemJSON.setMessage("Updated sale item message");
        assertEquals("Updated sale item message", saleItemJSON.getMessage(), "Message should be updated");
    }
}
