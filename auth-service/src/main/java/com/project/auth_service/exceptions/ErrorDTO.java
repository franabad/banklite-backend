package com.project.auth_service.exceptions;

public record ErrorDTO(String timestamp, Integer status, String error, String message, String path) {
}
