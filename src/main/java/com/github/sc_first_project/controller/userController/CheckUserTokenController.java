package com.github.sc_first_project.controller.userController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/posts")
public class CheckUserTokenController {
    @PostMapping
    public ResponseEntity<String> writeCRUD(Principal principal) {
        return ResponseEntity.ok().body(principal.getName() + "인증 회원입니다");
    }
}
