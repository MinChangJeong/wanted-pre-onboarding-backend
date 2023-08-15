package com.example.demo.global.advice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,  "서버오류"),
    INPUT_INVALID_ERROR(HttpStatus.BAD_REQUEST,  "잘못된 입력"),

    ENTITY_NOT_FOUND_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "유효하지 않은 접근입니다. "),
    EMAIL_DUPLICATION_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "중복된 이메일이 존재합니다. "),
    LOGIN_FAILED_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "로그인에 실패했습니다. 이메일 또는 비밀번호를 확인해주세요."),
    JWT_AUTHORIZATION_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "권한이 없습니다. "),
    NOT_ALLOWED_CONTROL_BOARD_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "게시글에 대한 권한이 없습니다. ");

    private final HttpStatus status;
    private final String message;
}