package com.workintech.twitterapi.exception;

import com.workintech.twitterapi.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// @ControllerAdvice: Bu sınıfın, tüm Controller'lar için merkezi bir
// istisna (exception) yöneticisi olduğunu Spring'e bildirir.
@ControllerAdvice
public class GlobalExceptionHandler {

    // @ExceptionHandler: Bu metodun, özellikle TwitterException tipindeki
    // hataları yakalamak için görevli olduğunu belirtir.
    @ExceptionHandler(TwitterException.class)
    public ResponseEntity<ErrorResponseDTO> handleTwitterException(TwitterException ex) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(
                ex.getStatus().value(),
                ex.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(errorResponse, ex.getStatus());
    }

    // Bu metot, yukarıda özel olarak ele almadığımız diğer tüm hataları yakalar.
    // Bu, beklenmedik bir hata olursa uygulamanın çökmesini engeller.
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleAllExceptions(Exception ex) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Beklenmedik bir hata oluştu: " + ex.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
