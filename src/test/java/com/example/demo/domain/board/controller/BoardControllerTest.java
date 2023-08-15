package com.example.demo.domain.board.controller;

import com.example.demo.domain.board.dto.request.BoardRegisterRequest;
import com.example.demo.domain.board.dto.request.BoardUpdateRequest;
import com.example.demo.domain.board.dto.response.BoardDeleteResponse;
import com.example.demo.domain.board.dto.response.BoardRegisterResponse;
import com.example.demo.domain.board.dto.response.BoardResponse;
import com.example.demo.domain.board.dto.response.BoardUpdateResponse;
import com.example.demo.domain.board.service.BoardService;
import com.example.demo.support.docs.RestDocumentTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;
import java.util.List;

import static com.example.demo.support.docs.ApiDocumentUtils.getDocumentRequest;
import static com.example.demo.support.docs.ApiDocumentUtils.getDocumentResponse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

    @Test
    @DisplayName("게시물 목록을 성공적으로 조회하는가?")
    void successFindAllBoard() throws Exception {
        // given
        int page = 0;
        int size = 10;

        Pageable pageable = PageRequest.of(page, size);

        List<BoardResponse> boardResponses = List.of(
                BoardResponse.builder()
                    .boardId(1L)
                    .title("게시글 제목 1")
                    .content("게시글 내용 1")
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build(),
                BoardResponse.builder()
                        .boardId(1L)
                        .title("게시글 제목 1")
                        .content("게시글 내용 1")
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .build()
        );

        Page<BoardResponse> response = new PageImpl<>(boardResponses);

        when(boardService.findAllBoard(pageable))
                .thenReturn(response);

        // when
        ResultActions perform =
                mockMvc.perform(
                        get("/board")
                            .param("page", String.valueOf(page))
                            .param("size", String.valueOf(size))
                );

        // then
        perform.andExpect(status().isOk());

        // docs
        perform.andDo(print())
                .andDo(document("find all board",
                        getDocumentRequest(),
                        getDocumentResponse()));
    }

    @Test
    @DisplayName("특정 게시물을 성공적으로 조회하는가?")
    void successFindBoardDetail() throws Exception {
        // given
        BoardResponse response = BoardResponse.builder()
                .boardId(1L)
                .title("게시글 제목 1")
                .content("게시글 내용 1")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        when(boardService.findBoardById(1L))
                .thenReturn(response);

        // when
        ResultActions perform =
                mockMvc.perform(
                        get("/board/{boardId}", 1L)
                );

        // then
        perform.andExpect(status().isOk());

        // docs
        perform.andDo(print())
                .andDo(document("find board by id",
                        getDocumentRequest(),
                        getDocumentResponse()));
    }

    @Test
    @DisplayName("게시물을 성공적으로 수정하는가?")
    void successUpdateBoard() throws Exception {
        // given
        BoardUpdateRequest request = new BoardUpdateRequest(
                1L,
                1L,
                "게시글 제목 1",
                "게시글 내용 1"
        );

        when(boardService.updateBoard(any()))
                .thenReturn(new BoardUpdateResponse());

        // when
        ResultActions perform =
                mockMvc.perform(
                        put("/board")
                            .content(toRequestBody(request))
                            .contentType(MediaType.APPLICATION_JSON)
                );

        // then
        perform.andExpect(status().isOk());

        // docs
        perform.andDo(print())
                .andDo(document("update board",
                        getDocumentRequest(),
                        getDocumentResponse()));
    }

    @Test
    @DisplayName("게시물을 성공적으로 삭제하는가?")
    void successDeleteBoard() throws Exception {
        // given
        BoardDeleteRequest request = new BoardDeleteRequest(
                1L,
                1L
        );

        when(boardService.deleteBoard(any()))
                .thenReturn(new BoardDeleteResponse());

        // when
        ResultActions perform =
                mockMvc.perform(
                        delete("/board")
                            .content(toRequestBody(request))
                            .contentType(MediaType.APPLICATION_JSON)
                );

        // then
        perform.andExpect(status().isOk());

        // docs
        perform.andDo(print())
                .andDo(document("delete board",
                        getDocumentRequest(),
                        getDocumentResponse()));
    }
}
