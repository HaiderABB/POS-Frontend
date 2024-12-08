package SCD.Service.Common;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import SCD.model.crud.local.BranchesDAO;
import SCD.model.crud.local.CodesDAO;
import SCD.model.crud.local.EmployeeDAO;
import SCD.model.crud.local.SyncTableDAO;
import SCD.model.models.Employee;
import SCD.model.models.SyncTable;
import SCD.model.service.Common.CommonServices;
import SCD.model.service.Json.AddResponseJSON;

public class CommonServicesTest {

  private CommonServices commonServices;

  @Mock
  private EmployeeDAO employeeDAO;
  @Mock
  private BranchesDAO branchesDAO;
  @Mock
  private CodesDAO codesDAO;
  @Mock
  private SyncTableDAO syncTable;

  @Before
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    commonServices = new CommonServices();
    commonServices.employeeDAO = employeeDAO;
    commonServices.branchesDAO = branchesDAO;
    commonServices.codesDAO = codesDAO;
    commonServices.syncTable = syncTable;
  }

  @Test
  public void testLogin_Success() {
    String empCode = "EMP001";
    String password = "password123";
    String role = "MANAGER";

    Employee mockEmployee = new Employee();
    when(employeeDAO.loginWithRole(empCode, password, role)).thenReturn(mockEmployee);

    Employee result = commonServices.Login(empCode, password, role);

    assertNotNull(result);
    verify(employeeDAO, times(1)).loginWithRole(empCode, password, role);
  }

  @Test
  public void testSetFirstLoginStatus_Success() {
    String empCode = "EMP001";

    when(employeeDAO.setFirstLoginToFalse(empCode)).thenReturn(true);

    boolean result = commonServices.setFirstLoginStatus(empCode);

    assertTrue(result);
    verify(employeeDAO, times(2)).setFirstLoginToFalse(empCode);
    verify(syncTable, times(1)).addSyncTable(any(SyncTable.class));
  }

  @Test
  public void testUpdatePassword_Success() {
    String empCode = "EMP001";
    String newPassword = "newPassword123";

    when(employeeDAO.updatePassword(empCode, newPassword)).thenReturn(true);

    boolean result = commonServices.update_password(empCode, newPassword);

    assertTrue(result);
    verify(employeeDAO, times(2)).updatePassword(empCode, newPassword);
    verify(syncTable, times(1)).addSyncTable(any(SyncTable.class));
  }

  @Test
  public void testAddEmployee_EmployeeExists() {
    Employee mockEmployee = new Employee();
    mockEmployee.setEmail("test@example.com");

    when(employeeDAO.isEmailExists(mockEmployee.getEmail())).thenReturn(true);

    AddResponseJSON result = commonServices.AddEmployee(mockEmployee);

    assertFalse(result.isSuccess());
    assertEquals("Employee already exists", result.getMessage());
    verify(employeeDAO, times(1)).isEmailExists(mockEmployee.getEmail());
  }

  @Test
  public void testAddEmployee_BranchDoesNotExist() {
    Employee mockEmployee = new Employee();
    mockEmployee.setEmail("test@example.com");
    mockEmployee.setBranch(null);

    when(employeeDAO.isEmailExists(mockEmployee.getEmail())).thenReturn(false);
    when(branchesDAO.getBranchByCode(anyString())).thenReturn(null);

    AddResponseJSON result = commonServices.AddEmployee(mockEmployee);

    assertFalse(result.isSuccess());
    assertEquals("Branch does not exist", result.getMessage());

  }

  @Test
  public void testRemoveEmployee_Success() {
    String empCode = "EMP001";

    when(employeeDAO.employeeExistsByEmployeeCode(empCode)).thenReturn(true);
    when(employeeDAO.deactivateEmployee(empCode)).thenReturn(true);

    AddResponseJSON result = commonServices.RemoveEmployee(empCode);

    assertTrue(result.isSuccess());
    assertEquals("Employee removed successfully", result.getMessage());
    verify(syncTable, times(1)).addSyncTable(any(SyncTable.class));
  }

  @Test
  public void testUpdateEmployee_Success() {
    Employee mockEmployee = new Employee();
    mockEmployee.setEmployeeCode("EMP001");

    when(employeeDAO.employeeExistsByEmployeeCode(mockEmployee.getEmployeeCode())).thenReturn(true);
    when(employeeDAO.updateEmployee(mockEmployee)).thenReturn(true);

    AddResponseJSON result = commonServices.UpdateEmployee(mockEmployee);

    assertTrue(result.isSuccess());
    assertEquals("Employee updated successfully", result.getMessage());
    verify(syncTable, times(1)).addSyncTable(any(SyncTable.class));
  }

  @Test
  public void testIncrementCode() {
    String initialCode = "0010";

    String result = commonServices.incrementCode(initialCode);

    assertEquals("0011", result);
  }

  @Test
  public void testLowerCaseEmail() {
    String email = "Test@Example.com";

    String result = commonServices.LowerCaseEmail(email);

    assertEquals("test@example.com", result);
  }
}
