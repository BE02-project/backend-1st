package com.github.sc_first_project.web.repository.postRepository;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private LocalDateTime writeAt;

    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void update(String title, String content, LocalDateTime writeAt) {
        this.title = title;
        this.content = content;
        this.writeAt = writeAt;
    }
}