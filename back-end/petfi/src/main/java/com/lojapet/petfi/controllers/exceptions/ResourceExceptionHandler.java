package com.lojapet.petfi.controllers.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.lojapet.petfi.services.exceptions.DatabaseException;
import com.lojapet.petfi.services.exceptions.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
    String error = "Resource not found";
    Integer status = HttpStatus.NOT_FOUND.value();
    StandardError standardError = new StandardError(Instant.now(), status, error, e.getMessage(),
        request.getRequestURI());
    return ResponseEntity.status(status).body(standardError);
  }

  @ExceptionHandler(DatabaseException.class)
  public ResponseEntity<StandardError> dataBase(DatabaseException e, HttpServletRequest request) {
    String error = "Database error";
    HttpStatus status = HttpStatus.BAD_REQUEST;
    StandardError standardError = new StandardError(Instant.now(), status.value(), error, e.getMessage(),
        request.getRequestURI());
    return ResponseEntity.status(status).body(standardError);
  }
}
