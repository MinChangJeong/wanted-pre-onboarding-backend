package com.example.demo.domain.member.dto.request;

import com.example.demo.domain.auth.valid.MinLength;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Email;


@Getter
@RequiredArgsConstructor
public class MemberRegisterRequest {
    @Email
    private String email;
    @MinLength
    private String password;
}
