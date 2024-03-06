package com.github.sc_first_project.service.like;

import com.github.sc_first_project.web.repository.likes.LikesEntity;
import com.github.sc_first_project.web.repository.likes.LikesRepository;
import com.github.sc_first_project.web.userDto.User;
import jakarta.persistence.NoResultException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class LikeService {
    private final LikesRepository likesRepository;

    public void postLike(String like, Integer postId, HttpServletRequest request) {
        User userEntity = getUserFromToken(request); // 토큰으로부터 user정보 가져오기
        PostEntity postEntity = postRepository.findById(postId).orElseThrow(
                () -> new NoResultException("해당 포스트를 찾을 수 없습니다.")
        );  // 포스트아이디 찾기
        LikesEntity likesEntity = new LikesEntity(userEntity, postEntity);

        if (Objects.equals(like, "true")) {
            likesRepository.save(likesEntity);
        } else {
            likesRepository.delete(likesEntity);
        }
    }
}
