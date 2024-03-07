package com.github.sc_first_project.controller.postController;

import com.github.sc_first_project.service.postService.PostService;
import com.github.sc_first_project.web.dto.postDto.PostRegisterDto;
import com.github.sc_first_project.web.dto.postDto.PostResponseDto;
import com.github.sc_first_project.web.repository.postRepository.Post;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
@Slf4j
public class PostController {

    private final PostService postService;

    @GetMapping
    public List<Post> findAllPost() {
        return postService.getAllPost();
    }

    @GetMapping("/{id}")
    public PostResponseDto getPostById(@PathVariable Long id) {
        return postService.getPostById(id);
    }

    @GetMapping("/search")
    public List<PostResponseDto> getPostByEmail(@RequestParam("email") String email) {
        return postService.findPostByEmail(email);
    }

    @PostMapping("/create")
    public Post createPost(@RequestBody PostRegisterDto post) {
        return postService.createPost(post);
    }

    @PutMapping("/{id}")
    public Post updatePost(@PathVariable Long id, @RequestBody PostRegisterDto updatedPost) {
        return postService.updatePost(id, updatedPost);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id) {
        postService.deletePost(id);
    }
}
