package com.example.liquibasedemo.services;

import com.example.liquibasedemo.entity.School;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class DeleteSchool {
    public static void deleteSchool() {
        Transaction transaction = null;
        try (Session session = Factory.getFactory().getCurrentSession()) {

//          старт транзакции
            transaction = session.beginTransaction();
            School school = session.get(School.class, 2);
            session.delete(school);
//          комит транзакции
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}
