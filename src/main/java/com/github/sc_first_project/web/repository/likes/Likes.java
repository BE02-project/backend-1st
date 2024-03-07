package com.github.sc_first_project.web.repository.likes;

import com.github.sc_first_project.web.repository.postRepository.Post;
import com.github.sc_first_project.web.repository.user.User;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
public class Likes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    public static Likes toEntity(User user, Post post) {
        Likes likes = new Likes();
        likes.setUser(user);
        likes.setPost(post);
        return likes;
    }
}
