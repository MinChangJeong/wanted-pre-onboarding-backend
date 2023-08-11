package com.example.demo.domain.board.controller;

import com.example.demo.domain.board.dto.request.BoardDeleteRequest;
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
     * 회원 등록
     * */
    @PostMapping
    public ResponseEntity<BoardRegisterResponse> registerBoard(@Valid @RequestBody BoardRegisterRequest request) {
        return ResponseEntity.ok().body(boardService.registerBoard(request));
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
    public ResponseEntity<BoardUpdateResponse> updateBoard(@Valid @RequestBody BoardUpdateRequest request) {
        return ResponseEntity.ok().body(boardService.updateBoard(request));
    }

    /*
    * 게시글 삭제
    * */
    @DeleteMapping
    public ResponseEntity<BoardDeleteResponse> deleteBoard(@Valid @RequestBody BoardDeleteRequest request) {
        return ResponseEntity.ok().body(boardService.deleteBoard(request));
    }
}