package SCD.model.crud;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import SCD.model.models.Branch;
import SCD.model.models.Employee;
import SCD.model.models.Sale;
import SCD.utils.HibernateUtil;

public class SaleDAO {

  private static SaleDAO instance;

  private SaleDAO() {
  }

  public static SaleDAO getInstance() {
    if (instance == null) {
      synchronized (SaleDAO.class) {
        if (instance == null) {
          instance = new SaleDAO();
        }
      }
    }
    return instance;
  }

  public boolean addSale(String cashierCode, String branchCode, double totalAmount) {
    boolean result = false;
    Transaction transaction = null;

    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      transaction = session.beginTransaction();

      Employee cashier = session.get(Employee.class, cashierCode);
      if (cashier == null) {
        System.err.println("Cashier with code " + cashierCode + " not found.");
        return false;
      }

      Branch branch = session.get(Branch.class, branchCode);
      if (branch == null) {
        System.err.println("Branch with code " + branchCode + " not found.");
        return false;
      }

      Sale sale = new Sale(cashier, branch, totalAmount);

      session.persist(sale);
      transaction.commit();
      result = true;

    } catch (Exception e) {
      if (transaction != null) {
        transaction.rollback();
      }
    }

    return result;
  }

  public List<Sale> getSalesByBranch(String branchCode) {
    List<Sale> sales = null;

    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      String hql = "FROM Sale s WHERE s.branch.branchCode = :branchCode";
      sales = session.createQuery(hql, Sale.class)
          .setParameter("branchCode", branchCode)
          .getResultList();
    } catch (Exception e) {
    }

    return sales;
  }
}
