package devscaling.coin.submit.service;
import devscaling.coin.submit.entity.PersonalState;
import devscaling.coin.submit.entity.SubmitBoard;
import devscaling.coin.submit.repository.PersonalStateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@RequiredArgsConstructor
public class PersonalStateService {
    private final PersonalStateRepository personalStateRepository;
    //제출율/차감/남은 보증금 업데이트: submitboard
//    public PersonalState savePersonalState(List<SubmitBoard> submitBoardList){
//
//    }
//제출율은 퍼센트인데 해당 월,submitboard의 개수
    //



    //제출월과 member로 쿼리 개발 필요

}
