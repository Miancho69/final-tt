package com.talento_tech.proyecto_final.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDate;

@ControllerAdvice
public class ExceptionsController {

    @ExceptionHandler(IngresoInvalidoException.class)
    public ResponseEntity<Error> handleNotFoundException(IngresoInvalidoException ex) {
        var error = new Error(ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
