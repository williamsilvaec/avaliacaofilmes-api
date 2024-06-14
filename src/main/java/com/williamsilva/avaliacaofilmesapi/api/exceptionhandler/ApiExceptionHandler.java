package com.williamsilva.avaliacaofilmesapi.api.exceptionhandler;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Problem other(Exception e, HttpServletRequest request) {
        String path = request.getRequestURI();
        return new Problem(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), e.getMessage(), path);
    }
}
