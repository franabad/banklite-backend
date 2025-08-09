package com.project.auth_service.exceptions;

import lombok.Getter;

@Getter
public class ExternalServiceException extends RuntimeException {
    private final ErrorDTO errorDTO;

    public ExternalServiceException(ErrorDTO errorDTO) {
        super(errorDTO.message());
        this.errorDTO = errorDTO;
    }
}

