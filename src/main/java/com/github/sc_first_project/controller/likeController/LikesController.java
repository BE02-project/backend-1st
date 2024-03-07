package com.github.sc_first_project.controller.likeController;

import com.github.sc_first_project.service.likeService.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class LikesController {

    private final LikeService likeService;

    @PostMapping("/{postId}/like")
    public ResponseEntity<String> likePost(@PathVariable long postId, Principal principal) {
        String result = likeService.postLike(postId, principal.getName());

        return ResponseEntity.ok(result);
    }
}
