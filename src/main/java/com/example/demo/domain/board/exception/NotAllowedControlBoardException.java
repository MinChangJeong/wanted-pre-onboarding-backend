package com.example.demo.domain.board.exception;

import com.example.demo.global.advice.ErrorCode;
import com.example.demo.global.advice.exception.BusinessException;

public class NotAllowedControlBoardException extends BusinessException {

    public NotAllowedControlBoardException() {
        super(ErrorCode.NOT_ALLOWED_CONTROL_BOARD_ERROR);
    }
}