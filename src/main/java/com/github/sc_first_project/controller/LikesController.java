package com.github.sc_first_project.controller;

import com.github.sc_first_project.service.LikeService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class LikesController {

    private final LikeService likeService;

    @PostMapping("/like/{postId}")
    public ResponseEntity<Boolean> postLike(@RequestParam String like, @PathVariable Integer postId, HttpServletRequest request) {

        likeService.postLike(like, postId, request);
        return ResponseEntity.status(HttpStatus.OK).body(true);
    }

}
