package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<?> handelUserNotFoundException(UserNotFoundException e) {
        ErrorResponseModel errorResponseModel = new ErrorResponseModel();
        errorResponseModel.setMessage(e.getMessage());
        errorResponseModel.setErrorCode(HttpStatus.NOT_FOUND.value());
        errorResponseModel.setErrorTime(System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.OK).body(errorResponseModel);
    }
}
