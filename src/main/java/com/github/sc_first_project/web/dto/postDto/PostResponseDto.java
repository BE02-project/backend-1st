package com.github.sc_first_project.web.dto.postDto;

import com.github.sc_first_project.web.repository.postRepository.Post;
import lombok.*;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostResponseDto {
    private Long id;
    private String title;
    private String content;
    private String email;

    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.email = post.getEmail();
    }
}
