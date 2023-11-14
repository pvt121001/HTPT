package com.example.htpt.exception;

public class StorageFileNotFoundException extends Throwable {
    public StorageFileNotFoundException(String message) {
        super();
    }
    public StorageFileNotFoundException(String message, Exception e) {
        super(message, e);
    }
}
