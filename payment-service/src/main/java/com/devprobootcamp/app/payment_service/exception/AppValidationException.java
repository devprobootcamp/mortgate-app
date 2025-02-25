package com.devprobootcamp.app.payment_service.exception;

/**
 * Author: Edward Tanko <br/>
 * Date: 2/24/25 7:00 PM<br/>
 */
public class AppValidationException extends RuntimeException{
    public AppValidationException(String message) {
        super(message);
    }
}
