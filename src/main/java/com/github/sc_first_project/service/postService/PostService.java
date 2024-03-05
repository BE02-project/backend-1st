package com.github.sc_first_project.service.postService;

import com.github.sc_first_project.exception.AppException;
import com.github.sc_first_project.exception.ErrorCode;
import com.github.sc_first_project.web.dto.postDto.PostRegisterDto;
import com.github.sc_first_project.web.dto.postDto.PostResponseDto;
import com.github.sc_first_project.web.repository.postRepository.Post;
import com.github.sc_first_project.web.repository.postRepository.PostRepository;
import com.github.sc_first_project.web.repository.user.UserRepository;
import com.github.sc_first_project.web.userDto.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public List<Post> getAllPost() {
        return postRepository.findAll();
    }

    @Transactional
    public PostResponseDto getPostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("잘못된 Post ID 입니다."));
        return new PostResponseDto(post);
    }

//    @Transactional
//    public List<Post> getPostByEmail(String email) {
////        List<Post> posts = postRepository.findPostByEmail(email).
//        List<Post> posts = postRepository.findPostByEmail(email);
//        posts.stream().
//
////        return new PostResponseDto(posts);
//    }

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
