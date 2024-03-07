package com.github.sc_first_project.web.dto.commentDto;


import com.github.sc_first_project.web.repository.commentRepository.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Builder
@AllArgsConstructor
@Getter
public class CommentDto {
    private Long id;
    private String content;
    private String author;
    private Long post_id;
    private Date created_at;

    public Comment toEntity(){
        return Comment.builder()
                .id(this.id)
                .content(this.content)
                .author(this.author)
                .post_id(this.post_id)
                .created_at(this.created_at)
                .build();
    };
}