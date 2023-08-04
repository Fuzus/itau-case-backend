package br.com.itau.casebackendjr.infra.exceptions;

import br.com.itau.casebackendjr.CaseBackendJrApplication;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorsHandler {

    private static Logger logger = LoggerFactory.getLogger(CaseBackendJrApplication.class);

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity handleErrorNotFound(EntityNotFoundException exception){
        logger.error("404 Recurso não encontrado: " + exception.getMessage());
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleErrorBadRequest(MethodArgumentNotValidException exception){
        var errors = exception.getFieldErrors();
        logger.error("Erro 400 Bad request: " + errors.stream().map(BadRequestValidationErrorDTO::new).toList());
        return ResponseEntity.badRequest().body(errors.stream().map(BadRequestValidationErrorDTO::new).toList());
    }


}
