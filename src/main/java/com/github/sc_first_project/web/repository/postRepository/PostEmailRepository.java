package com.github.sc_first_project.web.repository.postRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostEmailRepository extends JpaRepository<Post, String> {
    Optional<List<Post>> findPostByEmail(String email);
}
