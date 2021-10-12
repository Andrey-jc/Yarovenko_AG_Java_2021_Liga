package com.example.liquibasedemo.dto;

import com.example.liquibasedemo.entity.User;

public class PostUserDTO {

    private int id;

    private String content;

    private User user;

    public PostUserDTO() {
    }

    public String getUserId() {
        return user.getFirstName() + " " + user.getLastName();
    }

    public void setUserId(User userId) {
        this.user = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
