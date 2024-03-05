package com.github.sc_first_project.exception;

import com.github.sc_first_project.controller.AppException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice //에러나면 이걸 찾음
public class ExceptionManager {
    @ExceptionHandler(AppException.class)
    public ResponseEntity<?> AppExceptionHandler(AppException e) { //에러나면 return

        return ResponseEntity.status(e.getErrorCode().getHttpStatus()) //찍히는 에러
                .body(e.getErrorCode().name() + " " + e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> runtimeExceptionHandler(RuntimeException e) {

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(e.getMessage());
    }
}
