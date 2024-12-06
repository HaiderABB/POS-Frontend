package SCD.ui.SuperAdmin;

import SCD.utils.BranchManagerValidator;
import org.junit.jupiter.api.Test;

import javax.swing.table.DefaultTableModel;

import static org.junit.jupiter.api.Assertions.*; // Use JUnit 5 assertions exclusively

public class ManageUsersPageTest {

    @Test
    public void testValidateBranchManagerInputs_ValidData() {
        String empCode = "MH-1234";
        String username = "johnDoe";
        String email = "john.doe@example.com";
        String phone = "0321-1234567";
        String branchCode = "BH-1234";

        assertTrue(BranchManagerValidator.validateBranchManagerInputs(empCode, username, email, phone, branchCode));
    }

    @Test
    public void testValidateBranchManagerInputs_InvalidEmpCode() {
        String empCode = "1234";
        String username = "johnDoe";
        String email = "john.doe@example.com";
        String phone = "0321-1234567";
        String branchCode = "BH-1234";

        assertFalse(BranchManagerValidator.validateBranchManagerInputs(empCode, username, email, phone, branchCode));
    }

    @Test
    public void testValidateBranchManagerInputs_InvalidEmail() {
        String empCode = "MH-1234";
        String username = "johnDoe";
        String email = "invalidEmail";
        String phone = "0321-1234567";
        String branchCode = "BH-1234";

        assertFalse(BranchManagerValidator.validateBranchManagerInputs(empCode, username, email, phone, branchCode));
    }

    @Test
    public void testTableRowAddition() {
        String[] columnNames = {"Employee Code", "Username", "Email", "Phone", "Branch Code"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        String empCode = "MH-1234";
        String username = "johnDoe";
        String email = "john.doe@example.com";
        String phone = "0321-1234567";
        String branchCode = "BH-1234";

        tableModel.addRow(new Object[]{empCode, username, email, phone, branchCode});

        assertEquals(1, tableModel.getRowCount());
        assertEquals("MH-1234", tableModel.getValueAt(0, 0));
        assertEquals("johnDoe", tableModel.getValueAt(0, 1));
        assertEquals("john.doe@example.com", tableModel.getValueAt(0, 2));
        assertEquals("0321-1234567", tableModel.getValueAt(0, 3));
        assertEquals("BH-1234", tableModel.getValueAt(0, 4));
    }

    @Test
    public void testIsExistingEmployeeCode() {
        String[] columnNames = {"Employee Code", "Username", "Email", "Phone", "Branch Code"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        String empCode = "MH-1234";
        tableModel.addRow(new Object[]{empCode, "johnDoe", "john.doe@example.com", "0321-1234567", "BH-1234"});

        boolean exists = false;
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            if (tableModel.getValueAt(i, 0).equals(empCode)) {
                exists = true;
                break;
            }
        }

        assertTrue(exists);
    }
}
