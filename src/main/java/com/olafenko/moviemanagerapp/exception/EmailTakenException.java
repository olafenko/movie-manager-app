package com.olafenko.moviemanagerapp.exception;

public class EmailTakenException extends RuntimeException{
    public EmailTakenException(String message) {
        super(message);
    }

    public EmailTakenException(String message, Throwable cause) {
        super(message, cause);
    }
}
