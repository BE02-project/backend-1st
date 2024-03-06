package com.github.sc_first_project.web.repository.comment;

import com.github.sc_first_project.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
