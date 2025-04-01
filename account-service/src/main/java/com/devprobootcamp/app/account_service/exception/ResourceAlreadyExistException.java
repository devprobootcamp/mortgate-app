package com.devprobootcamp.app.account_service.exception;

/**
 * Author: Edward Tanko <br/>
 * Date: 2/19/25 6:36 PM<br/>
 */
public class ResourceAlreadyExistException extends RuntimeException{

    public ResourceAlreadyExistException(String message) {
        super(message);
    }
}
