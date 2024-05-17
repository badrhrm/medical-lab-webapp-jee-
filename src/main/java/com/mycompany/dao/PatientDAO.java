package com.mycompany.dao;

import com.mycompany.models.Patient;
import com.mycompany.utils.HibernateUtil;

import java.util.List;

import javax.management.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class PatientDAO {

    public Patient getPatientByEmail(String email) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Patient WHERE email = :email", Patient.class)
                    .setParameter("email", email)
                    .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public Patient getPatientById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Patient.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public boolean createPatient(Patient patient) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            
            session.save(patient);
            
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }
    
    public List<Patient> getAllPatients() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Patient", Patient.class)
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public boolean updatePatient(Patient patient) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            
            session.update(patient);
            
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }
    public void deletePatientById(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Begin a transaction
            transaction = session.beginTransaction();

            // Retrieve the patient by ID
            Patient patient = session.get(Patient.class, id);

            // If the patient exists, delete it
            if (patient != null) {
                session.delete(patient);
                // Commit the transaction
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
//    public boolean deletePatient(Patient patient) {
//        Transaction transaction = null;
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            transaction = session.beginTransaction();
//            
//            session.delete(patient);
//            
//            transaction.commit();
//            return true;
//        } catch (Exception e) {
//            if (transaction != null) {
//                transaction.rollback();
//            }
//            e.printStackTrace();
//            return false;
//        }
//    }
    
    public boolean updatePatientPassword(Patient patient, String newPassword) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            // set the new password in the object
            patient.setPassword(newPassword);

            // update the patient entity
            session.update(patient);

            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }
<<<<<<< HEAD
    
    public Patient getPatientById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Patient WHERE id = :id", Patient.class)
                    .setParameter("id", id)
                    .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public Patient getPatientByCin(String cin) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Patient WHERE cin = :cin", Patient.class)
                    .setParameter("cin", cin)
                    .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
=======

>>>>>>> c4663329ad7e55055516c565b313743c1724f0e6
}

