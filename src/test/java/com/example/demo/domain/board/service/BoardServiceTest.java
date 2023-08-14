package com.example.demo.domain.board.service;

import com.example.demo.domain.board.entity.Board;
import com.example.demo.domain.board.repository.BoardRepository;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.repository.MemberRepository;
import com.example.demo.global.advice.exception.EntityNotFoundException;
import com.example.demo.support.database.EnableDataBaseTest;
import com.example.demo.support.fixture.member.MemberFixture;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;


@EnableDataBaseTest
@DisplayName("BoardService 에서")
public class BoardServiceTest {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    public void setup() {
        Member member = memberRepository.save(MemberFixture.MINCHANG.toEntity());

        Board board1 = Board.builder()
                .member(member)
                .title("게시물 제목 1")
                .content("게시글 내용 1")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        Board board2 = Board.builder()
                .member(member)
                .title("게시물 제목 2")
                .content("게시글 내용 2")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

                boardRepository.saveAll(List.of(board1, board2));
    }

    @Test
    @Order(1)
    @DisplayName("게시물을 성공적으로 DB에 등록하는지 테스트")
    @Transactional
    @Rollback(value = true)
    void successSavedBoard() throws Exception {
        // given
        Member member = MemberFixture.MINCHANG.toEntity();

        Board board = Board.builder()
                .member(member)
                .title("게시물 제목 1")
                .content("게시글 내용 1")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        // when
        boardRepository.save(board);

        // then
        Assertions.assertThat(board.getId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    @DisplayName("게시물을 성공적으로 조회하는지 테스트")
    @Transactional
    @Rollback(value = true)
    void successFindBoardList() throws Exception {
        // given

        // when
        List<Board> boards = boardRepository.findAll();

        // then
        Assertions.assertThat(boards.size()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    @DisplayName("특정 게시물을 성공적으로 조회하는지 테스트")
    @Transactional
    @Rollback(value = true)
    void successFindBoardDetail() throws Exception {
        // given

        // when
        Board board = boardRepository.findById(1L).orElseThrow(EntityNotFoundException::new);

        // then
        Assertions.assertThat(board.getId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    @DisplayName("특정 게시물을 성공적으로 수정하는지 테스트")
    @Transactional
    @Rollback(value = true)
    void successUpdateBoard() throws Exception {
        // given

        // when

        // then
    }
}
