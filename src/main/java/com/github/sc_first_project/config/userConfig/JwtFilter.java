package com.github.sc_first_project.config.userConfig;

import com.github.sc_first_project.service.userService.UserService;
import com.github.sc_first_project.utils.JwtTokenUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class JwtFilter extends OncePerRequestFilter {

    private final UserService userService;
    private final String secretKey;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
        log.info("authorization : {}", authorization);

        if (authorization == null || !authorization.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            if (authorization == null) {
                log.info("Authorization header is missing in the request.");
            } else if (!authorization.startsWith("Bearer ")) {
                log.info("Bearer prefix is missing in the Authorization header.");
            }
            return;
        }

        //토큰 꺼내기
        String token = authorization.split(" ")[1];

        //Token Expired 되었는지 여부
        if (JwtTokenUtil.isExpired(token, secretKey)) {
            // 로그 추가하여 토큰 만료 확인
            log.info("Authentication 만료 되었습니다");
            filterChain.doFilter(request, response);
            return;
        } else {
            // 로그 추가하여 토큰 유효 함을 확인
            log.info("Token is still valid.");
        }

        //email token 에서 꺼내기
        String email = JwtTokenUtil.getUserEmail(token, secretKey);
        log.info("userEmail{} : ", email);
        //권한 부여
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(email, null, List.of(new SimpleGrantedAuthority("EMAIL")));
        //Detail 을 넣어줍니다
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
    }
}

