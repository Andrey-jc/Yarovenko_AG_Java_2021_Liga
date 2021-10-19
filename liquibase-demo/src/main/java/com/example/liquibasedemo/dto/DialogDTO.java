package com.example.liquibasedemo.dto;

import com.example.liquibasedemo.entity.Dialog;

public class DialogDTO {

    private int id;

    private String userForName;

    private String userToName;

    public DialogDTO(Dialog dialog) {
        this.id = dialog.getId();
        this.userForName = dialog.getUserFor().getFirstName() + " " +
                dialog.getUserFor().getLastName();
        this.userToName = dialog.getUserTo().getFirstName() + " " +
                dialog.getUserTo().getLastName();;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserForName() {
        return userForName;
    }

    public void setUserForName(String userForName) {
        this.userForName = userForName;
    }

    public String getUserToName() {
        return userToName;
    }

    public void setUserToName(String userToName) {
        this.userToName = userToName;
    }
}
