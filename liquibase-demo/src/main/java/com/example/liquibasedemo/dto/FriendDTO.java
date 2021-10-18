package com.example.liquibasedemo.dto;

import com.example.liquibasedemo.entity.Friend;

public class FriendDTO {

    private String firstName;

    private String lastName;

    private int age;

    private String gender;

    private String email;

    public FriendDTO(Friend friend) {
        this.firstName = friend.getFriend().getFirstName();
        this.lastName = friend.getFriend().getLastName();
        this.age = friend.getFriend().getAge();
        this.gender = friend.getFriend().getGender();
        this.email = friend.getFriend().getEmail();
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
}
