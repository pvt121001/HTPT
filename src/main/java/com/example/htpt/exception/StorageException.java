package com.example.htpt.exception;

public class StorageException extends Throwable {
    public StorageException(String message) {
        super(message);
    }
    public StorageException(String message, Exception e) {
        super(message, e);
    }
}
