package com.example.liquibasedemo.services;

import com.example.liquibasedemo.entity.PostUser;
import com.example.liquibasedemo.entity.School;
import com.example.liquibasedemo.entity.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Factory {
    public static SessionFactory getFactory() {
        return new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(PostUser.class)
                .addAnnotatedClass(School.class)
                .addAnnotatedClass(User.class)
                .buildSessionFactory();
    }
}
