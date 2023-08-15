package com.example.demo.domain.auth.exception;

import com.example.demo.global.advice.ErrorCode;
import com.example.demo.global.advice.exception.BusinessException;

public class JwtAuthorizationException extends BusinessException {

    public JwtAuthorizationException() {
        super(ErrorCode.JWT_AUTHORIZATION_ERROR);
    }
}