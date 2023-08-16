package com.example.demo.domain.member.exception;

import com.example.demo.global.advice.ErrorCode;
import com.example.demo.global.advice.exception.BusinessException;
public class EmailDuplicateException extends BusinessException {

    public EmailDuplicateException() {
        super(ErrorCode.EMAIL_DUPLICATION_ERROR);
    }
}