package devscaling.coin.domain.repository;

import devscaling.coin.domain.entity.Anonymous;
import devscaling.coin.domain.entity.QnaBoard;
import devscaling.coin.domain.entity.QnaComment;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class QnaCommentRepositoryTest {

    @Autowired
    QnaCommentRepository qnaCommentRepository;
    @Autowired
    QnaBoardRepository qnaBoardRepository;

    QnaBoard qnaBoard;
    QnaComment qnaComment;

    @BeforeEach
    void before(){
        qnaBoard = QnaBoard.builder()
                .title("시스템 오류")
                .content("장애 확인 바람")
                .build();
        qnaComment = QnaComment.builder()
                .content("조치했습니다.")
                .build();
    }

    @Test
    @DisplayName("qnaComment가 잘 저장되는지 테스트")
    void saveQnaCommentTest(){
        Long id = qnaCommentRepository.save(qnaComment).getId();

        assertThat(qnaComment.getContent())
                .isEqualTo(qnaCommentRepository.findById(id)
                        .get()
                        .getContent());
    }

    @Test
    @DisplayName("QnA게시판 - QnA댓글 간 연관관계 테스트")
    void correlationTest(){
        Long id = qnaBoardRepository.save(qnaBoard).getId();
        qnaComment.setQnaBoardQnaComment(qnaBoard);
        qnaCommentRepository.save(qnaComment);

        assertThat(qnaComment.getQnaBoards().getTitle())
                .isEqualTo(qnaBoardRepository.findById(id)
                        .get()
                        .getTitle());
        assertThat(qnaComment.getQnaBoards().getContent())
                .isEqualTo(qnaBoardRepository.findById(id)
                        .get()
                        .getContent());
    }

}