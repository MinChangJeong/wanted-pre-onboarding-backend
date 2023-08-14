package com.example.demo.domain.board.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Getter
@AllArgsConstructor
public class BoardUpdateRequest {
    @NotNull
    private Long memberId;
    @NotNull
    private Long boardId;
    @NotBlank
    private String title;
    @NotBlank
    private String content;
}
