package com.example.demo.domain.auth.entity;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticateUser {
    private Long memberId;
    private String email;
}
