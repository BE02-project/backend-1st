package com.github.sc_first_project.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Entity //엔티티 지정
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //기본키 1씩 증가
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "title", nullable = false) //title notNull 컬럼 매핑
    private String title;

    @Column(name = "userEmail")
    private String email;

    @Column(name = "created_At")
    private LocalDateTime createdAt;

    @Column(name = "content")
    private String content;

    @Builder //빌더 패턴 객체
    public Article(String title, String email, LocalDateTime createdAt, String content) {
        this.title = title;
        this.email = email;
        this.createdAt = createdAt;
        this.content = content;
    }

    public void update(String content) {
        this.content = content;
    }
}
