package com.example.demo.domain.member.controller;

import com.example.demo.domain.auth.dto.request.LoginRequest;
import com.example.demo.domain.auth.dto.response.LoginResponse;
import com.example.demo.domain.member.dto.request.MemberRegisterRequest;
import com.example.demo.domain.member.dto.response.MemberRegisterResponse;
import com.example.demo.domain.member.service.MemberService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MemberController {
    MemberService memberService;

    /*
    * 로그인
    * */
    @PostMapping("/login")
    private ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok().body(memberService.login(request));
    }

    /*
     * 회원 등록
     * */
    @PostMapping
    public ResponseEntity<MemberRegisterResponse> registerMember(@Valid @RequestBody MemberRegisterRequest request) {
        return ResponseEntity.ok().body(memberService.registerMember(request));
    }
}