package com.example.liquibasedemo.dto;

import com.example.liquibasedemo.entity.User;

import java.util.List;

public class SchoolDTO {

    private int id;

    private String name;

    private List<User> userList;

    public SchoolDTO() {
    }

    public String getUserList() {
        StringBuilder builder = new StringBuilder();
        for (User user : userList) {
            builder.append(user.toString()).append("\n");
        }
        return builder.toString();
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
}
