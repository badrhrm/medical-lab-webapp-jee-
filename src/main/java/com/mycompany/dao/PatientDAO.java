package com.mycompany.dao;

import com.mycompany.models.Patient;
import com.mycompany.utils.HibernateUtil;

import java.util.List;

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

    public boolean deletePatient(Patient patient) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            
            session.delete(patient);
            
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

}

