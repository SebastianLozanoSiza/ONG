package com.sebas.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.sebas.demo.common.StandarizedApiExceptionResponse;


@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(BussinesRuleException.class)
    public ResponseEntity<StandarizedApiExceptionResponse> handleBussinesRuleException(BussinesRuleException ex) {
        String errorMessage = "Wrong Validation. " + ex.getMessage();
        StandarizedApiExceptionResponse response = new StandarizedApiExceptionResponse("Error de conexion", ex.getCode(), errorMessage);
        return new ResponseEntity<>(response, HttpStatus.PARTIAL_CONTENT);
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<StandarizedApiExceptionResponse> handleAccessDeniedException(AccessDeniedException ex) {
        String errorMessage = "Wrong Validation. " + ex.getMessage();
        StandarizedApiExceptionResponse response = new StandarizedApiExceptionResponse("Acceso Denegado", String.valueOf(HttpStatus.FORBIDDEN.value()), errorMessage);
        return new ResponseEntity<>(response, HttpStatus.PARTIAL_CONTENT);
    }

}