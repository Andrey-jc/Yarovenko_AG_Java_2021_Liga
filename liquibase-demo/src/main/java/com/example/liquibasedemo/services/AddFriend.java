package com.example.liquibasedemo.services;

import com.example.liquibasedemo.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class AddFriend {

    public static void addFriends() {
            Transaction transaction = null;
            try (Session session = Factory.getFactory().getCurrentSession()) {
//                начало транзакции
                transaction = session.beginTransaction();
                User user1 = session.get(User.class, 2);
                User user2 = session.get(User.class, 3);
                User user3 = session.get(User.class, 1);
                user1.addFriendUser(user2);
                user1.addFriendUser(user3);
                user2.addFriendUser(user3);
//                конец транзакции
                transaction.commit();
            } catch (Exception e) {
                e.printStackTrace();
                if (transaction != null) {
                    transaction.rollback();
                }
            }
        }
}
