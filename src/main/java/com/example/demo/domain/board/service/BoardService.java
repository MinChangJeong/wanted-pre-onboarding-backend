package com.example.demo.domain.board.service;

import com.example.demo.domain.auth.entity.AuthenticateUser;
import com.example.demo.domain.board.dto.request.BoardRegisterRequest;
import com.example.demo.domain.board.dto.request.BoardUpdateRequest;
import com.example.demo.domain.board.dto.response.BoardDeleteResponse;
import com.example.demo.domain.board.dto.response.BoardRegisterResponse;
import com.example.demo.domain.board.dto.response.BoardResponse;
import com.example.demo.domain.board.dto.response.BoardUpdateResponse;
import com.example.demo.domain.board.entity.Board;
import com.example.demo.domain.board.exception.NotAllowedControlBoardException;
import com.example.demo.domain.board.repository.BoardRepository;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.repository.MemberRepository;
import com.example.demo.global.advice.exception.EntityNotFoundException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BoardService {
    BoardRepository boardRepository;
    MemberRepository memberRepository;

    /*
     *  게시글 등록
     * */
    @Transactional
    public BoardRegisterResponse registerBoard(AuthenticateUser authenticateUser, BoardRegisterRequest request) {
        // [1] 게시글 등록 회원 정보 조회
        Member member = memberRepository.findById(authenticateUser.getMemberId()).orElseThrow(EntityNotFoundException::new);

        // [2] 게시글 등록
        boardRepository.save(
                Board.builder()
                    .member(member)
                    .title(request.getTitle())
                    .content(request.getContent())
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build()
        );

        return new BoardRegisterResponse();
    }

    /*
    * 게시글 목록 조회
    * */
    public Page<BoardResponse> findAllBoard(Pageable pageable) {
        Page<Board> boards = boardRepository.findAll(pageable);
        return boards.map(board -> board.toDTO(board));
    }

    /*
    * 게시글 상세 조회
    * */
    public BoardResponse findBoardById(Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(EntityNotFoundException::new);
        return board.toDTO(board);
    }

    /*
    * 게시글 수정
    * */
    @Transactional
    public BoardUpdateResponse updateBoard(AuthenticateUser authenticateUser, BoardUpdateRequest request) {
        // [1] 로그인한 회원 정보와 작성자의 정보가 일치하는지 확인
        Member member = memberRepository.findById(authenticateUser.getMemberId()).orElseThrow(EntityNotFoundException::new);
        Board board = boardRepository.findById(request.getBoardId()).orElseThrow(EntityNotFoundException::new);

        if(member.equals(board.getMember())) {
            board.setTitle(request.getTitle());
            board.setContent(request.getContent());
            board.setUpdatedAt(LocalDateTime.now());
            boardRepository.save(board);
            return new BoardUpdateResponse();
        }
        else {
            throw new NotAllowedControlBoardException();
        }
    }

    /*
    * 게시글 삭제
    */
    @Transactional
    public BoardDeleteResponse deleteBoard(AuthenticateUser authenticateUser, Long boardId) {
        // [1] 로그인한 회원 정보와 작성자의 정보가 일치하는지 확인
        Member member = memberRepository.findById(authenticateUser.getMemberId()).orElseThrow(EntityNotFoundException::new);
        Board board = boardRepository.findById(boardId).orElseThrow(EntityNotFoundException::new);

        if(member.equals(board.getMember())) {
            boardRepository.delete(board);
            return new BoardDeleteResponse();
        }
        else {
            throw new NotAllowedControlBoardException();
        }
    }
}