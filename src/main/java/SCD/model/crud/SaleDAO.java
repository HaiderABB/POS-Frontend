package SCD.model.crud;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

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

  public Sale addSale(String cashierCode, String branchCode, double totalAmount, double profit) {
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
      sale = new Sale(cashier, branch, totalAmount, profit);

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

      sale = null;
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

  // Method to get all sales for a specific day and branch
  public List<Sale> getSalesForDay(LocalDate date, String branchCode) {
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      String hql = "FROM Sale s WHERE s.createdAt >= :startOfDay AND s.createdAt < :endOfDay AND s.branch.branchCode = :branchCode";
      LocalDateTime startOfDay = date.atStartOfDay();
      LocalDateTime endOfDay = date.atTime(23, 59, 59);

      Query<Sale> query = session.createQuery(hql, Sale.class);
      query.setParameter("startOfDay", startOfDay);
      query.setParameter("endOfDay", endOfDay);
      query.setParameter("branchCode", branchCode);

      return query.list();
    } catch (Exception e) {
      return null;
    }
  }

  // Method to get all sales for a specific week and branch
  public List<Sale> getSalesForWeek(LocalDate date, String branchCode) {
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      LocalDate startOfWeek = date.with(TemporalAdjusters.previousOrSame(java.time.DayOfWeek.MONDAY));
      LocalDate endOfWeek = startOfWeek.plusDays(6);

      String hql = "FROM Sale s WHERE s.createdAt >= :startOfWeek AND s.createdAt < :endOfWeek AND s.branch.branchCode = :branchCode";
      LocalDateTime startOfWeekTime = startOfWeek.atStartOfDay();
      LocalDateTime endOfWeekTime = endOfWeek.atTime(23, 59, 59);

      Query<Sale> query = session.createQuery(hql, Sale.class);
      query.setParameter("startOfWeek", startOfWeekTime);
      query.setParameter("endOfWeek", endOfWeekTime);
      query.setParameter("branchCode", branchCode);

      return query.list();
    } catch (Exception e) {
      return null;
    }
  }

  // Method to get all sales for a specific month and branch
  public List<Sale> getSalesForMonth(LocalDate date, String branchCode) {
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      LocalDate firstDayOfMonth = date.withDayOfMonth(1);
      LocalDate lastDayOfMonth = date.withDayOfMonth(date.lengthOfMonth());

      String hql = "FROM Sale s WHERE s.createdAt >= :startOfMonth AND s.createdAt < :endOfMonth AND s.branch.branchCode = :branchCode";
      LocalDateTime startOfMonthTime = firstDayOfMonth.atStartOfDay();
      LocalDateTime endOfMonthTime = lastDayOfMonth.atTime(23, 59, 59);

      Query<Sale> query = session.createQuery(hql, Sale.class);
      query.setParameter("startOfMonth", startOfMonthTime);
      query.setParameter("endOfMonth", endOfMonthTime);
      query.setParameter("branchCode", branchCode);

      return query.list();
    } catch (Exception e) {
      return null;
    }
  }

  // Method to get all sales for a specific year and branch
  public List<Sale> getSalesForYear(int year, String branchCode) {
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      LocalDate startOfYear = LocalDate.of(year, 1, 1);
      LocalDate endOfYear = LocalDate.of(year, 12, 31);

      String hql = "FROM Sale s WHERE s.createdAt >= :startOfYear AND s.createdAt < :endOfYear AND s.branch.branchCode = :branchCode";
      LocalDateTime startOfYearTime = startOfYear.atStartOfDay();
      LocalDateTime endOfYearTime = endOfYear.atTime(23, 59, 59);

      Query<Sale> query = session.createQuery(hql, Sale.class);
      query.setParameter("startOfYear", startOfYearTime);
      query.setParameter("endOfYear", endOfYearTime);
      query.setParameter("branchCode", branchCode);

      return query.list();
    } catch (Exception e) {
      return null;
    }
  }

  // Method to get all sales for a specific date range and branch
  public List<Sale> getSalesForDateRange(LocalDate startDate, LocalDate endDate, String branchCode) {
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      String hql = "FROM Sale s WHERE s.createdAt >= :startDate AND s.createdAt <= :endDate AND s.branch.branchCode = :branchCode";
      LocalDateTime startDateTime = startDate.atStartOfDay();
      LocalDateTime endDateTime = endDate.atTime(23, 59, 59);

      Query<Sale> query = session.createQuery(hql, Sale.class);
      query.setParameter("startDate", startDateTime);
      query.setParameter("endDate", endDateTime);
      query.setParameter("branchCode", branchCode);

      return query.list();
    } catch (Exception e) {
      return null;
    }
  }

}
