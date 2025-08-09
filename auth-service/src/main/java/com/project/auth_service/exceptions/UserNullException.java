package com.project.auth_service.exceptions;

public class UserNullException extends RuntimeException {
    public UserNullException(String message) {
        super(message);
    }
}
