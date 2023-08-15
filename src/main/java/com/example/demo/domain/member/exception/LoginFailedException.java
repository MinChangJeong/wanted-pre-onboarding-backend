package com.example.demo.domain.member.exception;

import com.example.demo.global.advice.ErrorCode;
import com.example.demo.global.advice.exception.BusinessException;

public class LoginFailedException extends BusinessException {

    public LoginFailedException() {
        super(ErrorCode.LOGIN_FAILED_ERROR);
    }
}