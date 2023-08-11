package com.example.demo.domain.member.service;

import com.example.demo.domain.auth.dto.LoginRequest;
import com.example.demo.domain.member.dto.request.MemberRegisterRequest;
import com.example.demo.domain.member.dto.response.MemberRegisterResponse;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.exception.EmailDuplicateException;
import com.example.demo.domain.member.repository.MemberRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.demo.domain.auth.util.PasswordEncryptor.encryptPassword;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MemberService {
    MemberRepository memberRepository;

    /*
     *  회원 등록
     * */
    @Transactional
    public MemberRegisterResponse registerMember(MemberRegisterRequest request) {
        // email duplicate check
        if(memberRepository.findByEmail(request.getEmail()) != null) {
            throw new EmailDuplicateException();
        }
        else {
            Member mentor = Member.builder()
                    .email(request.getEmail())
                    .password(encryptPassword(request.getEmail()))
                    .build();

            memberRepository.save(mentor);
            return new MemberRegisterResponse();
        }
    }

    public boolean verifyUser(LoginRequest request){
        Member member = memberRepository.findByEmail(request.getEmail());
        return member != null;
    }

    @Transactional
    public void updateRefreshToken(String email, String refreshToken){
        Member member = memberRepository.findByEmail(email);
        if(member != null) member.updateRefreshToken(refreshToken);
    }
}