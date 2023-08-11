package com.example.demo.domain.auth.filter;


import com.example.demo.domain.auth.dto.LoginRequest;
import com.example.demo.domain.auth.entity.AuthenticateUser;
import com.example.demo.domain.member.service.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

import javax.servlet.*;
import java.io.IOException;

@RequiredArgsConstructor
public class VerifyFilter implements Filter {
    public static final String AUTHENTICATE_USER = "authenticateUser";
    private final ObjectMapper objectMapper;
    private final MemberService memberService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException {
        try {
            LoginRequest loginRequest = objectMapper.readValue(request.getReader(), LoginRequest.class);

            if (memberService.verifyUser(loginRequest)) {
                request.setAttribute(AUTHENTICATE_USER, new AuthenticateUser(loginRequest.getEmail()));
            } else {
                throw new IllegalArgumentException();
            }

            chain.doFilter(request, response);

        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void destroy() {

    }
}