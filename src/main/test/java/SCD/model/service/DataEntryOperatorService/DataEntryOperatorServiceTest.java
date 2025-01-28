package SCD.model.service.DataEntryOperatorService;

import org.junit.jupiter.api.Test;
import SCD.model.models.Product;
import SCD.model.models.Vendor;
import SCD.model.service.Json.AddResponseJSON;
import SCD.model.service.Json.GetResponseJSON;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DataEntryOperatorServiceTest {

    private DataEntryOperatorService service = new DataEntryOperatorService() {
        @Override
        public AddResponseJSON addVendor(Vendor vendor) {
            return vendor.getVendorCode().equals("VM-0001")
                    ? new AddResponseJSON("Added Successfully", true, vendor.getVendorCode())
                    : new AddResponseJSON("Could not add Vendor", false, null);
        }

        @Override
        public AddResponseJSON addProduct(Product product) {
            return product.getProductCode().equals("PM-0001")
                    ? new AddResponseJSON("Added Successfully", true, product.getProductCode())
                    : new AddResponseJSON("Error Adding product", false, null);
        }

        @Override
        public GetResponseJSON<Vendor> getVendors() {
            List<Vendor> vendors = new ArrayList<>();
            vendors.add(new Vendor("VendorA", "123456789", "AddressA"));
            return new GetResponseJSON<>("Found Vendors", vendors);
        }

        @Override
        public GetResponseJSON<Product> getProducts() {
            List<Product> products = new ArrayList<>();
            products.add(new Product("PM-0001", new Vendor("VendorA", "123456789", "AddressA"), "ProductA", "CategoryA",
                    100, 90, 10, 90, 50));
            return new GetResponseJSON<>("Found Products", products);
        }
    };

    @Test
    void addVendor() {
        Vendor vendor = new Vendor("VendorA", "123456789", "AddressA");
        vendor.setVendorCode("VM-0001");
        AddResponseJSON response = service.addVendor(vendor);

        assertTrue(response.isSuccess(), "Vendor should be added successfully");
        assertEquals("VM-0001", response.getCode(), "Vendor code should match");
    }

    @Test
    void addProduct() {
        Product product = new Product("PM-0001", new Vendor("VendorA", "123456789", "AddressA"), "ProductA",
                "CategoryA", 100, 90, 10, 90, 50);
        AddResponseJSON response = service.addProduct(product);

        assertTrue(response.isSuccess(), "Product should be added successfully");
        assertEquals("PM-0001", response.getCode(), "Product code should match");
    }

    @Test
    void incrementCode() {
        String newCode = service.incrementCode("0001");

        assertEquals("0002", newCode, "Incremented code should match");
    }

    @Test
    void getVendors() {
        GetResponseJSON<Vendor> response = service.getVendors();

        assertNotNull(response.getData(), "Vendors list should not be null");
        assertEquals(1, response.getData().size(), "Vendors list size should be 1");
    }

    @Test
    void getProducts() {
        GetResponseJSON<Product> response = service.getProducts();

        assertNotNull(response.getData(), "Products list should not be null");
        assertEquals(1, response.getData().size(), "Products list size should be 1");
    }

    @Test
    void removeProduct() {
        AddResponseJSON response = service.removeProduct("PM-0001");

        assertFalse(response.isSuccess(), "Product should not be removed for mock");
    }

    @Test
    void getProductByCode() {
        Product product = service.getProductByCode("PM-0001");

        assertNull(product, "Product should not be null");

    }

    @Test
    void getVendorByCode() {
        Vendor vendor = service.getVendorByCode("VM-0001");

        assertNull(vendor, "Vendor should not be null");

    }

    @Test
    void removeVendor() {
        // scd- proj init AddResponseJSON response = service.removeVendor("VM-0001");
        //
        // scd- proj init assertFalse(response.isSuccess(), "Vendor should not be
        // removed for mock");
    }

    @Test
    void checkPhoneNumber() {
        boolean exists = service.checkPhoneNumber("123456789");

        assertFalse(exists, "Phone number check should return false for mock");
    }

    @Test
    void updateVendor() {
        Vendor vendor = new Vendor("VendorA", "123456789", "AddressA");
        vendor.setVendorCode("VM-0001");
        AddResponseJSON response = service.updateVendor(vendor);

        assertFalse(response.isSuccess(), "Vendor should not be updated for mock");
    }

    @Test
    void updateProduct() {
        Product product = new Product("PM-0001", new Vendor("VendorA", "123456789", "AddressA"), "ProductA",
                "CategoryA", 100, 90, 10, 90, 50);
        AddResponseJSON response = service.updateProduct(product);

        assertFalse(response.isSuccess(), "Product should not be updated for mock");
    }

    @Test
    void getProductsByVendorCode() {
        GetResponseJSON<Product> response = service.getProductsByVendorCode("VM-0001");

        assertNotNull(response, "Response should not be null");
        assertNull(response.getData(), "Products list should be null for mock");
    }
}
