package com.example.demo.domain.board.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;


@Getter
@AllArgsConstructor
public class BoardDeleteRequest {
    @NotNull
    private Long memberId;
    @NotNull
    private Long boardId;
}
