package com.example.liquibasedemo.services;

import com.example.liquibasedemo.entity.School;
import com.example.liquibasedemo.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class AddUserAndSchool {

    public static void createUserAndSchool() {

        Transaction transaction = null;
        try (Session session = Factory.getFactory().getCurrentSession()) {

            School school = new School("IBM-school");
            School school2 = new School("liga-2021");

            User user = new User(
                    "Andrey",
                    "Yarovenko",
                    22,
                    "male",
                    "stuffingeu56@gmail.com");
            User user2 = new User("Alina",
                    "Yarovenko",
                    20,
                    "female",
                    "alina@gmail.com");
            User user3 = new User("Oleg",
                    "Skripichnikov",
                    22,
                    "male",
                    "oleg@gmail.com");
            User user4 = new User("Jenia",
                    "Mihalkov",
                    22,
                    "male",
                    "jenia@gmail.com");

            school.addUserToSchool(user);
            school2.addUserToSchool(user2);
            school2.addUserToSchool(user3);
            school2.addUserToSchool(user4);
//          начало транзакции
            transaction = session.beginTransaction();
            session.persist(school);
            session.persist(school2);
//            конец транзакции
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}
