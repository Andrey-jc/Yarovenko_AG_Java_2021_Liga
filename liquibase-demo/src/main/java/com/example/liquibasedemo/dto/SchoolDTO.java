package com.example.liquibasedemo.dto;

import java.util.List;

public class SchoolDTO {

    private int id;

    private String name;

    private List<UserForSchoolDTO> userList;

    public SchoolDTO() {
    }

    public List<UserForSchoolDTO> getUserList() {
        return userList;
    }

    public void setUserList(List<UserForSchoolDTO> userList) {
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
