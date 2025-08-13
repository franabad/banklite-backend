package com.project.auth_service.controller;

import com.project.auth_service.model.AuthResponse;
import com.project.auth_service.model.LoginRequest;
import com.project.auth_service.services.AuthServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/v1/")
public class AuthController {

    private final AuthServiceImpl authService;

    public AuthController(AuthServiceImpl authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        return ResponseEntity.status(OK).body(authService.login(loginRequest, response)
        );
    }

    @GetMapping("/check-session")
    public ResponseEntity<Boolean> checkSession(HttpServletRequest request) {
        return ResponseEntity.status(OK).body(authService.checkSession(request));
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody LoginRequest registerRequest) {
        //authService.register(registerRequest);
        return ResponseEntity.status(CREATED).body("User registered successfully");
    }
}
