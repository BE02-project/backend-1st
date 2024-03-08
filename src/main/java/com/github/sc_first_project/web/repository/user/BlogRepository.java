package com.github.sc_first_project.web.repository.user;

import com.github.sc_first_project.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Article, Long> {
}
