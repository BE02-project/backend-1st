package com.github.sc_first_project.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts")
public class CheckUserTokenController {
    @PostMapping
    public ResponseEntity<String> writeCRUD() {
        return ResponseEntity.ok().body("인증 회원입니다");
    }
}
