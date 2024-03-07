package com.github.sc_first_project.controller.commentController;

import com.github.sc_first_project.apiResponse.ApiResponse;
import com.github.sc_first_project.service.commentService.CommentService;
import com.github.sc_first_project.web.dto.commentDto.CommentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    private CommentService commentService;


    @GetMapping("/comments")
    public ResponseEntity<List<CommentDto>> getComments(){
        return ResponseEntity.ok().body(commentService.getComments());
    }

    @GetMapping("/comments/{id}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable Long id){
        return ResponseEntity.ok().body(commentService.getCommentById(id));
    }

    @PostMapping("/comments")
    public ResponseEntity<ApiResponse> createComment(@RequestBody CommentDto comment){
        if (commentService.createComment(comment)) {
            return ResponseEntity.ok().body(new ApiResponse("댓글 생성완료"));
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/comments")
    public ResponseEntity<ApiResponse> updateComment(@RequestBody CommentDto comment){
        if (commentService.updateComment(comment)) {
            return ResponseEntity.ok().body(new ApiResponse("댓글 수정완료"));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/comments")
    public ResponseEntity<ApiResponse> deleteComment(@RequestParam Long id){
        if (commentService.deleteComment(id)) {
            return ResponseEntity.ok().body(new ApiResponse("댓글 삭제완료"));
        }
        return ResponseEntity.notFound().build();
    }
}
