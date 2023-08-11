package com.example.demo.global.advice.exception;

import com.example.demo.global.advice.ErrorCode;

public class EntityNotFoundException extends BusinessException {

    public EntityNotFoundException() {
        super(ErrorCode.ENTITY_NOT_FOUND_ERROR);
    }
}