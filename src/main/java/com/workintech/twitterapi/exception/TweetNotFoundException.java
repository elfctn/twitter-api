package com.workintech.twitterapi.exception;

import org.springframework.http.HttpStatus;

public class TweetNotFoundException extends TwitterException {
  public TweetNotFoundException(String message) {
    super(message, HttpStatus.NOT_FOUND); // HTTP 404 - Not Found
  }
}
