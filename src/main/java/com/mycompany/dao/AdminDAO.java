package com.mycompany.dao;

import com.mycompany.models.Admin;
import com.mycompany.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class AdminDAO {

    public Admin getAdminByEmail(String email) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Admin WHERE email = :email", Admin.class)
                    .setParameter("email", email)
                    .uniqueResult();
        } catch (Exception e) {
        	System.out.println("admin not found in db from dao");
            e.printStackTrace();
            return null;
        }
    }
    
}

