package SCD.model.crud.local;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import SCD.model.models.SyncTable;
import SCD.utils.HibernateUtil;

public class SyncTableDAO {

  private static SyncTableDAO instance;

  private SyncTableDAO() {
  }

  @SuppressWarnings("DoubleCheckedLocking")
  public static SyncTableDAO getInstance() {
    if (instance == null) {
      synchronized (SyncTableDAO.class) {
        if (instance == null) {
          instance = new SyncTableDAO();
        }
      }
    }
    return instance;
  }

  public boolean addSyncTable(SyncTable syncTable) {
    Transaction transaction = null;
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      transaction = session.beginTransaction();
      session.persist(syncTable);
      transaction.commit();
      return true;
    } catch (Exception e) {
      if (transaction != null) {
        transaction.rollback();
      }
      return false;
    }
  }

  public SyncTable getSyncTableById(Long id) {
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      return session.get(SyncTable.class, id);
    } catch (Exception e) {
      return null;
    }
  }

  public List<SyncTable> getAllSyncTableEntries() {
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      return session.createQuery("FROM SyncTable", SyncTable.class).list();
    } catch (Exception e) {
      return null;
    }
  }

  public boolean updateSyncTableById(Long id, String tableName, String operationType, String keyColumn) {
    Transaction transaction = null;
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      transaction = session.beginTransaction();
      SyncTable syncTable = session.get(SyncTable.class, id);

      if (syncTable != null) {
        syncTable.setTableName(tableName);
        syncTable.setOperationType(operationType);
        syncTable.setKeyColumn(keyColumn);
        session.merge(syncTable);
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

  public boolean insertSyncTable(SyncTable syncTable) {
    Transaction transaction = null;
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      transaction = session.beginTransaction();

      session.persist(syncTable);

      transaction.commit();
      return true;
    } catch (Exception e) {
      if (transaction != null) {
        transaction.rollback();
      }
      return false;
    }
  }

  public boolean removeAllSyncTableEntries() {
    Transaction transaction = null;
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      transaction = session.beginTransaction();

      String hql = "DELETE FROM SyncTable";
      int result = session.createMutationQuery(hql).executeUpdate();

      transaction.commit();
      return result > 0;
    } catch (Exception e) {
      if (transaction != null) {
        transaction.rollback();
      }
      return false;
    }
  }

  public boolean removeSyncTableById(Long id) {
    Transaction transaction = null;
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      transaction = session.beginTransaction();

      SyncTable syncTable = session.get(SyncTable.class, id);

      if (syncTable != null) {
        session.remove(syncTable);
        transaction.commit();
        return true;
      } else {
        System.out.println("No entry found with id: " + id);
      }
    } catch (Exception e) {
      if (transaction != null) {
        transaction.rollback();
      }
    }
    return false;
  }

}
