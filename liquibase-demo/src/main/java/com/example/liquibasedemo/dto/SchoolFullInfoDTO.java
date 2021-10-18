package com.example.liquibasedemo.dto;

import com.example.liquibasedemo.entity.User;

import java.util.List;

public class SchoolFullInfoDTO {

    private int id;

    private String name;

    private List<User> userList;

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

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}
