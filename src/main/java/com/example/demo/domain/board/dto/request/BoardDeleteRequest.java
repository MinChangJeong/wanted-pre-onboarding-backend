package com.example.demo.domain.board.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;


@Getter
@RequiredArgsConstructor
public class BoardDeleteRequest {
    @NotNull
    private Long memberId;
    @NotNull
    private Long boardId;
}
