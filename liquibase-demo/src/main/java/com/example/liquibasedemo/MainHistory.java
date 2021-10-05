package com.example.liquibasedemo;

import com.example.liquibasedemo.services.*;

public class MainHistory {
    public static void main(String[] args) {
        AddUserAndSchool.createUserAndSchool();
        AddPost.addPost();
        AddFriend.addFriends();
        DeleteUser.deleteUser();
        DeleteSchool.deleteSchool();
    }
}
