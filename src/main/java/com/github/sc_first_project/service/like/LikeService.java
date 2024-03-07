package com.github.sc_first_project.service.like;

import com.github.sc_first_project.exception.AppException;
import com.github.sc_first_project.exception.ErrorCode;
import com.github.sc_first_project.web.repository.likes.Likes;
import com.github.sc_first_project.web.repository.likes.LikesRepository;
import com.github.sc_first_project.web.repository.postRepository.Post;
import com.github.sc_first_project.web.repository.postRepository.PostRepository;
import com.github.sc_first_project.web.repository.user.User;
import com.github.sc_first_project.web.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class LikeService {
    private final LikesRepository likesRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;


    public String postLike(long postId, String email) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new AppException("포스트를 찾을 수 없습니다.", ErrorCode.POST_NOT_FOUND)
        );

        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new AppException("사용자를 찾을 수 없습니다", ErrorCode.USE_EMAIL_NOT_FOUND)
        );

        Optional<Likes> likes = likesRepository.findByUserAndPost(user, post);

        if(likes.isPresent()){
            likesRepository.delete(likes.get());
            return "좋아요를 취소했습니다.";
        } else {
            likesRepository.save(Likes.toEntity(user, post));
            return "좋아요를 눌렀습니다.";
        }
    }
}
