package com.example.demo.domain.member.service;

import com.example.demo.domain.auth.dto.request.LoginRequest;
import com.example.demo.domain.auth.dto.response.LoginResponse;
import com.example.demo.domain.auth.jwt.JwtProvider;
import com.example.demo.domain.member.dto.request.MemberRegisterRequest;
import com.example.demo.domain.member.dto.response.MemberRegisterResponse;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.exception.EmailDuplicateException;
import com.example.demo.domain.member.exception.LoginFailedException;
import com.example.demo.domain.member.repository.MemberRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.example.demo.domain.auth.util.PasswordEncryptor.hashPassword;
import static com.example.demo.domain.auth.util.PasswordEncryptor.verifyPassword;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MemberService {
    MemberRepository memberRepository;
    JwtProvider jwtProvider;

    /*
    * 로그인
    * */
    public LoginResponse login(LoginRequest request) {
        Optional<Member> member = memberRepository.findByEmail(request.getEmail());

        if (member.isPresent()
                && verifyPassword(member.get().getPassword(), hashPassword(request.getPassword()))) {
            return new LoginResponse(jwtProvider.createToken(member.get()));
        }
        throw new LoginFailedException();
    }

    /*
     *  회원 등록
     * */
    @Transactional
    public MemberRegisterResponse registerMember(MemberRegisterRequest request) {
        // email duplicate check
        if(memberRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new EmailDuplicateException();
        }
        else {
            Member mentor = Member.builder()
                    .email(request.getEmail())
                    .password(hashPassword(request.getPassword()))
                    .build();

            memberRepository.save(mentor);
            return new MemberRegisterResponse();
        }
    }
}