package com.github.sc_first_project.service;

import com.github.sc_first_project.entity.Comment;
import com.github.sc_first_project.web.dto.CommentDto;
import com.github.sc_first_project.web.repository.comment.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public List<CommentDto> getComments() {
        return commentRepository.findAll().stream().map(Comment::toDto).toList();
    }

    public CommentDto getCommentById(Long id) {
        return commentRepository.findById(id).get().toDto();
    }

    public boolean createComment(CommentDto comment) {
        commentRepository.save(comment.toEntity());
        return true;
    }

    public boolean updateComment(CommentDto dto) {
        Comment comment = commentRepository.findById(dto.getId()).orElse(null);
        comment.setContent(dto.getContent());
        commentRepository.save(comment);
        return true;
    }

    public boolean deleteComment(Long id) {
        Comment comment = commentRepository.findById(id).orElse(null);
        commentRepository.delete(comment);
        return true;
    }
}

