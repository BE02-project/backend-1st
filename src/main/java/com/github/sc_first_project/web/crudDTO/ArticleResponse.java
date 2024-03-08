package com.github.sc_first_project.web.crudDTO;

import com.github.sc_first_project.domain.Article;
import lombok.Getter;

@Getter
public class ArticleResponse {
    private final String email;
    private final String title;
    private final String content;

    public ArticleResponse(Article article) {
        this.email = article.getEmail();
        this.title = article.getTitle();
        this.content = article.getTitle();
    }
}
