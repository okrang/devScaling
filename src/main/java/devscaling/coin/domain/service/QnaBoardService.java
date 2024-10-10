package devscaling.coin.domain.service;

import devscaling.coin.domain.dto.QnaBoardRequestDto;
import devscaling.coin.domain.entity.Anonymous;
import devscaling.coin.domain.entity.QnaBoard;
import devscaling.coin.domain.repository.AnonymousRepository;
import devscaling.coin.domain.repository.QnaBoardRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * UPDATE, DELETE의 경우, 인증된 사용자만 삭제할 수 있도록 이후 추가로직 구현 필요
 */
@Service
@RequiredArgsConstructor
public class QnaBoardService {


    private final QnaBoardRepository qnaBoardRepository;
    private final AnonymousRepository anonymousRepository;


    @Transactional
    public void createQnaBoard(QnaBoardRequestDto qnaBoardRequestDto){
        QnaBoard qnaBoard = QnaBoard.builder()
                .title(qnaBoardRequestDto.getTitle())
                .content(qnaBoardRequestDto.getContent())
                .build();

        if(!qnaBoardRequestDto.getUserName().isEmpty()){


            return;
        }
        Anonymous anonymous = Anonymous.builder()
                .password(qnaBoardRequestDto.getPassword())
                .build();
        anonymousRepository.save(anonymous);
        qnaBoard.setAnonymousQnaBoard(anonymous);
        qnaBoardRepository.save(qnaBoard);
    }

    @Transactional
    public void updateQnaBoard(Long id, QnaBoard qnaBoard){
        QnaBoard findQnaBoard = qnaBoardRepository.findById(id).get();

        if(findQnaBoard == null){

        }

        findQnaBoard.updateQnaBoard(qnaBoard);
    }

    @Transactional(readOnly = true)
    public QnaBoard getQnaBoard(Long id){
        return qnaBoardRepository.findById(id).get();
    }

    @Transactional(readOnly = true)
    public List<QnaBoard> getQnaBoards(){
        return qnaBoardRepository.findAll();
    }

    @Transactional
    public void deleteQnaBoard(Long id){
        qnaBoardRepository.deleteById(id);
    }

}
