package com.example.liquibasedemo.entity;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "POSTS_USERS")
public class PostUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.PERSIST,
            CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "user_id")
    private User userId;

    @Column(name = "text", length = 2000)
    private String context;

    public PostUser() {
    }

    public PostUser(String context) {
        this.context = context;
    }

    /**
     * метод добовляет посты пользователей
     * и связывает их
     * @param user пользователь с которым необходимо связать
     */
    public void addPostUser(User user) {
        if (user.getPostUserList() == null) {
            user.setPostUserList(new ArrayList<>());
        }
        user.getPostUserList().add(this);
        this.setUserId(user);
    }

    public User getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}
