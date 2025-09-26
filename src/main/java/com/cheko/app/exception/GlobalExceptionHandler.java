package com.cheko.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.cheko.app.dto.ErrorDetailsDto;

import io.swagger.v3.oas.annotations.Hidden;

@ControllerAdvice
@Hidden
public class GlobalExceptionHandler {

    @ExceptionHandler(ChekoException.class)
    public ResponseEntity<?> handleChekoException(ChekoException ex, WebRequest request) {
        ErrorDetailsDto errorDetails = new ErrorDetailsDto(HttpStatus.BAD_REQUEST, ex.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGenericException(Exception ex, WebRequest request) {
        ErrorDetailsDto errorDetails = new ErrorDetailsDto(HttpStatus.INTERNAL_SERVER_ERROR,
                "Internal server error occurred");
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
