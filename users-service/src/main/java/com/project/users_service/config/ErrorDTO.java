package com.project.users_service.config;

public record ErrorDTO(String timestamp, Integer status, String error, String message, String path) {
}
