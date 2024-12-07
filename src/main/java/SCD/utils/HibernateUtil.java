package SCD.utils;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import SCD.model.models.Branch;

public class HibernateUtil {

  private static final SessionFactory sessionFactory;
  private static final SessionFactory remoteSessionFactory;

  static {
    try {

      remoteSessionFactory = new Configuration().configure("hibernate-remote.cfg.xml").addAnnotatedClass(Branch.class)
          .buildSessionFactory();

      sessionFactory = new Configuration()
          .configure("hibernate.cfg.xml")
          .addAnnotatedClass(Branch.class)
          .buildSessionFactory();
    } catch (HibernateException ex) {
      System.err.println("Initial SessionFactory creation failed." + ex);
      throw new ExceptionInInitializerError(ex);
    }
  }

  public static SessionFactory getSessionFactory() {
    return sessionFactory;
  }

  public static SessionFactory getRemoteSessionFactory() {
    return remoteSessionFactory;
  }

  public static void shutdown() {
    // Close the SessionFactory when the application terminates
    if (sessionFactory != null) {
      sessionFactory.close();
    }
    if (remoteSessionFactory != null) {
      remoteSessionFactory.close();
    }
  }

  public static void main(String[] args) {
    try {
      SessionFactory session = HibernateUtil.getRemoteSessionFactory();

      if (session != null) {
        System.out.println("SessionFactory successfully created.");
      } else {
        System.err.println("Failed to create SessionFactory.");
      }

      HibernateUtil.shutdown();
      System.out.println("SessionFactory has been shut down.");
    } catch (Exception e) {
      System.err.println("Error occurred while testing HibernateUtil.");
    }
  }
}