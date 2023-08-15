package com.example.demo.domain.auth.resolver;

import com.example.demo.domain.auth.entity.AuthenticateUser;
import com.example.demo.domain.auth.exception.JwtAuthorizationException;
import com.example.demo.domain.auth.jwt.JwtAuthorization;
import com.example.demo.domain.auth.jwt.JwtProvider;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class JwtAuthorizationArgumentResolver implements HandlerMethodArgumentResolver {

    JwtProvider jwtProvider;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(JwtAuthorization.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        HttpServletRequest httpServletRequest = webRequest.getNativeRequest(HttpServletRequest.class);

        // 헤더 값 체크
        if (httpServletRequest != null) {
            String token = httpServletRequest.getHeader("Authorization");

            if (token != null && !token.trim().equals("")) {
                // 토큰 있을 경우 검증
                if (jwtProvider.validateToken(token)) {
                    // 검증 후 AuthenticationUser 리턴
                    return jwtProvider.getClaim(token);
                }
            }

            // 토큰은 없지만 필수가 아닌 경우 체크
            JwtAuthorization annotation = parameter.getParameterAnnotation(JwtAuthorization.class);
            if (annotation != null && !annotation.required()) {
                // 필수가 아닌 경우 기본 객체 리턴
                return new AuthenticateUser();
            }
        }

        // 토큰 값이 없으면 에러
        throw new JwtAuthorizationException();
    }
}
