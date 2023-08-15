package com.example.demo.domain.board.service;

import com.example.demo.domain.board.entity.Board;
import com.example.demo.domain.board.repository.BoardRepository;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.repository.MemberRepository;
import com.example.demo.global.advice.exception.EntityNotFoundException;
import com.example.demo.support.database.EnableDataBaseTest;
import com.example.demo.support.fixture.member.MemberFixture;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;



@EnableDataBaseTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("BoardService 에서")
public class BoardServiceTest {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @Order(1)
    @DisplayName("게시물을 성공적으로 DB에 등록하는지 테스트")
    @Rollback(value = false)
    void successSavedBoard() throws Exception {
        // given
        Member member = memberRepository.save(MemberFixture.MINCHANG.toEntity());

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
    @DisplayName("특정 게시물을 성공적으로 조회하는지 테스트")
    void successFindBoardDetail() throws Exception {
        // given

        // when
        Board board = boardRepository.findById(1L).orElseThrow(EntityNotFoundException::new);

        // then
        Assertions.assertThat(board.getId()).isEqualTo(1L);
    }

    @Test
    @Order(3)
    @DisplayName("게시물을 성공적으로 조회하는지 테스트")
    void successFindBoardList() throws Exception {
        // given

        // when
        List<Board> boards = boardRepository.findAll();

        // then
        Assertions.assertThat(boards.size()).isGreaterThan(0);
    }

    @Test
    @Order(4)
    @DisplayName("특정 게시물을 성공적으로 수정하는지 테스트")
    @Rollback(value = false)
    void successUpdateBoard() throws Exception {
        Board board = boardRepository.findById(1L).orElseThrow(EntityNotFoundException::new);

        board.setTitle("수정된 게시글 제목 1");

        Board boardUpdate = boardRepository.save(board);

        Assertions.assertThat(boardUpdate.getTitle()).isEqualTo("수정된 게시글 제목 1");
    }

    @Test
    @Order(5)
    @DisplayName("특정 게시물을 성공적으로 수정하는지 테스트")
    @Rollback(value = false)
    public void successDeleteBoard(){

        Board board = boardRepository.findById(1L).orElseThrow(EntityNotFoundException::new);

        boardRepository.delete(board);

        Optional<Board> deletedBoard = boardRepository.findById(1L);

        Assertions.assertThat(deletedBoard).isEmpty();
    }
}
