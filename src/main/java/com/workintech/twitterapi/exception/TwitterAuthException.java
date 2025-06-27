package com.workintech.twitterapi.exception;

import org.springframework.http.HttpStatus;

public class TwitterAuthException extends TwitterException {
    public TwitterAuthException(String message) {
        super(message, HttpStatus.FORBIDDEN); // HTTP 403 - Forbidden
    }
}


//Yetkilendirme hataları için