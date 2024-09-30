package devscaling.coin.appy_state.repository;

// ApplyState 참조
import devscaling.coin.appy_state.model.ApplyState;

// DB와 상요작용 하기위한 JPA(CRUD 메소드를 제공)
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplyStateRepository extends JpaRepository<ApplyState, Integer> {
}
