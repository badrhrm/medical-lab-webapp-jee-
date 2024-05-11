package com.mycompany.dao;

import com.mycompany.models.Patient;
import com.mycompany.utils.HibernateUtil;

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
}

