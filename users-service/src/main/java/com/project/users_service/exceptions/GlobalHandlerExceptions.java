package com.project.users_service.exceptions;


import com.project.users_service.config.ErrorDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class GlobalHandlerExceptions {

    @ExceptionHandler(UserNullException.class)
    public ResponseEntity<ErrorDTO> handleUserNullException(UserNullException e) {
        return ResponseEntity.status(NOT_FOUND).body(new ErrorDTO(
                LocalDateTime.now().toString(),
                NOT_FOUND.value(),
                NOT_FOUND.getReasonPhrase(),
                e.getMessage(),
                "/api/v1/users"
        ));
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorDTO> handleUserAlreadyExistsException(UserAlreadyExistsException e) {
        return ResponseEntity.status(CONFLICT).body(new ErrorDTO(
                LocalDateTime.now().toString(),
                CONFLICT.value(),
                CONFLICT.getReasonPhrase(),
                e.getMessage(),
                "/api/v1/user"
        ));
    }

    @ExceptionHandler(PasswordInvalidException.class)
    public ResponseEntity<ErrorDTO> handlePasswordInvalidException(PasswordInvalidException e) {
        return ResponseEntity.status(UNAUTHORIZED).body(new ErrorDTO(
                LocalDateTime.now().toString(),
                UNAUTHORIZED.value(),
                UNAUTHORIZED.getReasonPhrase(),
                e.getMessage(),
                "/api/v1/user/validate"
        ));
    }
}
