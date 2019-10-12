package hibernate;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ebay.eBayRecord;
import org.hibernate.query.Query;

public class HibernateHandler {

  private SessionFactory factory = null;
  private Session session = null;
  private static HibernateHandler single_instance = null;

  public static HibernateHandler getInstance() {
    if (single_instance == null) {
      single_instance = new HibernateHandler();
    }
    System.out.println("Getting Instance");
    return single_instance;
  }

  public HibernateHandler() {
    factory = HibernateUtils.getSessionFactory();
  }

  public eBayRecord getRecord(String searchPhrase) {
    try {
      session = factory.openSession();
      session.getTransaction().begin();
      Query query = session.createQuery("from ebay.eBayRecord where id= :id");
      query.setParameter("id", searchPhrase);
      eBayRecord record = (eBayRecord) query.getSingleResult();
      session.getTransaction().commit();
      return record;
    } catch (Exception e) {
      System.err.println("Error in Test: " + e);
      session.getTransaction().rollback();
      return null;
    } finally {
      session.close();
    }
  }

  public boolean containsRecord(String searchPhrase) {
    try {
      session = factory.openSession();
      Query query = session.createQuery("from  ebay.eBayRecord where id = :id");
      query.setParameter("id", searchPhrase);
      List<?> list = query.list();

      if(list.isEmpty()) {
        return false;
      }
      else {
        return true;
      }


    } catch (Exception e) {
      System.err.println("Error in Test: " + e);
      session.getTransaction().rollback();
      return false;
    } finally {
      session.close();
    }
  }

  public void addRecord(eBayRecord ebayRecord) {
    try {
      session = factory.openSession();
      session.getTransaction().begin();

      session.save(ebayRecord);
      session.getTransaction().commit();
    } catch (Exception e) {
      System.err.println("Error in Test: " + e);
      session.getTransaction().rollback();
    } finally {
      session.close();
    }
  }

  public void updateRecord(eBayRecord ebayRecord) {
    try {
      session = factory.openSession();
      session.getTransaction().begin();

      session.saveOrUpdate(ebayRecord);
      session.getTransaction().commit();
    } catch (Exception e) {
      System.err.println("Error in Test: " + e);
      session.getTransaction().rollback();
    } finally {
      session.close();
    }
  }
}
