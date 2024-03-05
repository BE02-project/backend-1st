package com.github.sc_first_project.apiResponse;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponseWithToken {
    private String token;
    private String message;
    private String status;
    private String contentType; // this is a new field

    public ApiResponseWithToken(String status, String contentType, String token, String message) {
        this.status = status;
        this.contentType = contentType;
        this.token = token;
        this.message = message;
    }

}