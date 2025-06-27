package com.workintech.twitterapi.exception;

import com.workintech.twitterapi.dto.ErrorResponseDTO; // Güncellendi
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Bu metot artık TwitterException'dan türeyen TÜM hataları yakalar.
    // (TweetNotFound, UserNotFound, TwitterAuth, TwitterConflict...)
    @ExceptionHandler(TwitterException.class)
    public ResponseEntity<ErrorResponseDTO> handleTwitterException(TwitterException ex) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO( // Güncellendi
                ex.getStatus().value(),
                ex.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(errorResponse, ex.getStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleAllExceptions(Exception ex) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO( // Güncellendi
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Beklenmedik bir sunucu hatası oluştu.",
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
