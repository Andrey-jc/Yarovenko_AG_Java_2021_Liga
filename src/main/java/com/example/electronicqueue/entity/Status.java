package com.example.electronicqueue.entity;

public enum Status {
    OPEN("Open"), ARRIVED("Arrived"),
    TIMEOUT("TimeOut"), DONE("Done"),
    ACCEPT("Accept");

    private String name;

    Status(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
