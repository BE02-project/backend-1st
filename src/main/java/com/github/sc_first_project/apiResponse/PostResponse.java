package com.github.sc_first_project.apiResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostResponse {
    private Long id;
    private String title;
    private String content;
}
