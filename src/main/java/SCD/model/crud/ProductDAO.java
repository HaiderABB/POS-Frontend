package SCD.model.crud;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import SCD.model.models.Product;
import SCD.utils.HibernateUtil;

public class ProductDAO {

  public boolean addProduct(Product product) {
    boolean result = false;
    Transaction transaction = null;
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      transaction = session.beginTransaction();
      session.persist(product);
      transaction.commit();
      result = true;
    } catch (Exception e) {
      if (transaction != null) {
        transaction.rollback();
      }
    }
    return result;
  }

  public Product getProductByCode(String productCode) {
    Product product = null;
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      product = session.get(Product.class, productCode);
    } catch (Exception e) {
    }
    return product;
  }

  public List<Product> getAllProducts() {
    List<Product> products = null;
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      products = session.createQuery("from Product", Product.class).list();
    } catch (Exception e) {
    }
    return products;
  }

  public boolean deleteProduct(String productCode) {
    boolean result = false;
    Transaction transaction = null;
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      transaction = session.beginTransaction();
      Product product = session.get(Product.class, productCode);
      if (product != null) {
        session.remove(product);
        transaction.commit();
        result = true;
      }
    } catch (Exception e) {
      if (transaction != null) {
        transaction.rollback();
      }
      return false;
    }
    return result;
  }

  public List<Product> getAllProductsSortedByCode() {
    List<Product> products = null;
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      products = session.createQuery("from Product p order by p.productCode asc", Product.class).list();
    } catch (Exception e) {

    }
    return products;
  }

}
