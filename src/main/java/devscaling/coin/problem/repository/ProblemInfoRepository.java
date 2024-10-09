package devscaling.coin.problem.repository;

import devscaling.coin.problem.entity.ProblemInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProblemInfoRepository extends JpaRepository<ProblemInfo,Long> {
}
