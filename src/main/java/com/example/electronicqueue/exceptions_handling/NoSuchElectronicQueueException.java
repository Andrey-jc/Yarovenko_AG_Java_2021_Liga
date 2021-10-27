package com.example.electronicqueue.exceptions_handling;

public class NoSuchElectronicQueueException extends RuntimeException {
    public NoSuchElectronicQueueException(String message) {
        super(message);
    }
}
