package com.github.sc_first_project.web.userDto;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity //엔티티 지정
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //기본키 1씩 증가
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "title", nullable = false) //title notNull 컬럼 매핑
    private String title;

    @Column(name = "content")
    private String content;

    @Builder //빌더 패턴 객체
    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
