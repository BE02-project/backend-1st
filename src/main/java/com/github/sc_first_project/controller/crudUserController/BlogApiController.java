package com.github.sc_first_project.controller.crudUserController;

import com.github.sc_first_project.domain.Article;
import com.github.sc_first_project.service.curdUser.BlogService;
import com.github.sc_first_project.web.crudDTO.AddArticleRequest;
import com.github.sc_first_project.web.crudDTO.ArticleResponse;
import com.github.sc_first_project.web.crudDTO.UpdateArticleRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class BlogApiController {
    private final BlogService blogService;

    //인증 사용자 글 쓰기 // http://localhost:8080/api/articles
    @PostMapping("/api/articles") //쓰기
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request, Principal principal) {
        Article savedArticle = blogService.save(request, principal.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(savedArticle);
    }

    //전체 조회 // http://localhost:8080/api/articles1
    @GetMapping("/api/articles1")
    public ResponseEntity<List<ArticleResponse>> findUserAll() {
        List<ArticleResponse> findAll = blogService.findAll()
                .stream()
                .map(ArticleResponse::new)
                .toList();
        return ResponseEntity.ok().body(findAll);
    }

// email 사용자 조회 / http://localhost:8080/api/emailUser?author_email=jinsung3@naver.com
    @GetMapping("/api/emailUser")
    public ResponseEntity<List<ArticleResponse>> findAllArticles(@RequestParam("author_email") String authorEmail) {
        List<ArticleResponse> articles = blogService.emailFindAll(authorEmail)
                .stream()
                .map(ArticleResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(articles);
    }

//id 값으로 조회 // http://localhost:8080/api/idUser/5
    @GetMapping("/api/idUser/{id}")
    public ResponseEntity<ArticleResponse> findIdUser(@PathVariable long id) {
        Article article = blogService.findById(id);
        return ResponseEntity.ok()
                .body(new ArticleResponse(article));
    }


    // 자기가 쓴 글 삭제, 작성자 email, JWT, ID 확인 // http://localhost:8080/api/userIdDel/9
    @DeleteMapping("/api/userIdDel/{id}")
    public ResponseEntity<String> deleteArticle(@PathVariable long id, Principal principal) {
        //사용자 이메일
        String userEmail = principal.getName();
        //글 작성자 이메일
        Article article = blogService.findById(id);
        String authorEmail = article.getEmail();
        if (!userEmail.equals(authorEmail)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("인증회원이 아닙니다 / 글 삭제 권한이 없습니다.");
        }
        blogService.delete(id);
        return ResponseEntity.ok().body("삭제 완료");
    }

    // id 로 업데이트 ( 자기것만 ) // http://localhost:8080/api/put/8
    @PutMapping("/api/put/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable long id, @RequestBody UpdateArticleRequest request, Principal principal) {
        String userEmail = principal.getName();
        Article updateArticle = blogService.update(id, request, userEmail);
        return ResponseEntity.ok(updateArticle);
    }


}
