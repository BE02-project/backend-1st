package com.github.sc_first_project.service.userService;

import com.github.sc_first_project.exception.AppException;
import com.github.sc_first_project.exception.ErrorCode;
import com.github.sc_first_project.utils.JwtTokenUtil;
import com.github.sc_first_project.web.repository.userRepository.User;
import com.github.sc_first_project.web.repository.userRepository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    @Value("${jwt.token.secret}")
    private String key;
    private Long expireTimeMs = 1000 * 60 * 60L;

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

    public String login(String userEmail, String password) {
        // userEmail 없을 경우
        User selectUser = userRepository.findByEmail(userEmail).orElseThrow(() -> new AppException(userEmail + " 이 없습니다", ErrorCode.USE_EMAIL_NOT_FOUND));
        // password 틀렸을 경우
        if (!encoder.matches(password, selectUser.getPassword())) {
            throw new AppException("패스워드 틀렸습니다", ErrorCode.INVALID_PASSWORD);
        }
        String token = JwtTokenUtil.createToken(selectUser.getEmail(), key, expireTimeMs);
        return token;
    }
}
