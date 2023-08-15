package com.example.demo.domain.member.controller;

import com.example.demo.domain.auth.dto.request.LoginRequest;
import com.example.demo.domain.auth.dto.response.LoginResponse;
import com.example.demo.domain.auth.jwt.JwtProvider;
import com.example.demo.domain.member.dto.request.MemberRegisterRequest;
import com.example.demo.domain.member.dto.response.MemberRegisterResponse;
import com.example.demo.domain.member.service.MemberService;
import com.example.demo.support.docs.RestDocumentTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static com.example.demo.support.docs.ApiDocumentUtils.getDocumentRequest;
import static com.example.demo.support.docs.ApiDocumentUtils.getDocumentResponse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MemberController.class)
@DisplayName("MemberController 에서")
class MemberControllerTest extends RestDocumentTest {
    @MockBean private MemberService memberService;
    @MockBean private JwtProvider jwtProvider;

    @Test
    @DisplayName("로그인을 성공적으로 수행하는가?")
    void successLogin() throws Exception {
        // given
        LoginRequest request
                = new LoginRequest(
                "wjdalsckd777@naver.com",
                "test12345");

        String accessToken = "accessToken";

        when(memberService.login(any()))
                .thenReturn(new LoginResponse(accessToken));

        // when
        ResultActions perform =
                mockMvc.perform(
                        post("/member/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(toRequestBody(request))
                );

        // then
        perform.andExpect(status().isOk());

        // docs
        perform.andDo(print())
                .andDo(document("login",
                        getDocumentRequest(),
                        getDocumentResponse()));
    }
    @Test
    @DisplayName("회원을 성공적으로 등록하는가?")
    void successRegisterMember() throws Exception {
        // given
        MemberRegisterRequest request
                = new MemberRegisterRequest(
                "wjdalsckd777@naver.com",
                "test12345");

        when(memberService.registerMember(request))
                .thenReturn(new MemberRegisterResponse());

        // when
        ResultActions perform =
                mockMvc.perform(
                        post("/member")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(toRequestBody(request))
                );

        // then
        perform.andExpect(status().isOk());

        // docs
        perform.andDo(print())
                .andDo(document("register member",
                        getDocumentRequest(),
                        getDocumentResponse()));
    }
}
