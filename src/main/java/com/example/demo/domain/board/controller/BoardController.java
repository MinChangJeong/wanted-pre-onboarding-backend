package com.example.demo.domain.board.controller;

import com.example.demo.domain.auth.entity.AuthenticateUser;
import com.example.demo.domain.auth.jwt.JwtAuthorization;
import com.example.demo.domain.board.dto.request.BoardRegisterRequest;
import com.example.demo.domain.board.dto.request.BoardUpdateRequest;
import com.example.demo.domain.board.dto.response.BoardDeleteResponse;
import com.example.demo.domain.board.dto.response.BoardRegisterResponse;
import com.example.demo.domain.board.dto.response.BoardResponse;
import com.example.demo.domain.board.dto.response.BoardUpdateResponse;
import com.example.demo.domain.board.service.BoardService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BoardController {
    BoardService boardService;

    /*
     * 게시글 등록
     * */
    @PostMapping
    public ResponseEntity<BoardRegisterResponse> registerBoard(@JwtAuthorization AuthenticateUser authenticateUser,
                                                               @Valid @RequestBody BoardRegisterRequest request) {
        return ResponseEntity.ok().body(boardService.registerBoard(authenticateUser, request));
    }

    /*
    * 게시글 목록 조회
    * */
    @GetMapping
    public ResponseEntity<Page<BoardResponse>> findAllBoard(@RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok().body(boardService.findAllBoard(pageable));
    }

    /*
    * 게시글 상세 조횐
    * */
    @GetMapping("/{boardId}")
    public ResponseEntity<BoardResponse> findBoardById(@PathVariable("boardId") final Long boardId) {
        return ResponseEntity.ok().body(boardService.findBoardById(boardId));
    }

    /*
    * 게시글 수정
    * */
    @PutMapping
    public ResponseEntity<BoardUpdateResponse> updateBoard(@JwtAuthorization AuthenticateUser authenticateUser,
                                                           @Valid @RequestBody BoardUpdateRequest request) {
        return ResponseEntity.ok().body(boardService.updateBoard(authenticateUser, request));
    }

    /*
    * 게시글 삭제
    * */
    @DeleteMapping("/{boardId}")
    public ResponseEntity<BoardDeleteResponse> deleteBoard(@JwtAuthorization AuthenticateUser authenticateUser,
                                                           @Valid @PathVariable("boardId") Long boardId) {
        return ResponseEntity.ok().body(boardService.deleteBoard(authenticateUser, boardId));
    }
}