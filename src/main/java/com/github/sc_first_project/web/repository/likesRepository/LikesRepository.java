package com.github.sc_first_project.web.repository.likesRepository;

import com.github.sc_first_project.web.repository.postRepository.Post;
import com.github.sc_first_project.web.repository.userRepository.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikesRepository extends JpaRepository<Likes, Long> {
    Optional<Likes> findByUserAndPost(User user, Post post);
}
