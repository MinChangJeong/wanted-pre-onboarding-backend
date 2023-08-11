package com.example.demo.domain.member.controller;

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
     * 회원 등록
     * */
    @PostMapping
    public ResponseEntity<MemberRegisterResponse> registerMember(@Valid @RequestBody MemberRegisterRequest request) {
        MemberRegisterResponse response = memberService.registerMember(request);
        return ResponseEntity.ok().body(response);
    }
}