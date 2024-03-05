package com.github.sc_first_project.controller;


import com.github.sc_first_project.apiResponse.ApiResponse;
import com.github.sc_first_project.apiResponse.ApiResponseWithToken;
import com.github.sc_first_project.service.UserService;
import com.github.sc_first_project.web.dto.UerJoinRequest;
import com.github.sc_first_project.web.dto.UserLoginRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Controller
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody UerJoinRequest dto) {
        userService.signup(dto.getEmail(), dto.getPassword());
        String message = "회원가입이 완료되었습니다.";
        ApiResponse response = new ApiResponse(message);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponseWithToken> login(@RequestBody UserLoginRequest dto) {
        String token = userService.login(dto.getEmail(), dto.getPassword());

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + token);
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        ApiResponseWithToken response = new ApiResponseWithToken(
                "HTTP/1.1 200 OK",
                Objects.requireNonNull(headers.get(HttpHeaders.CONTENT_TYPE)).get(0),
                token,
                "로그인이 성공적으로 완료되었습니다."

        );
        return ResponseEntity.ok().headers(headers).body(response);
    }

    @GetMapping("/logout")
    public ResponseEntity<ApiResponse> logout(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(
                request,
                response,
                SecurityContextHolder.getContext().getAuthentication()
        );
        ApiResponse logoutResponse = new ApiResponse("로그아웃되었습니다.");
        return ResponseEntity.ok().body(logoutResponse);
    }
}
