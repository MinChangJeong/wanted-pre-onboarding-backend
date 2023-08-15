package com.example.demo.domain.board.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;


@Getter
@AllArgsConstructor
public class BoardRegisterRequest {
    @NotBlank
    private String title;
    @NotBlank
    private String content;
}
