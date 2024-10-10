package devscaling.coin.submit.service;
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
    public SubmitBoard createSubmitBoard(SubmitBoard submitBoard,PersonalState personalState){
        submitBoard.setPersonalStateSubmitBoard(personalState);
        return submitboardrepository.save(submitBoard);
    }
    public void deleteSubmitBoard(Long id) {
        SubmitBoard submitBoard = getSubmitBoardById(id);
        submitboardrepository.delete(submitBoard);
    }
    //하나 조회
    public SubmitBoard getSubmitBoardById(Long id){
        return submitboardrepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("SubmitBoard를 찾을 수 없습니다."));
    }
    //personalstate 하나로 여러건 조회
    public List<SubmitBoard> findByPersonalState(PersonalState personalState){
        return submitboardrepository.findByPersonalState(personalState);
    }


    //member_id, 다른 조건에 따른 조회






}
