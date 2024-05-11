package com.mycompany.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.mycompany.models.Patient;
import com.mycompany.models.PatientToken;
import com.mycompany.utils.HibernateUtil;

public class PatientTokenDAO {
	public boolean createPatientToken(PatientToken patientToken) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            
            session.save(patientToken);
            
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

	public PatientToken getPatientTokenByToken(String token) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM PatientToken WHERE token = :token", PatientToken.class)
                    .setParameter("token", token)
                    .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
	}

	public Boolean deletePatientToken(PatientToken patientToken) {
	    Transaction transaction = null;
	    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	        transaction = session.beginTransaction();
	        
	        session.delete(patientToken);
	        
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
