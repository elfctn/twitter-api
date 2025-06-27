package com.workintech.twitterapi.exception;

import org.springframework.http.HttpStatus;

public class TweetNotFoundException extends TwitterException {
  public TweetNotFoundException(String message) {
    super(message, HttpStatus.NOT_FOUND); // HTTP 404 - Not Found
  }
}
//Tweet bulunamadı durumuna özel hata sınıfı

//TweetServiceImpl içinde
// throw new RuntimeException(...) demek yerine
// throw new TweetNotFoundException(...) dedim
//daha açıklayıcı oldu