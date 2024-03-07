package com.github.sc_first_project.controller.postController;

import com.github.sc_first_project.service.postService.PostService;
import com.github.sc_first_project.web.dto.postDto.PostRegisterDto;
import com.github.sc_first_project.web.dto.postDto.PostResponseDto;
import com.github.sc_first_project.web.repository.postRepository.Post;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
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


    @GetMapping("/posts/{id}")
    public PostResponseDto getPostById(@PathVariable Long id) {
        return postService.getPostById(id);
    }

    @GetMapping("/posts/search")
    public ResponseEntity<List<PostResponseDto>> getPostByEmail(@RequestParam("email") String email) {
        List<PostResponseDto> responseDtoList = postService.getPostByEmail(email);

        return ResponseEntity.ok().body(responseDtoList);
    }

    @PostMapping("/posts/create")
    public Post createPost(@RequestBody PostRegisterDto post) {
        return postService.createPost(post);
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
