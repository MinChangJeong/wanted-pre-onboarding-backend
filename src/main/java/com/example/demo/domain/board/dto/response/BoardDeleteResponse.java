package com.example.demo.domain.board.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardDeleteResponse {
    private final String message = "게시글 삭제를 성공적으로 완료했습니다.";
}
