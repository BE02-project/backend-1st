package com.github.sc_first_project.web.repository.user;

import com.github.sc_first_project.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FindBlogUserRepository extends JpaRepository<Article, String> {
    List<Article> findByEmail(String email);
}
