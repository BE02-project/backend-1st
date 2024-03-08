package com.github.sc_first_project.service.curdUser;

import com.github.sc_first_project.domain.Article;
import com.github.sc_first_project.web.crudDTO.AddArticleRequest;
import com.github.sc_first_project.web.crudDTO.UpdateArticleRequest;
import com.github.sc_first_project.web.repository.user.BlogRepository;
import com.github.sc_first_project.web.repository.user.FindBlogUserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor // final 이 붙거나 @NotNull 이 붙은 필드 생성자 추가
@Service
public class BlogService {
    private final BlogRepository blogRepository;
    private final FindBlogUserRepository findBlogUserRepository;

    //글 추가
    public Article save(AddArticleRequest request, String email) {
        Article articleToSave = request.toEntity(email);
        return blogRepository.save(articleToSave);
    }

    public List<Article> emailFindAll(String email) {
        return findBlogUserRepository.findByEmail(email);
    }

    public Article findById(long id) {
        return blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));
    }

    public void delete(long id) {
        blogRepository.deleteById(id);
    }

    public List<Article> findAll() {
        return blogRepository.findAll();
    }

    @Transactional
    public Article update(long id, UpdateArticleRequest request, String userEmailToken) {
        Article article = blogRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("not found " + id));

        if (!userEmailToken.equals(article.getEmail())) {
            throw new IllegalArgumentException("업데이트 권한이 없습니다");
        }
        article.update(request.getContent());
        return article;

    }

}
