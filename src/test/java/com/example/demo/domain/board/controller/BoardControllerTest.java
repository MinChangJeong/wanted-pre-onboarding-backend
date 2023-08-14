package com.example.demo.domain.board.controller;

import com.example.demo.domain.board.dto.request.BoardRegisterRequest;
import com.example.demo.domain.board.dto.response.BoardRegisterResponse;
import com.example.demo.domain.board.service.BoardService;
import com.example.demo.support.docs.RestDocumentTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static com.example.demo.support.docs.ApiDocumentUtils.getDocumentRequest;
import static com.example.demo.support.docs.ApiDocumentUtils.getDocumentResponse;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BoardController.class)
@DisplayName("BoardController 에서")
class BoardControllerTest extends RestDocumentTest {
    @MockBean private BoardService boardService;
    @Test
    @DisplayName("게시물을 성공적으로 등록하는가?")
    void successRegisterBoard() throws Exception {
        // given
        BoardRegisterRequest request
                = new BoardRegisterRequest(
                        1L,
                "게시글 제목 1",
                "게시글 내용 1");

        when(boardService.registerBoard(request))
                .thenReturn(new BoardRegisterResponse());

        // when
        ResultActions perform =
                mockMvc.perform(
                        post("/board")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(toRequestBody(request))
                );

        // then
        perform.andExpect(status().isOk());

        // docs
        perform.andDo(print())
                .andDo(document("register board",
                        getDocumentRequest(),
                        getDocumentResponse()));
    }
}
