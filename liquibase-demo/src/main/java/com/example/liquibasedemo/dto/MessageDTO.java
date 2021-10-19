package com.example.liquibasedemo.dto;

import com.example.liquibasedemo.entity.Message;

public class MessageDTO {

    private int id;

    private String name;

    private String context;

    public MessageDTO(Message message) {
        this.id = message.getId();
        this.name = getName();
        this.context = message.getContent();
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

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}
