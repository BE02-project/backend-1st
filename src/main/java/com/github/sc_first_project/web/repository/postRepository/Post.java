package com.github.sc_first_project.web.repository.postRepository;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@NoArgsConstructor
@ToString
//@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "email")
    private String email;
    @Column(name = "title")
    private String title;
    @Column(name = "content")
    private String content;

    public Post(String title, String content, String email) {
        this.title = title;
        this.content = content;
        this.email = email;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}