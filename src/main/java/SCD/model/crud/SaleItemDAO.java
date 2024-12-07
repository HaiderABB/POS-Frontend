package SCD.model.crud;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import SCD.model.models.SaleItem;
import SCD.utils.HibernateUtil;

public class SaleItemDAO {

  private static SaleItemDAO instance;

  private SaleItemDAO() {
  }

  @SuppressWarnings("DoubleCheckedLocking")
  public static SaleItemDAO getInstance() {
    if (instance == null) {
      synchronized (SaleItemDAO.class) {
        if (instance == null) {
          instance = new SaleItemDAO();
        }
      }
    }
    return instance;
  }

  public boolean addSaleItems(List<SaleItem> saleItems) {
    boolean result = false;
    Transaction transaction = null;

    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      transaction = session.beginTransaction();

      // Iterate through each SaleItem in the list and persist them
      for (SaleItem saleItem : saleItems) {
        session.persist(saleItem);
      }

      transaction.commit();
      result = true;
    } catch (Exception e) {
      if (transaction != null) {
        transaction.rollback();
      }
    }

    return result;
  }

}
