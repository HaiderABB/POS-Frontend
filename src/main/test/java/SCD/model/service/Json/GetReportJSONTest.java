package SCD.model.service.Json;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import SCD.model.models.Product;
import SCD.model.models.Sale;

class GetReportJSONTest {

    private GetReportJSON report;

    @BeforeEach
    void setUp() {
        List<Sale> salesData = new ArrayList<>();
        salesData.add(new Sale());
        salesData.add(new Sale());

        List<Product> products = new ArrayList<>();
        products.add(new Product("PM-0001", null, "Product A", "Category A", 100.0, 90.0, 10.0, 90.0, 50));
        products.add(new Product("PM-0002", null, "Product B", "Category B", 200.0, 180.0, 20.0, 180.0, 30));

        report = new GetReportJSON(salesData, products, 500.0, "Report generated successfully");
    }

    @Test
    void testGetSalesData() {
        List<Sale> salesData = report.getSalesData();
        assertNotNull(salesData, "Sales data should not be null");
        assertEquals(2, salesData.size(), "Sales data should have 2 items");
    }

    @Test
    void testSetSalesData() {
        List<Sale> newSalesData = new ArrayList<>();
        newSalesData.add(new Sale());
        report.setSalesData(newSalesData);

        assertEquals(1, report.getSalesData().size(), "Sales data should be updated");
    }

    @Test
    void testGetProducts() {
        List<Product> products = report.getProducts();
        assertNotNull(products, "Products list should not be null");
        assertEquals(2, products.size(), "Products list should have 2 items");
    }

    @Test
    void testSetProducts() {
        List<Product> newProducts = new ArrayList<>();
        newProducts.add(new Product("PM-0003", null, "Product C", "Category C", 300.0, 270.0, 30.0, 270.0, 20));
        report.setProducts(newProducts);

        assertEquals(1, report.getProducts().size(), "Products list should be updated");
    }

    @Test
    void testGetProfit() {
        double profit = report.getProfit();
        assertEquals(500.0, profit, "Profit should match the initialized value");
    }

    @Test
    void testSetProfit() {
        report.setProfit(600.0);
        assertEquals(600.0, report.getProfit(), "Profit should be updated");
    }

    @Test
    void testGetMessage() {
        String message = report.getMessage();
        assertEquals("Report generated successfully", message, "Message should match the initialized value");
    }

    @Test
    void testSetMessage() {
        report.setMessage("Updated report message");
        assertEquals("Updated report message", report.getMessage(), "Message should be updated");
    }
}
