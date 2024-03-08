package com.github.sc_first_project.web.crudDTO;


import com.github.sc_first_project.domain.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor // 기본 생성자
@AllArgsConstructor // 모든 필드 값을 파라미터로 받는 생성자
@Getter
public class AddArticleRequest {
    private String title;
    private String content;
    private String email;
    private LocalDateTime createdAt;

    public Article toEntity(String email) {
        return Article.builder()
                .title(title)
                .content(content)
                .email(email)
                .createdAt(LocalDateTime.now())
                .build();
    }
}
