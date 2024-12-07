package SCD.utils;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import SCD.model.models.Branch;

public class HibernateUtil {

  private static final SessionFactory sessionFactory;

  static {
    try {
      // Create the SessionFactory using Hibernate Configuration
      sessionFactory = new Configuration()
          .configure("hibernate.cfg.xml") // This file contains Hibernate settings
          .addAnnotatedClass(Branch.class) // Add your annotated entity classes here
          .buildSessionFactory();
    } catch (HibernateException ex) {
      System.err.println("Initial SessionFactory creation failed." + ex);
      throw new ExceptionInInitializerError(ex);
    }
  }

  public static SessionFactory getSessionFactory() {
    return sessionFactory;
  }

  public static void shutdown() {
    // Close the SessionFactory when the application terminates
    getSessionFactory().close();
  }

  public static void main(String[] args) {
    // Attempt to get the SessionFactory
    try {
      SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

      if (sessionFactory != null) {
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