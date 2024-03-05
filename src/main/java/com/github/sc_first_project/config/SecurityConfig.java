package com.github.sc_first_project.config;

import com.github.sc_first_project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserService userService;

    @Value("${jwt.token.secret}")
    private String secretKey;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf(AbstractHttpConfigurer::disable) //csrf
                .httpBasic(AbstractHttpConfigurer::disable) //ui쪽
                .formLogin(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/api/signup", "/api/login", "/api/logout", "/api/posts").permitAll() //경로 허용
                        .requestMatchers(HttpMethod.POST, "/api/v1/**").authenticated()
                        .anyRequest().authenticated())
//				.formLogin(formLogin -> formLogin
//						.loginPage("/login")
//						.defaultSuccessUrl("/home"))
                .logout((logout) -> logout
                        .logoutSuccessUrl("/login")
                        .invalidateHttpSession(true))
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)//jwt 사용하는 경우
//                        .and()
//                        .addFilterBefore(new JwtFilter(userService, secretKey), UsernamePasswordAuthenticationFilter.class)

                );
        return http.build()
                ;
    }


}
