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

  public Sale addSale(String cashierCode, String branchCode, double totalAmount) {
    Transaction transaction = null;
    Sale sale = null;

    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      transaction = session.beginTransaction();

      // Fetch the cashier and branch entities
      Employee cashier = session.get(Employee.class, cashierCode);
      if (cashier == null) {
        System.err.println("Cashier with code " + cashierCode + " not found.");
        return null;
      }

      Branch branch = session.get(Branch.class, branchCode);
      if (branch == null) {
        System.err.println("Branch with code " + branchCode + " not found.");
        return null;
      }

      // Create the Sale object
      sale = new Sale(cashier, branch, totalAmount);

      // Persist the Sale entity
      session.persist(sale);

      // Commit the transaction
      transaction.commit();

      // Print the sale_id
      System.out.println("Sale added successfully with sale_id: " + sale.getSaleId());

    } catch (Exception e) {
      if (transaction != null) {
        transaction.rollback();
      }
      e.printStackTrace(); // Print the exception details for debugging
      sale = null; // Return null in case of an exception
    }

    return sale; // Return the Sale object after successful insertion
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
