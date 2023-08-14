package com.example.demo.domain.member.dto.request;

import com.example.demo.domain.auth.valid.MinLength;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Email;


@Getter
@AllArgsConstructor
public class MemberRegisterRequest {
    @Email
    private String email;
    @MinLength
    private String password;
}
