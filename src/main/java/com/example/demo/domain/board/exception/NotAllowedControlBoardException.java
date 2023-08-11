package com.example.demo.domain.board.exception;

import com.example.demo.global.advice.exception.BusinessException;
import com.example.demo.global.advice.ErrorCode;

public class NotAllowedControlBoardException extends BusinessException {

    public NotAllowedControlBoardException() {
        super(ErrorCode.NOT_ALLOWED_CONTROL_BOARD_ERROR);
    }
}