package com.github.sc_first_project.web.dto.userDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UerJoinRequest {
    private Long id;
    private String email;
    private String password;
}