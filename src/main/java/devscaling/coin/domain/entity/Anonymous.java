package devscaling.coin.domain.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "ANONYMOUS")
public class Anonymous extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "anonymous_id")
    private Long id;

    @Column(length = 150, nullable = false)
    private String password;

    @OneToMany(mappedBy = "anonymousMember", orphanRemoval = true)
    @Builder.Default
    private List<QnaBoard> qnaBoards = new ArrayList<>();



}
