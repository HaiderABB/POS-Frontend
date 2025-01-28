package SCD.model.service.CashierService;

import org.junit.jupiter.api.Test;
import SCD.model.models.*;
import SCD.model.service.Json.AddResponseJSON;
import SCD.model.service.Json.SaleItemJSON;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CashierServiceTest {

    private CashierService service = new CashierService();

    @Test
    void generateBill() {
        List<SaleItem> saleItems = new ArrayList<>();
        Product product1 = new Product("PM-0001", new Vendor("VendorA", "123456789", "AddressA"), "ProductA", "CategoryA", 100, 90, 10, 90, 50);
        SaleItem saleItem1 = new SaleItem(product1, 2, 90.0, 100.0);

        saleItems.add(saleItem1);

        double totalBill = service.GenerateBill(saleItems);
        assertEquals(180.0, totalBill, "Total bill should match the expected value");
    }

    @Test
    void addSaleItem() {
        List<SaleItem> saleItems = new ArrayList<>();
        Product product1 = new Product("PM-0001", new Vendor("VendorA", "123456789", "AddressA"), "ProductA", "CategoryA", 100, 90, 10, 90, 50);

        SaleItem saleItem = service.addSaleItem(saleItems, 2, product1);
        assertNotNull(saleItem, "Sale item should be added successfully");
        assertEquals(2, saleItem.getQuantity(), "Quantity should match the added value");
    }

    @Test
    void addSaleItem_ExceedsStock() {
        List<SaleItem> saleItems = new ArrayList<>();
        Product product1 = new Product("PM-0001", new Vendor("VendorA", "123456789", "AddressA"), "ProductA", "CategoryA", 100, 90, 10, 90, 2);

        SaleItem saleItem = service.addSaleItem(saleItems, 3, product1);
        assertNull(saleItem, "Sale item should not be added as it exceeds stock");
    }

    @Test
    void removeSaleItem() {
        List<SaleItem> saleItems = new ArrayList<>();
        Product product1 = new Product("PM-0001", new Vendor("VendorA", "123456789", "AddressA"), "ProductA", "CategoryA", 100, 90, 10, 90, 50);
        SaleItem saleItem1 = new SaleItem(product1, 2, 90.0, 100.0);
        saleItems.add(saleItem1);

        SaleItemJSON response = service.removeSaleItem(saleItems, "PM-0001", 1);
        assertNotNull(response, "Response should not be null");
        assertTrue(response.isSuccess(), "Sale item should be removed successfully");
        assertEquals(1, saleItem1.getQuantity(), "Remaining quantity should match");
    }

    @Test
    void removeSaleItem_RemoveAll() {
        List<SaleItem> saleItems = new ArrayList<>();
        Product product1 = new Product("PM-0001", new Vendor("VendorA", "123456789", "AddressA"), "ProductA", "CategoryA", 100, 90, 10, 90, 50);
        SaleItem saleItem1 = new SaleItem(product1, 2, 90.0, 100.0);
        saleItems.add(saleItem1);

        SaleItemJSON response = service.removeSaleItem(saleItems, "PM-0001", 2);
        assertNull(response, "Sale item should be completely removed from the list");
        assertTrue(saleItems.isEmpty(), "Sale items list should be empty");
    }

    @Test
    void proceedPayment() {
        List<SaleItem> saleItems = new ArrayList<>();
        Product product1 = new Product("PM-0001", new Vendor("VendorA", "123456789", "AddressA"), "ProductA", "CategoryA", 100, 90, 10, 90, 50);
        SaleItem saleItem1 = new SaleItem(product1, 2, 90.0, 100.0);
        saleItems.add(saleItem1);

        Employee cashier = new Employee();
        cashier.setEmployeeCode("EMP-0001");
        Branch branch = new Branch();
        branch.setBranchCode("BR-0001");

        AddResponseJSON response = service.proceedPayment(saleItems, cashier, branch, 180.0);
        assertNotNull(response, "Response should not be null");

    }

    @Test
    void getProducts() {
        List<Product> products = service.getProducts();
        assertNotNull(products, "Products list should not be null");
    }

    @Test
    void getProduct() {
        Product product = service.getProduct("PM-0001");
        assertNull(product, "Product should be null");

    }

    @Test
    void main() {
        assertDoesNotThrow(() -> CashierService.main(new String[]{}), "Main method should run without exceptions");
    }
}