package com.example.liquibasedemo.services;

import com.example.liquibasedemo.entity.PostUser;
import com.example.liquibasedemo.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class AddPost {

    public static void addPost() {
        Transaction transaction = null;
        try (Session session = Factory.getFactory().getCurrentSession()) {

            PostUser post = new PostUser("Hello world!");
            PostUser post2 = new PostUser("Hello all!");
            PostUser post3 = new PostUser("Hello people!");
//          старт транзакции
            transaction = session.beginTransaction();
            User user1 = session.get(User.class, 1);
            User user2 = session.get(User.class, 2);

            user1.addPostToUser(post);
            user1.addPostToUser(post2);
            user2.addPostToUser(post3);

            session.persist(post);
            session.persist(post2);
            session.persist(post3);
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
