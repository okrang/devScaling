package devscaling.coin.problem.service;
import devscaling.coin.problem.entity.ProblemInfo;
import devscaling.coin.problem.repository.ProblemInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProblemInfoService {
    private final ProblemInfoRepository probleminforepository;

    public ProblemInfo getProblemById(Long id){
        return probleminforepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("문제를 찾을 수 없습니다."));
    }

    public ProblemInfo createProblem(ProblemInfo problemInfo) {
        return probleminforepository.save(problemInfo);
    }
    //API통해서 삽입로직 필요
}
