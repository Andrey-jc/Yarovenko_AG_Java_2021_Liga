package com.example.liquibasedemo.services;

import com.example.liquibasedemo.entity.PostUser;
import com.example.liquibasedemo.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class DeleteUser {

    public static void deleteUser() {
        Transaction transaction = null;
        try (Session session = Factory.getFactory().getCurrentSession()) {

//          старт транзакции
            transaction = session.beginTransaction();
            User user1 = session.get(User.class, 1);
            session.delete(user1);
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
