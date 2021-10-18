package com.example.liquibasedemo.dto;

import com.example.liquibasedemo.entity.PostUser;
import com.example.liquibasedemo.entity.School;
import com.example.liquibasedemo.entity.User;

import java.util.List;

public class UserFullInfoDTO {
    private int id;

    private String firstName;

    private String lastName;

    private int age;

    private String gender;

    private String email;

    private School school;

    private List<PostUser> postUserList;

    private List<User> friends;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public List<PostUser> getPostUserList() {
        return postUserList;
    }

    public void setPostUserList(List<PostUser> postUserList) {
        this.postUserList = postUserList;
    }

    public List<User> getFriends() {
        return friends;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }
}
