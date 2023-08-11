package com.example.demo.domain.member.exception;

import com.example.demo.global.advice.exception.BusinessException;
import com.example.demo.global.advice.ErrorCode;
public class EmailDuplicateException extends BusinessException {

    public EmailDuplicateException() {
        super(ErrorCode.EMAIL_DUPLICATION_ERROR);
    }
}