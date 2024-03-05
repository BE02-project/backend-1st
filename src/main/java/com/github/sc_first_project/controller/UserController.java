package com.github.sc_first_project.controller;


import com.github.sc_first_project.service.UserService;
import com.github.sc_first_project.web.dto.UerJoinRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody UerJoinRequest dto) {
        userService.signup(dto.getEmail(), dto.getPassword());
        return ResponseEntity.ok().body("회원가입 성공");
    }
}
