package com.github.sc_first_project.web.repository.userRepository;


import jakarta.persistence.*;
import lombok.*;

@Builder
@Entity
@AllArgsConstructor
@Getter
@NoArgsConstructor
//@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
}
