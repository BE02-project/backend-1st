package com.github.sc_first_project.service;

import com.github.sc_first_project.controller.AppException;
import com.github.sc_first_project.controller.ErrorCode;
import com.github.sc_first_project.web.dto.User;
import com.github.sc_first_project.web.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    public void signup(String userEmail, String password) {
        //중복 체크 ( email )
        userRepository.findByEmail(userEmail)
                .ifPresent(user -> {
                    throw new AppException(userEmail + " 는 이미 있습니다", ErrorCode.USERNAME_DUPLICATED);
                });
        //저장
        User user = User.builder()
                .email(userEmail)
                .password(encoder.encode(password))
                .build();
        userRepository.save(user);
    }
}