package devscaling.coin.domain.repository;


import devscaling.coin.domain.entity.Anonymous;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class AnonymousRepositoryTest {

    @Autowired
    AnonymousRepository anonymousRepository;

    Anonymous anonymous;

    @BeforeEach
    void before(){
        anonymous = Anonymous.builder()
                .password("1234")
                .build();
    }


    @Test
    @DisplayName("익명유저가 잘 저장되는지 테스트")
    void saveAnonymousTest(){
        Long id = anonymousRepository.save(anonymous).getId();

        assertThat(anonymous.getPassword())
                .isEqualTo(anonymousRepository.findById(id).
                        get().getPassword());
    }

}