package com.project.auth_service.exceptions;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class GlobalHandlerExceptions {

//    @ExceptionHandler(UserNullException.class)
//    public ResponseEntity<ErrorDTO> handleUserNullException(UserNullException e) {
//        return ResponseEntity.status(NOT_FOUND).body(new ErrorDTO(e.getMessage()));
//    }
//
    @ExceptionHandler(ExternalServiceException.class)
    public ResponseEntity<ErrorDTO> handleExternalServiceException(ExternalServiceException e) {
        ErrorDTO errorDTO = e.getErrorDTO();
        return ResponseEntity.status(errorDTO.status()).body(errorDTO);
    }

    @ExceptionHandler(SessionNullException.class)
    public ResponseEntity<ErrorDTO> handleSessionNullException(SessionNullException e) {
        return ResponseEntity.status(UNAUTHORIZED).body(new ErrorDTO(
                LocalDateTime.now().toString(),
                UNAUTHORIZED.value(),
                UNAUTHORIZED.getReasonPhrase(),
                e.getMessage(),
                "/api/v1/auth/check-session"
        ));
    }

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<ErrorDTO> handleInvalidTokenException(InvalidTokenException e) {
        return ResponseEntity.status(UNAUTHORIZED).body(new ErrorDTO(
                LocalDateTime.now().toString(),
                UNAUTHORIZED.value(),
                UNAUTHORIZED.getReasonPhrase(),
                e.getMessage(),
                "/api/v1/auth/check-session"
        ));
    }
}
