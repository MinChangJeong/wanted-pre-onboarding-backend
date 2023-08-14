package com.example.demo.domain.member.service;

import com.example.demo.domain.member.dto.request.MemberRegisterRequest;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.exception.EmailDuplicateException;
import com.example.demo.domain.member.repository.MemberRepository;
import com.example.demo.support.database.EnableDataBaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static com.example.demo.global.advice.ErrorCode.EMAIL_DUPLICATION_ERROR;
import static org.junit.jupiter.api.Assertions.assertEquals;

@EnableDataBaseTest
@DisplayName("MemberServiceTest 에서")
public class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("회원 정보를 DB에 성공적으로 등록하고 중복된 이메일로 등록 시 EmailDuplicateException 발생하는지 테스트")
    @Transactional
    @Rollback(value = true)
    void testRegisterMemberAndEmailDuplicationException() {
        // given
        MemberRegisterRequest request1 = new MemberRegisterRequest("wjdalsckd777@naver.com", "test12345");
        MemberRegisterRequest request2 = new MemberRegisterRequest("wjdalsckd888@naver.com", "test12345");

        // when
        memberService.registerMember(request1);

        // then
        Member member1 = memberRepository.findByEmail(request1.getEmail());
        Assertions.assertNotNull(member1);

        // when
        memberService.registerMember(request2);

        // then
        Member member2 = memberRepository.findByEmail(request2.getEmail());
        Assertions.assertNotNull(member2);

        // when
        Throwable exception = Assertions.assertThrows(EmailDuplicateException.class, () -> {
            memberService.registerMember(request1);
        });

        // then
        assertEquals(EMAIL_DUPLICATION_ERROR.getMessage(), exception.getMessage());
    }
}
