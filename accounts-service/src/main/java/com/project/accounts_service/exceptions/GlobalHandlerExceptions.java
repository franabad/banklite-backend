package com.project.accounts_service.exceptions;

import static org.springframework.http.HttpStatus.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalHandlerExceptions {

    @ExceptionHandler(AccountNullException.class)
    public ResponseEntity<ErrorDTO> handleAccountNullException(AccountNullException e) {
        return ResponseEntity.status(NOT_FOUND).body(new ErrorDTO(
                LocalDateTime.now().toString(),
                NOT_FOUND.value(),
                NOT_FOUND.getReasonPhrase(),
                e.getMessage(),
                "/api/v1/account/"
        ));
    }

    @ExceptionHandler(InvalidAccountIdException.class)
    public ResponseEntity<ErrorDTO> handleInvalidAccountIdException(InvalidAccountIdException e) {
        return ResponseEntity.status(BAD_REQUEST).body(new ErrorDTO(
                LocalDateTime.now().toString(),
                BAD_REQUEST.value(),
                BAD_REQUEST.getReasonPhrase(),
                e.getMessage(),
                "/api/v1/account/"
        ));
    }
}
