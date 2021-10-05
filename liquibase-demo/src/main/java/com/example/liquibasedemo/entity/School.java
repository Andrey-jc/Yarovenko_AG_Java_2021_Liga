package com.example.liquibasedemo.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "SCHOOL")
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private int id;

    @Column(name = "name", length = 25, unique = true)
    private String name;

    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.PERSIST,
            CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "school_id")
    private List<User> userList;

    public School() {
    }

    public School(String name) {
        this.name = name;
    }
    
    @PreRemove
    private void preRemove() {
        for (User user:
             userList) {
            user.setSchool(null);
        }
    }

    /**
     * ПОзволяет каскадом добавлять в список пользователя
     * и школу для пользователя
     *
     * @param user школа пользователя
     */
    public void addUserToSchool(User user) {
        if (userList == null) {
            userList = new ArrayList<>();
        }
        userList.add(user);
        if (user.getSchool() == null) {
            user.setSchool(this);
        }
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "School{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
