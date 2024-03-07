package com.github.sc_first_project.service.postService;

import com.github.sc_first_project.web.dto.postDto.PostRegisterDto;
import com.github.sc_first_project.web.dto.postDto.PostResponseDto;
import com.github.sc_first_project.web.repository.postRepository.Post;
import com.github.sc_first_project.web.repository.postRepository.PostEmailRepository;
import com.github.sc_first_project.web.repository.postRepository.PostRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final PostEmailRepository postEmailRepository;
    @Value("${jwt.token.secret}")
    private String secretKey;

    public PostService(PostRepository postRepository, PostEmailRepository postEmailRepository) {
        this.postRepository = postRepository;
        this.postEmailRepository = postEmailRepository;
    }

    @Transactional
    public List<Post> getAllPost() {
        return postRepository.findAll();
    }

    @Transactional
    public PostResponseDto getPostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("잘못된 Post ID 입니다."));
        return new PostResponseDto(post);
    }

    @Transactional
    public Post createPost(PostRegisterDto postRegisterDto) {
        return postRepository.save(postRegisterDto.toEntity());
    }

    @Transactional
    public Post updatePost(Long id, PostRegisterDto updatedPost) {
        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("잘못된 Post ID 입니다."));
        post.update(updatedPost.getTitle(), post.getContent());

        return postRepository.save(post);
    }

    @Transactional
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    @Transactional
    public List<PostResponseDto> getPostByEmail(String email) {
        List<Post> posts = postEmailRepository.findPostByEmail(email).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 email 입니다."));

        return posts.stream().map(PostResponseDto::new).collect(Collectors.toList());
    }
}
