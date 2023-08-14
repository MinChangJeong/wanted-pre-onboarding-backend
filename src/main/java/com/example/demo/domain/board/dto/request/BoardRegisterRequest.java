package com.example.demo.domain.board.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Getter
@AllArgsConstructor
public class BoardRegisterRequest {
    @NotNull
    private Long memberId;
    @NotBlank
    private String title;
    @NotBlank
    private String content;
}
