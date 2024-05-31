package com.send_message.message.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handlerAllException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(
                new ExceptionResponse(
                        e.getMessage(),
                        HttpStatus.INTERNAL_SERVER_ERROR.value()
                )
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handlerMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return ResponseEntity.status(
                HttpStatus.BAD_GATEWAY.value()).body(
                        new ExceptionResponse(e.getBindingResult()
                                .getFieldError()
                                .getDefaultMessage(), HttpStatus.BAD_GATEWAY.value()));
    }
}
