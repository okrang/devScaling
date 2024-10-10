package devscaling.coin.domain.repository;

import devscaling.coin.domain.entity.Anonymous;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnonymousRepository extends JpaRepository<Anonymous, Long> {

}
