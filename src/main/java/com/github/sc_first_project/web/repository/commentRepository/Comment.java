package com.github.sc_first_project.web.repository.commentRepository;


import com.github.sc_first_project.web.dto.commentDto.CommentDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Builder
@Entity
@AllArgsConstructor
@Getter
@NoArgsConstructor
//@Table(name = "user")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    private String content;
    private String author;
    private Long post_id;
    private Date created_at;


    public CommentDto toDto(){
        return CommentDto.builder()
                .id(this.id)
                .content(this.content)
                .author(this.author)
                .post_id(this.post_id)
                .created_at(this.created_at)
                .build();
    };
}
