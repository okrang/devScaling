package devscaling.coin.submit.repository;
import jakarta.persistence.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import devscaling.coin.submit.entity.SubmitBoard;
import java.util.*;
import devscaling.coin.submit.entity.PersonalState;
@Repository
public interface SubmitBoardRepository extends JpaRepository<SubmitBoard, Long> {
    List<SubmitBoard> findByPersonalState(PersonalState personalState);
    //id
}
