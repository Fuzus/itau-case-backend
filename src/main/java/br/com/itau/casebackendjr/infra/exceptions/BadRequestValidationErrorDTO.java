package br.com.itau.casebackendjr.infra.exceptions;

import org.springframework.validation.FieldError;

public record BadRequestValidationErrorDTO(String field, String message) {
    public BadRequestValidationErrorDTO(FieldError fieldError){
        this(fieldError.getField(), fieldError.getDefaultMessage());
    }
}
