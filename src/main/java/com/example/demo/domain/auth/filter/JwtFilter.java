package com.example.demo.domain.auth.filter;


import com.example.demo.domain.auth.entity.AuthenticateUser;
import com.example.demo.domain.auth.jwt.Jwt;
import com.example.demo.domain.auth.jwt.JwtProvider;
import com.example.demo.domain.member.service.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.servlet.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class JwtFilter implements Filter {

    JwtProvider jwtProvider;
    ObjectMapper objectMapper;
    MemberService memberService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException {

        Object attribute = request.getAttribute(VerifyFilter.AUTHENTICATE_USER);
        if (attribute instanceof AuthenticateUser authenticateUser) {
            Map<String, Object> claims = new HashMap<>();
            String authenticateUserJson = objectMapper.writeValueAsString(authenticateUser);
            claims.put(VerifyFilter.AUTHENTICATE_USER, authenticateUserJson);

            Jwt jwt = jwtProvider.createJwt(claims);
            memberService.updateRefreshToken(authenticateUser.getEmail(), jwt.getRefreshToken());

            String json = objectMapper.writeValueAsString(jwt);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        }
        else  {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void destroy() {

    }
}