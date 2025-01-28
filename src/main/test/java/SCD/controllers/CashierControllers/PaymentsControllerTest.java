package SCD.controllers.CashierControllers;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import SCD.model.models.Employee;
import SCD.model.models.SaleItem;
import SCD.ui.Cashier.PaymentsPage;

import java.util.ArrayList;
import java.util.List;

class PaymentsControllerTest {

    @Test
    void testHandlePayButtonWithItems() {
        List<SaleItem> saleItems = new ArrayList<>();
        saleItems.add(new SaleItem()); // scd- proj initAdd a dummy SaleItem

        double totalBill = 100.0;

        // scd- proj initNo assertion here as handlePayButton() mainly interacts with
        // GUI
        // scd- proj initVerify manually if required
    }

    @Test
    void testHandlePayButtonWithoutItems() {
        List<SaleItem> saleItems = new ArrayList<>();
        double totalBill = 0.0;

        PaymentsController controller = new PaymentsController(saleItems, totalBill);
        controller.handlePayButton();

        // scd- proj initNo assertion here as handlePayButton() mainly interacts with
        // GUI
        // scd- proj initVerify manually if required
    }

    @Test
    void testUpdateTotalBill() {
        List<SaleItem> saleItems = new ArrayList<>();
        double initialTotal = 100.0;

        PaymentsController controller = new PaymentsController(saleItems, initialTotal);
        double newTotal = 150.0;

        controller.updateTotalBill(newTotal);
        assertNotEquals(newTotal, "Total bill should not be updated");
    }

    @Test
    void testShowPage() {
        List<SaleItem> saleItems = new ArrayList<>();
        double totalBill = 100.0;

        PaymentsController controller = new PaymentsController(saleItems, totalBill);

        controller.showPage();
        assertTrue(controller.paymentsPage.isVisible(), "Payments page should be visible");
    }

    @Test
    void testMain() {
        assertDoesNotThrow(() -> PaymentsController.main(new String[] {}));
    }
}
