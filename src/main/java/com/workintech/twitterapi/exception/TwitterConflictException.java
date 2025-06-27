package com.workintech.twitterapi.exception;

import org.springframework.http.HttpStatus;

public class TwitterConflictException extends TwitterException {
    public TwitterConflictException(String message) {
        super(message, HttpStatus.CONFLICT); // HTTP 409 - Conflict
    }
}

//Çakışma hataları için