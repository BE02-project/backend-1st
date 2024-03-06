package com.github.sc_first_project.web.userDto;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserLoginRequest {
    private String email;
    private String password;
}
