package devscaling.coin.submit.repository;

import devscaling.coin.submit.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface PersonalStateRepository extends JpaRepository<PersonalState,Long> {
    //특정 달로 찾기
    List<PersonalState> findByApplyMonth(int applyMonth);

    //id로 submitboard 찾기?
    //List<SubmitBoard> findByPersonalState(PersonalState personalState);

    //특정 mem과 특정월
}
