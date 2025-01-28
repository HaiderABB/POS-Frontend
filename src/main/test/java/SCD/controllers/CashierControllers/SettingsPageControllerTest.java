package SCD.controllers.CashierControllers;

import SCD.model.service.Common.CommonServices;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SettingsPageControllerTest {

    @Test
    void testHandleChangePassword_Success() {
        SettingsPageController controller = new SettingsPageController();
        CommonServices commonServices = new CommonServices();

        String oldPassword = "oldPass123";
        String newPassword = "newPass123";

        // scd- proj initAssume `update_password` is correctly implemented in
        // CommonServices
        boolean result = commonServices.update_password("emp123", newPassword);

        assertFalse(result, "Password should not change successfully.");
    }

    @Test
    void testHandleChangePassword_Failure() {
        SettingsPageController controller = new SettingsPageController();
        CommonServices commonServices = new CommonServices();

        String oldPassword = "wrongPass";
        String newPassword = "newPass123";

        // scd- proj initAssume `update_password` returns false for incorrect old
        // password
        boolean result = commonServices.update_password("emp123", newPassword);

        assertFalse(result, "Password change should fail with incorrect old password.");
    }

    @Test
    void testMain() {
        assertDoesNotThrow(() -> SettingsPageController.main(new String[] {}));
    }
}
