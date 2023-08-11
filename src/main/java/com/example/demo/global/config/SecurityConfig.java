package com.example.demo.global.config;

import com.example.demo.domain.auth.filter.JwtFilter;
import com.example.demo.domain.auth.filter.VerifyFilter;
import com.example.demo.domain.auth.jwt.JwtProvider;
import com.example.demo.domain.member.service.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

@Configuration
public class SecurityConfig {
    @Bean
    public FilterRegistrationBean verifyUserFilter(ObjectMapper mapper, MemberService memberService) {
        FilterRegistrationBean<Filter> filterRegistrationBean = new
                FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new VerifyFilter(mapper, memberService));
        filterRegistrationBean.setOrder(1);
        filterRegistrationBean.addUrlPatterns("/auth/login");
        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean jwtFilter(JwtProvider provider, ObjectMapper mapper, MemberService memberService) {
        FilterRegistrationBean<Filter> filterRegistrationBean = new
                FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new JwtFilter(provider, mapper, memberService));
        filterRegistrationBean.setOrder(2);
        filterRegistrationBean.addUrlPatterns("/auth/login");
        return filterRegistrationBean;
    }
}