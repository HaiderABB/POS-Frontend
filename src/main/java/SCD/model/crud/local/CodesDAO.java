
package SCD.model.crud.local;

import org.hibernate.Session;
import org.hibernate.Transaction;

import SCD.model.models.Codes;
import SCD.utils.HibernateUtil;

public class CodesDAO {

  private static CodesDAO instance;

  private CodesDAO() {
  }

  @SuppressWarnings("DoubleCheckedLocking")
  public static CodesDAO getInstance() {
    if (instance == null) {
      synchronized (CodesDAO.class) {
        if (instance == null) {
          instance = new CodesDAO();
        }
      }
    }
    return instance;
  }

  public boolean addCode(Codes code) {
    Transaction transaction = null;
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      transaction = session.beginTransaction();

      session.persist(code);

      transaction.commit();
      return true;
    } catch (Exception e) {
      if (transaction != null) {
        transaction.rollback();
      }
      return false;
    }
  }

  public String getCodeByTableName(String tableName) {
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      Codes codeRecord = session
          .createQuery("FROM Codes WHERE tableName = :tableName", Codes.class)
          .setParameter("tableName", tableName)
          .uniqueResult();
      return codeRecord != null ? codeRecord.getCode() : null;
    } catch (Exception e) {
      return null;
    }
  }

  public boolean updateCodeByTableName(String tableName, String newCode) {
    Transaction transaction = null;
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      transaction = session.beginTransaction();

      Codes codeRecord = session
          .createQuery("FROM Codes WHERE tableName = :tableName", Codes.class)
          .setParameter("tableName", tableName)
          .uniqueResult();

      if (codeRecord != null) {
        codeRecord.setCode(newCode);
        session.merge(codeRecord);
        transaction.commit();
        return true;
      }
      return false;
    } catch (Exception e) {
      if (transaction != null) {
        transaction.rollback();
      }
      return false;
    }
  }

}
