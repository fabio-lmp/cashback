package br.com.fabioporto.cashback.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.fabioporto.cashback.entity.ErrorResponse;
import br.com.fabioporto.cashback.exception.UserExistsExcetion;

@ControllerAdvice
public class ExceptionHandlerController {
    
    @ExceptionHandler(HttpMessageNotReadableException.class)
    private ResponseEntity<ErrorResponse> httpMNRException(HttpMessageNotReadableException e) {
        
        return ResponseEntity.status(HttpStatus.valueOf(400))
            .body(new br.com.fabioporto.cashback.entity.ErrorResponse(e.getMessage(), "erro"));
    } 

    @ExceptionHandler(UserExistsExcetion.class)
    private ResponseEntity<ErrorResponse> UserExistsExcetionHandler(UserExistsExcetion e) {
        
        return ResponseEntity.status(HttpStatus.valueOf(400))
            .body(new br.com.fabioporto.cashback.entity.ErrorResponse(e.getMessage(), "erro"));
    } 

    
}
