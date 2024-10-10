package devscaling.coin.toatlProgressState.service;
import devscaling.coin.toatlProgressState.entity.TotalProgressState;
import devscaling.coin.toatlProgressState.repository.TotalProgressStateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TotalProgressStateService {

    private final TotalProgressStateRepository totalprogressstate;
    //오늘의 총 진행상황 삽입메소드??
//    public TotalProgressState saveTPS(){
//
//    }


}
