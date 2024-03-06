package com.github.sc_first_project.web.repository.likes;


import com.github.sc_first_project.web.userDto.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "likes")
public class LikesEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    private Long LikeId;

    @JoinColumn(name = "user_id")
    @ManyToOne
    private User userEntity;

//    @JoinColumn(name = "post_id")
//    @ManyToOne
//    private PostEntity postEntity;
//
//    public LikesEntity(UserEntity userEntity, PostEntity postEntity){
//        this.userEntity = userEntity;
//        this.postEntity = postEntity;
//    }
}
