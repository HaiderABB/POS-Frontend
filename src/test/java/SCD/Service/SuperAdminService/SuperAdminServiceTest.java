package SCD.Service.SuperAdminService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import SCD.model.crud.local.BranchesDAO;
import SCD.model.crud.local.CodesDAO;
import SCD.model.crud.local.ProductDAO;
import SCD.model.crud.local.SaleDAO;
import SCD.model.crud.local.SyncTableDAO;
import SCD.model.models.Branch;
import SCD.model.models.Product;
import SCD.model.models.Sale;
import SCD.model.models.SyncTable;
import SCD.model.service.Json.AddResponseJSON;
import SCD.model.service.Json.GetReportJSON;
import SCD.model.service.SuperAdminService.SuperAdminService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class SuperAdminServiceTest {

  @InjectMocks
  private SuperAdminService superAdminService;

  @Mock
  private BranchesDAO branchesDAO;

  @Mock
  private ProductDAO productDAO;

  @Mock
  private SaleDAO saleDAO;

  @Mock
  private CodesDAO codesDAO;

  @Mock
  private SyncTableDAO syncTableDAO;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testIncrementCode() {
    String code = "0001";
    String incrementedCode = superAdminService.incrementCode(code);
    assertEquals("0002", incrementedCode);
  }

  @Test
  void testCreateBranch_Success() {
    Branch branch = new Branch();
    branch.setPhone("1234567890");

    when(branchesDAO.doesBranchExistWithPhone(branch.getPhone())).thenReturn(false);
    when(codesDAO.getCodeByTableName("BRANCHES")).thenReturn("0001");
    when(branchesDAO.addBranch(branch)).thenReturn(true);

    AddResponseJSON response = superAdminService.createBranch(branch);

    assertTrue(response.isSuccess());
    assertEquals("Branch Creation Successful", response.getMessage());
    verify(syncTableDAO, times(2)).addSyncTable(any(SyncTable.class));
  }

  @Test
  void testCreateBranch_BranchExists() {
    Branch branch = new Branch();
    branch.setPhone("1234567890");

    when(branchesDAO.doesBranchExistWithPhone(branch.getPhone())).thenReturn(true);

    AddResponseJSON response = superAdminService.createBranch(branch);

    assertFalse(response.isSuccess());
    assertEquals("Branch Exists with phone number", response.getMessage());
  }

  @Test
  void testDeleteBranch_Success() {
    String branchCode = "BR-0001";
    when(branchesDAO.getBranchByCode(branchCode)).thenReturn(new Branch());
    when(branchesDAO.getBranchActiveStatus(branchCode)).thenReturn(true);
    when(branchesDAO.deleteBranch(branchCode)).thenReturn(true);

    AddResponseJSON response = superAdminService.deleteBranch(branchCode);

    assertTrue(response.isSuccess());
    assertEquals("Branch Deletion Successful", response.getMessage());
    verify(syncTableDAO, times(1)).addSyncTable(any(SyncTable.class));
  }

  @Test
  void testDeleteBranch_AlreadyDeleted() {
    String branchCode = "BR-0001";
    when(branchesDAO.getBranchByCode(branchCode)).thenReturn(new Branch());
    when(branchesDAO.getBranchActiveStatus(branchCode)).thenReturn(false);

    AddResponseJSON response = superAdminService.deleteBranch(branchCode);

    assertTrue(response.isSuccess());
    assertEquals("Branch already deleted", response.getMessage());
  }

  @Test
  void testGetTodaysReport_NoBranch() {
    String branchCode = "BR-0001";
    when(branchesDAO.getBranchByCode(branchCode)).thenReturn(null);

    GetReportJSON response = superAdminService.getTodaysReport(branchCode);

    assertEquals("Branch not found", response.getMessage());
    assertNull(response.getSalesData());
    assertNull(response.getProducts());
  }

  @Test
  void testGetTodaysReport_Success() {
    String branchCode = "BR-0001";
    Branch branch = new Branch();
    branch.setActive(true);
    when(branchesDAO.getBranchByCode(branchCode)).thenReturn(branch);

    Product product = new Product();
    when(productDAO.getAllActiveProducts()).thenReturn(Arrays.asList(product));

    Sale sale = new Sale();
    sale.setProfit(100.0);
    when(saleDAO.getSalesForDay(LocalDate.now(), branchCode)).thenReturn(Arrays.asList(sale));

    GetReportJSON response = superAdminService.getTodaysReport(branchCode);

    assertEquals("Report Generated", response.getMessage());
    assertEquals(100.0, response.getProfit());
    assertNotNull(response.getSalesData());
    assertNotNull(response.getProducts());
  }
}