package SCD.utils;


public class BranchManagerValidator {

    public static boolean validateBranchManagerInputs(String empCode, String username, String email, String phone, String branchCode) {
        if (!empCode.matches("MH-\\d{4}")) {
            return false;
        }
        if (username.isEmpty() || username.length() > 11) {
            return false;
        }
        if (!email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}")) {
            return false;
        }
        if (!phone.matches("03\\d{2}-\\d{7}")) {
            return false;
        }
        if (!branchCode.matches("BH-\\d{4}")) {
            return false;
        }
        return true;
    }
}
