package com.project.auth_service.services;

import com.project.auth_service.exceptions.InvalidTokenException;
import com.project.auth_service.exceptions.SessionNullException;
import com.project.auth_service.jwt.JwtService;
import com.project.auth_service.model.AuthResponse;
import com.project.auth_service.model.LoginRequest;
import com.project.auth_service.model.UserDTO;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;


@Service
public class AuthServiceImpl implements AuthService {

    private final UserClientService userClientService;
    private final JwtService jwtService;

    public AuthServiceImpl(UserClientService userClientService, JwtService jwtService) {
        this.userClientService = userClientService;
        this.jwtService = jwtService;
    }

    @Override
    public AuthResponse login(LoginRequest loginRequest, HttpServletResponse response) {
        UserDTO user = userClientService.validateUser(loginRequest);

        String accessToken = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        setTokenCookies(response, accessToken, refreshToken);

        return new AuthResponse(user.getNif(), accessToken, refreshToken);
    }

    @Override
    public boolean checkSession(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("sessionid".equals(cookie.getName())) {
                    String sessionId = cookie.getValue();

                    try {
                        String userNif = jwtService.getNifFromToken(cookie.getValue());
                        UserDTO user = userClientService.getUserByNif(userNif);

                        if (!jwtService.isTokenValid(sessionId, user) || jwtService.isTokenExpired(sessionId)) {
                            throw new InvalidTokenException("Session cookie is invalid or expired");
                        }

                        return true;
                    }
                    catch (JwtException | IllegalArgumentException e) {
                            throw new InvalidTokenException("Malformed or forged token");
                    }
                }
            }
        }
        throw new SessionNullException("Session cookie is null or not found");
    }

    private void setTokenCookies(HttpServletResponse response, String sessionId, String refreshSession) {

        Cookie sessionIdCookie = new Cookie("sessionid", sessionId);
        sessionIdCookie.setHttpOnly(true);
        sessionIdCookie.setSecure(false); // WARNING - Change this to true in production
        sessionIdCookie.setPath("/");
        sessionIdCookie.setAttribute("sameSite", "strict");
        sessionIdCookie.setMaxAge(60 * 15); // 15 minutes

        Cookie refreshSessionCookie = new Cookie("refreshsession", refreshSession);
        refreshSessionCookie.setHttpOnly(true);
        refreshSessionCookie.setSecure(false); // WARNING - Change this to true in production
        refreshSessionCookie.setPath("/");
        refreshSessionCookie.setAttribute("sameSite", "strict");
        refreshSessionCookie.setMaxAge(60 * 60 * 24 * 7); // 7 days
        response.addCookie(sessionIdCookie);
        response.addCookie(refreshSessionCookie);
    }
}
