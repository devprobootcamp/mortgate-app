package com.devprobootcamp.app.account_service.exception;

/**
 * Author: Edward Tanko <br/>
 * Date: 2/19/25 6:36 PM<br/>
 */
public class SecurityAccessException extends RuntimeException{

    public SecurityAccessException(String message) {
        super(message);
    }
}
