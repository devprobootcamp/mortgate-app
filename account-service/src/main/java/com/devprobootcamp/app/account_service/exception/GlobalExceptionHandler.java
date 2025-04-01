package com.devprobootcamp.app.account_service.exception;

import org.springframework.http.*;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Author: Edward Tanko <br/>
 * Date: 3/29/25 7:48 AM<br/>
 */
@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(value = {MissingRequestHeaderException.class})
    public ResponseEntity<ProblemDetail> handleMissingParams(Exception exception) {

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(400), exception.getMessage());
        return new ResponseEntity<>(problemDetail, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(value = {SecurityAccessException.class})
    public ResponseEntity<ProblemDetail> handleSecurityAccessException(SecurityAccessException exception) {

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(401), exception.getMessage());
        return new ResponseEntity<>(problemDetail, HttpStatus.UNAUTHORIZED);

    }

    @ExceptionHandler(value = {ResourceAlreadyExistException.class})
    public ResponseEntity<ProblemDetail> handleResourceAlreadyExistException(ResourceAlreadyExistException exception) {

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(409), exception.getMessage());
        return new ResponseEntity<>(problemDetail, HttpStatus.CONFLICT);

    }
}
