package com.github.sc_first_project.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AppException extends RuntimeException{
    private String message;
    private ErrorCode errorCode;

}
