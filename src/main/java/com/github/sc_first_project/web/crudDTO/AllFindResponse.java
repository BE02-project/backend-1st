package com.github.sc_first_project.web.crudDTO;


import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
public class AllFindResponse {
    private final String title;
    private final String content;


    public AllFindResponse(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
