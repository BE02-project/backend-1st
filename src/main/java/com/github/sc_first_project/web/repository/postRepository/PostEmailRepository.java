package com.github.sc_first_project.web.repository.postRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostEmailRepository extends JpaRepository<Post, String> {
    List<Post> findPostByEmail(String email);
}
