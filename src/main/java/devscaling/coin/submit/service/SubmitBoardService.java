package devscaling.coin.submit.service;
import devscaling.coin.submit.DTO.*;
import devscaling.coin.submit.entity.SubmitBoard;
import devscaling.coin.submit.repository.SubmitBoardRepository;
import devscaling.coin.submit.entity.PersonalState;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubmitBoardService {
    private final SubmitBoardRepository submitboardrepository;
    //submitboard 저장
//    public SubmitBoard createSubmitBoard(SubmitBoard submitBoard,PersonalState personalState){
//        submitBoard.setPersonalStateSubmitBoard(personalState);
//        return submitboardrepository.save(submitBoard);
//    }
    public void createSubmitBoard(SubmitBoardDto dto){
        SubmitBoard submitBoard=new SubmitBoard();
        submitBoard.setTitle(dto.getTitle());
        submitBoard.setProblemLink(dto.getProblemLink());
        submitBoard.setContent(dto.getContent());
        submitBoard.setLanguage(dto.getLanguage());
        submitBoard.setCode(dto.getCode());
        submitBoard.setMember(dto.getMember());
        submitboardrepository.save(submitBoard);

    }
    public SubmitBoardResponseDto getSubmitBoardById(Long id){
        SubmitBoard submitBoard=submitboardrepository.findById(id).orElseThrow(()->new IllegalArgumentException("게시글을 찾을 수 없습니다. ID: " + id));
        return SubmitBoardResponseDto.builder()
                .id(submitBoard.getId())
                .title(submitBoard.getTitle())
                .content(submitBoard.getContent())
                .problemLink(submitBoard.getProblemLink())
                .language(submitBoard.getLanguage())
                .code(submitBoard.getCode())
                .createdAt(submitBoard.getCreatedAt())
                .updatedAt(submitBoard.getUpdatedAt())
                .build();

    }
//    public void deleteSubmitBoard(Long id) {
//        SubmitBoard submitBoard = getSubmitBoardById(id);
//        submitboardrepository.delete(submitBoard);
//    }

    //personalstate 하나로 여러건 조회
    public List<SubmitBoard> findByPersonalState(PersonalState personalState){
        return submitboardrepository.findByPersonalState(personalState);
    }


    //member_id, 다른 조건에 따른 조회






}
