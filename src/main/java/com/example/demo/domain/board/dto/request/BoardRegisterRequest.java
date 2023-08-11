package com.example.demo.domain.board.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Getter
@RequiredArgsConstructor
public class BoardRegisterRequest {
    @NotNull
    private Long memberId;
    @NotBlank
    private String title;
    @NotBlank
    private String content;
}
