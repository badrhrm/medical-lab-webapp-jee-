package com.mycompany.dao;

import com.mycompany.models.Test;
import com.mycompany.utils.HibernateUtil;
import java.util.Collections;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

/**
 *
 * @author ELyazid
 */

/**
 * Data Access Object (DAO) for Test entity.
 * Handles database operations related to Test entity.
 */
public class TestDAO {
    //Private helper method to begin a transaction
    private void beginTransaction(Session session) {
        session.beginTransaction();
    }

    // Private helper method to commit a transaction
    private void commitTransaction(Session session) {
        session.getTransaction().commit();
    }

    // Saves a new test to the database
    public void saveTest(Test test) {
        String sql = "INSERT INTO tests_jee (label, price,duration, days_to_get_result, description) "
                + "VALUES (:label, :price, :duration, :daysToGetResult, :description)";

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            beginTransaction(session);

            Query query = session.createNativeQuery(sql)
                    .setParameter("label", test.getLabel())
                    .setParameter("price", test.getPrice())
                    .setParameter("duration", test.getDuration())
                    .setParameter("daysToGetResult", test.getDaysToGetResult())
                    .setParameter("description", test.getDescription());

            query.executeUpdate();

            commitTransaction(session);
        } catch (Exception e) {
            // Handle exception
            e.printStackTrace();
        }
    }

   // Updates an existing test in the database
    public void updateTest(Test test) {
        String sql = "UPDATE tests_jee SET label = :label, price = :price, duration = :duration, "
                + "days_to_get_result = :daysToGetResult, description = :description "
                + "WHERE id = :id";

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            beginTransaction(session);

            Query query = session.createNativeQuery(sql)
                    .setParameter("label", test.getLabel())
                    .setParameter("price", test.getPrice())
                    .setParameter("duration", test.getDuration())
                    .setParameter("daysToGetResult", test.getDaysToGetResult())
                    .setParameter("description", test.getDescription())
                    .setParameter("id", test.getId());
            query.executeUpdate();

            commitTransaction(session);
        } catch (Exception e) {
            // Handle exception
            e.printStackTrace();
        }
    }
    

    // Deletes a test from the database
    public void deleteTest(Test test) {
        String sql = "DELETE FROM tests_jee WHERE id = :id";

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            beginTransaction(session);

            Query query = session.createNativeQuery(sql)
                    .setParameter("id", test.getId());
            query.executeUpdate();

            commitTransaction(session);
        } catch (Exception e) {
            // Handle exception
            e.printStackTrace();
        }
    }
// Retrieves a test by its ID from the database
    public Test getTestById(int id) {
        String sql = "SELECT * FROM tests_jee WHERE id = :id";

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            beginTransaction(session);

            Query<Test> query = session.createNativeQuery(sql, Test.class)
                    .setParameter("id", id);
            Test test = query.uniqueResult();

            commitTransaction(session);
            return test;
        } catch (Exception e) {
            // Handle exception
            e.printStackTrace();
            return null;
        }
    }
    
    // Retrieves all tests from the database
    public List<Test> getAllTests() {
        String sql = "SELECT * FROM tests_jee order by created_at DESC";

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            beginTransaction(session);

            Query query = session.createNativeQuery(sql, Test.class);
            List<Test> tests = query.getResultList();

            commitTransaction(session);
            return tests;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
// Searches for tests based on a keyword
    public List<Test> searchTests(String keyword) {
        String sql = "FROM Test t WHERE CAST(t.id AS string) LIKE :keyword OR t.description LIKE :keyword OR CAST(t.price AS string) LIKE :keyword";

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            beginTransaction(session);

            Query<Test> query = session.createQuery(sql, Test.class)
                    .setParameter("keyword", "%" + keyword + "%");
            List<Test> tests = query.getResultList();

            commitTransaction(session);
            return tests;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    // Retrieves today's tests along with their counts
    public List<Object[]> getTodaysTestsAndCounts() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();

            String sqlQuery = "SELECT t.label AS test_label, COUNT(*) AS test_count " +
                          "FROM tests_jee t " +
                          "JOIN appointments a ON t.id = a.test_id " +
                          "WHERE DATE(a.day) = CURDATE() " +
                          "AND a.state = 'pending' " + // added this one recently so test table wont show ended appoitments
                          "GROUP BY t.label";

            NativeQuery<Object[]> query = session.createNativeQuery(sqlQuery);
            List<Object[]> results = query.getResultList();

            session.getTransaction().commit();

            return results;
        } catch (Exception e) {
            return null;
        }
    }

}