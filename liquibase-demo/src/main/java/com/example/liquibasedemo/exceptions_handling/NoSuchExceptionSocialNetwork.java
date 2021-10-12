package com.example.liquibasedemo.exceptions_handling;

public class NoSuchExceptionSocialNetwork extends RuntimeException {
    public NoSuchExceptionSocialNetwork(String message) {
        super(message);
    }
}
