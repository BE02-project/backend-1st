package com.github.sc_first_project.web.dto.postDto;

import com.github.sc_first_project.web.repository.postRepository.Post;
import lombok.*;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostRegisterDto {
    private String title;
    private String content;
    private String email;

    public Post toEntity() {
        return new Post(title, content, email);
    }
}