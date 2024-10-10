package devscaling.coin.member.repository;

// Member 참조를 위한 import
import devscaling.coin.member.model.Member;

// DB와 상호작용 하기위한 JPA(CRUD 메서드를 제공)
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
    Member findByPersonalId(String personalId);
    Member findByEmail(String email);
}
