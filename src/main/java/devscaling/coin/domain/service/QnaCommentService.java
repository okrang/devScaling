package devscaling.coin.domain.service;

import devscaling.coin.domain.dto.QnaCommentRequestDto;
import devscaling.coin.domain.entity.QnaBoard;
import devscaling.coin.domain.entity.QnaComment;
import devscaling.coin.domain.repository.QnaBoardRepository;
import devscaling.coin.domain.repository.QnaCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QnaCommentService {

    private final QnaBoardRepository qnaBoardRepository;
    private final QnaCommentRepository qnaCommentRepository;


    @Transactional
    public void createQnaComment(Long qnaBoardId, QnaCommentRequestDto qnaCommentRequestDto){
        QnaComment qnaComment = QnaComment.builder()
                .content(qnaCommentRequestDto.getContent())
                .build();

        // 관리자인지 검증하는 기능
        QnaBoard qnaBoard = qnaBoardRepository.findById(qnaBoardId).get();
        qnaComment.setQnaBoardQnaComment(qnaBoard);
        qnaCommentRepository.save(qnaComment);
    }

    @Transactional
    public void updateQnaComment(Long qnaBoardId , Long qnaCommentId, QnaComment qnaComment){
        /**
         * 이후 Querydsl 연결해서 해결할 예정
         */
    }

    @Transactional(readOnly = true)
    public QnaComment getQnaComment(Long qnaBoardId , Long qnaCommentId){
        /**
         * 이후 Querydsl 연결해서 해결할 예정
         */
        return null;
    }

    @Transactional(readOnly = true)
    public List<QnaComment> getQnaComments(Long qnaBoardId){
        /**
         * 이후 Querydsl 연결해서 해결할 예정
         */
        return null;
    }

    @Transactional
    public void deleteQnaComment(Long qnaBoardId , Long qnaCommentId){
        /**
         * 이후 Querydsl 연결해서 해결할 예정
         */
    }

}
