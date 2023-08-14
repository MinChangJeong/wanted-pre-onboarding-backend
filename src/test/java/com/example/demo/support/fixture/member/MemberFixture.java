package com.example.demo.support.fixture.member;


import com.example.demo.domain.member.entity.Member;
import lombok.Getter;

@Getter
public enum MemberFixture {
    MINCHANG(
            "wjdalsckd@naver.com",
            "0000");
    private String email;
    private String password;

    MemberFixture(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Member toEntity() {
        return Member.builder().email(email).password(password).build();
    }
}
