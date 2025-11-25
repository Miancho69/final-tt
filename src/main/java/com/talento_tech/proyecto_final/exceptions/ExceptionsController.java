package com.talento_tech.proyecto_final.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ExceptionsController {

    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<String> handleNotFoundException(InvalidInputException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmptyListException.class)
    public ResponseEntity<String> handleNoDataFound(EmptyListException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidInformationException.class)
    public ResponseEntity<String> handleNoDataFound(InvalidInformationException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(EmptyNameException.class)
    public ResponseEntity<String> handleNoDataFound(EmptyNameException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(InvalidBCRACodeException.class)
    public ResponseEntity<String> handleNoDataFound(InvalidBCRACodeException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(InvalidCuitNumberException.class)
    public ResponseEntity<String> handleNoDataFound(InvalidCuitNumberException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> manejarExcepcionGeneral(Exception e) {
        return new ResponseEntity<>("Ocurrió un error inesperado /n"
                + e.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
