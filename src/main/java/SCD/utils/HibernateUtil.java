package SCD.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import SCD.model.entities.Employee;

public class HibernateUtil {

  private static SessionFactory sessionFactory;

  static {
    try {
      sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Employee.class)
          .buildSessionFactory();
    } catch (Exception e) {
      e.printStackTrace();
      throw new ExceptionInInitializerError("SessionFactory initialization failed");
    }
  }

  public static SessionFactory getSessionFactory() {
    return sessionFactory;
  }
}
