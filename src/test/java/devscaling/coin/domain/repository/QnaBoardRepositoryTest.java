package devscaling.coin.domain.repository;

import devscaling.coin.domain.entity.Anonymous;
import devscaling.coin.domain.entity.QnaBoard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
class QnaBoardRepositoryTest {

    @Autowired
    QnaBoardRepository qnaBoardRepository;
    @Autowired
    AnonymousRepository anonymousRepository;

    Anonymous anonymous;
    QnaBoard qnaBoard;

    @BeforeEach
    void before(){
        qnaBoard = QnaBoard.builder()
                .title("시스템 오류")
                .content("장애 확인 바람")
                .build();
        anonymous = Anonymous.builder()
                .password("1234")
                .build();
    }

    @Test
    @DisplayName("QnA게시판에 잘 저장되는지 테스트")
    void saveQnaBoardTest(){
        Long id = qnaBoardRepository.save(qnaBoard).getId();

        assertThat(qnaBoard.getContent())
                .isEqualTo(qnaBoardRepository
                        .findById(id)
                        .get()
                        .getContent());
        assertThat(qnaBoard.getTitle())
                .isEqualTo(qnaBoardRepository
                        .findById(id)
                        .get()
                        .getTitle());
    }

    @Test
    @DisplayName("익명유저 - QnA게시판간 연관관계 테스트")
    void correlationTest(){
        Long id = anonymousRepository.save(anonymous).getId();
        qnaBoard.setAnonymousQnaBoard(anonymous);
        qnaBoardRepository.save(qnaBoard);


        assertThat(qnaBoard.getAnonymousMember().getPassword())
                .isEqualTo(anonymousRepository.findById(id)
                        .get().getPassword());
    }

}