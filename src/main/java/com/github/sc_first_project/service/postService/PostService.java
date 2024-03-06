package com.github.sc_first_project.service.postService;

import com.github.sc_first_project.utils.JwtTokenUtil;
import com.github.sc_first_project.web.dto.postDto.PostRegisterDto;
import com.github.sc_first_project.web.dto.postDto.PostResponseDto;
import com.github.sc_first_project.web.repository.postRepository.Post;
import com.github.sc_first_project.web.repository.postRepository.PostRepository;
import com.github.sc_first_project.web.repository.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    @Value("${jwt.token.secret}")
    private String secretKey;

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
    public List<Post> getPostByEmail(String email, String token) {
        String emailToken = token.substring(7);
        String userEmail = JwtTokenUtil.getUserEmail(emailToken, secretKey);
        return postRepository.findPostByEmail(userEmail);
    }

    @Transactional
    public Post createPost(PostRegisterDto post, String email) {

        return postRepository.save(post.toEntity());
    }

    @Transactional
    public Post updatePost(Long id, PostRegisterDto updatedPost) {
        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("잘못된 Post ID 입니다."));
        post.update(updatedPost.getTitle(), post.getContent(), LocalDateTime.now());

        return postRepository.save(post);
    }

    @Transactional
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}
