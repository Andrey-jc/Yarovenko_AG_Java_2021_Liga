package com.example.liquibasedemo.dto;

import com.example.liquibasedemo.entity.User;

import java.util.List;

public class FriendDTO {

    private User user;

    private List<User> userList;

    public FriendDTO() {
    }

    public String getUserList() {
        List<User> friendsList = user.getFriends();
        StringBuilder builder = new StringBuilder();
        for (User user : friendsList) {
            builder.append(user.toString()).append("\n");
        }
        return builder.toString();
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public String getUser() {
        return user.getFirstName() + user.getLastName();
    }

    public void setUser(User user) {
        this.user = user;
    }
}
