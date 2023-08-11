package com.example.demo.domain.member.entity;

import com.example.demo.domain.auth.dto.LoginRequest;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    private String refreshToken;

    public void updateRefreshToken(String refreshToken){
        this.refreshToken = refreshToken;
    }

    public boolean verifyUser(LoginRequest request){
        return this.email.equals(request.getEmail()) && this.password.equals(request.getPassword());
    }
}