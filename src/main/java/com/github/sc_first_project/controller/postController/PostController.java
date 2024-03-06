package com.github.sc_first_project.controller.postController;

import com.github.sc_first_project.service.postService.PostService;
import com.github.sc_first_project.web.dto.postDto.PostRegisterDto;
import com.github.sc_first_project.web.repository.postRepository.Post;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class PostController {

    private final PostService postService;

    @GetMapping("/posts")
    public List<Post> findAllPost() {
        return postService.getAllPost();
    }

    @GetMapping("/posts/search")
    public List<Post> getPostByEmail(@RequestParam("author_email") String email, @RequestHeader("Authorization") String token) {
        return postService.getPostByEmail(email, token);
    }

    @PostMapping("/posts/{email}/write")
    public Post createPost(@RequestBody PostRegisterDto post, @PathVariable String email) {
        return postService.createPost(post, email);
    }

    @PutMapping("/posts/{id}")
    public Post updatePost(@PathVariable Long id, @RequestBody PostRegisterDto updatedPost) {
        return postService.updatePost(id, updatedPost);
    }

    @DeleteMapping("/posts/{id}")
    public void deletePost(@PathVariable Long id) {
        postService.deletePost(id);
    }
}
