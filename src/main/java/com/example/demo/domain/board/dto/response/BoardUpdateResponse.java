package com.example.demo.domain.board.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardUpdateResponse {
    private final String message = "게시글 수정을 성공적으로 완료했습니다.";
}
