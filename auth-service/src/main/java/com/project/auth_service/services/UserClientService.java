package com.project.auth_service.services;

import com.project.auth_service.exceptions.ErrorDTO;
import com.project.auth_service.exceptions.ExternalServiceException;
import com.project.auth_service.exceptions.PasswordInvalidException;
import com.project.auth_service.model.LoginRequest;
import com.project.auth_service.model.UserDTO;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.ErrorResponse;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
public class UserClientService {

    private final WebClient webClient;

    public UserClientService(WebClient webClient) {
        this.webClient = webClient;
    }

    public UserDTO validateUser(LoginRequest loginRequest) {
        try {
            return webClient.post()
                    .uri("/api/v1/user/validate")
                    .body(BodyInserters.fromValue(loginRequest))
                    .retrieve()
                    .onStatus(HttpStatusCode::isError, response ->
                            response.bodyToMono(ErrorDTO.class)
                                    .flatMap(errorBody -> Mono.error(new ExternalServiceException(errorBody)
                                            )
                                    )
                    )
                    .bodyToMono(UserDTO.class)
                    .block();
        } catch (WebClientRequestException e) {
            ErrorDTO errorDTO = new ErrorDTO(
                    LocalDateTime.now().toString(),
                    503,
                    "Service Unavailable",
                    "User service is currently unreachable",
                    "/api/v1/user/validate"
            );
            throw new ExternalServiceException(errorDTO);
        }
    }

    public UserDTO getUserByNif(String nif) {
        try {
            return webClient.get()
                    .uri("/api/v1/user/{nif}", nif)
                    .retrieve()
                    .onStatus(HttpStatusCode::isError, response ->
                            response.bodyToMono(ErrorDTO.class)
                                    .flatMap(errorBody -> Mono.error(new ExternalServiceException(errorBody)))
                    )
                    .bodyToMono(UserDTO.class)
                    .block();
        } catch (WebClientRequestException e) {
            ErrorDTO errorDTO = new ErrorDTO(
                    LocalDateTime.now().toString(),
                    503,
                    "Service Unavailable",
                    "User service is currently unreachable",
                    "/api/v1/user/" + nif
            );
            throw new ExternalServiceException(errorDTO);
        }
    }
}
