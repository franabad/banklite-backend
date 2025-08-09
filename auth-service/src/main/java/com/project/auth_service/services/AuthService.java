package com.project.auth_service.services;

import com.project.auth_service.model.AuthResponse;
import com.project.auth_service.model.LoginRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthService {

    AuthResponse login(LoginRequest loginRequest, HttpServletResponse response);

    boolean checkSession(HttpServletRequest request);
}
