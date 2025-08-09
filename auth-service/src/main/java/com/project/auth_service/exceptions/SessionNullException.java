package com.project.auth_service.exceptions;

public class SessionNullException extends RuntimeException {
    public SessionNullException(String message) {
        super(message);
    }
}
