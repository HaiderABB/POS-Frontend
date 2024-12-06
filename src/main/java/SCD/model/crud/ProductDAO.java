package SCD.model.crud;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import SCD.model.models.Product;
import SCD.utils.HibernateUtil;

public class ProductDAO {

  private static ProductDAO instance;

  private ProductDAO() {
  }

  public static ProductDAO getInstance() {
    if (instance == null) {
      synchronized (ProductDAO.class) {
        if (instance == null) {
          instance = new ProductDAO();
        }
      }
    }
    return instance;
  }

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

  public Product getActiveProductByCode(String productCode) {
    Product product = null;
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      String hql = "FROM Product WHERE productCode = :productCode AND isActive = true";
      product = session.createQuery(hql, Product.class)
          .setParameter("productCode", productCode)
          .uniqueResult();
    } catch (Exception e) {
    }
    return product;
  }

  public List<Product> getAllActiveProducts() {
    List<Product> products = null;
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      products = session.createQuery("from Product where isActive = true", Product.class).list();
    } catch (Exception e) {

    }
    return products;
  }

  public boolean deactivateProduct(String productCode) {
    boolean result = false;
    Transaction transaction = null;
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      transaction = session.beginTransaction();

      Product product = session.get(Product.class, productCode);

      if (product != null) {
        product.setActive(false);
        session.merge(product);

        transaction.commit();
        result = true;

        System.out.println("Product " + productCode + " deactivated successfully.");
      } else {
        System.out.println("Product not found with code: " + productCode);
      }
    } catch (Exception e) {
      if (transaction != null) {
        transaction.rollback();
      }
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

  public int getStockQuantity(String productCode) {
    int stockQuantity = 0;
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      String hql = "SELECT p.stockQuantity FROM Product p WHERE p.productCode = :productCode AND p.isActive = true";
      stockQuantity = session.createQuery(hql, Integer.class)
          .setParameter("productCode", productCode)
          .uniqueResultOptional()
          .orElse(0);
    } catch (Exception e) {
    }
    return stockQuantity;
  }

  public Product getProductByCode(String productCode) {
    Product product = null;
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      String hql = "FROM Product p WHERE p.productCode = :productCode AND p.isActive = true";
      product = session.createQuery(hql, Product.class)
          .setParameter("productCode", productCode)
          .uniqueResultOptional()
          .orElse(null);
    } catch (Exception e) {
    }
    return product;
  }

  public boolean decrementStockQuantity(String productCode, int quantityToDecrement) {
    boolean result = false;
    Transaction transaction = null;

    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      transaction = session.beginTransaction();

      Product product = session.get(Product.class, productCode);

      if (product != null && product.isActive()) {
        int currentStock = product.getStockQuantity();

        if (currentStock >= quantityToDecrement) {
          product.setStockQuantity(currentStock - quantityToDecrement);
          session.merge(product);

          transaction.commit();
          result = true;

          System.out.println("Stock decremented for product: " + productCode +
              ". New stock: " + product.getStockQuantity());
        } else {
          System.out.println("Insufficient stock for product: " + productCode);
        }
      } else {
        System.out.println("Product not found or inactive with code: " + productCode);
      }
    } catch (Exception e) {
      if (transaction != null) {
        transaction.rollback();
      }
    }

    return result;
  }

}
