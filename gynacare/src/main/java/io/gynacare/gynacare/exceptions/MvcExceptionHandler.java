package io.gynacare.gynacare.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.security.auth.login.CredentialException;
import java.time.LocalDateTime;

@RestControllerAdvice
public class MvcExceptionHandler {

    @ExceptionHandler(CredentialException.class)
    public ResponseEntity<ErrorDetails> credentialException(CredentialException credentialException) {
        return ResponseEntity.badRequest().body(ErrorDetails.builder()
                .message(credentialException.getMessage())
                .timeStamp(LocalDateTime.now())
                .build());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> exception(Exception exception) {
        return ResponseEntity.badRequest().body(ErrorDetails.builder()
                .message(exception.getMessage())
                .timeStamp(LocalDateTime.now())
                .build());
    }


}
