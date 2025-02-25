package com.devprobootcamp.app.payment_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Author: Edward Tanko <br/>
 * Date: 2/24/25 7:45 PM<br/>
 */
@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(AppValidationException.class)
    public ResponseEntity<ErrorMessage> handleAppValidationException(AppValidationException exception) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setErrormessage(exception.getMessage());

        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
}
