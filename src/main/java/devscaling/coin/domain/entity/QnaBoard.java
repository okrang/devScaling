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
@Table(name = "qna_board")
public class QnaBoard extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qnaboard_id")
    private Long id;

    @Column(length = 50)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;


    @OneToMany(mappedBy = "qnaBoards", orphanRemoval = true)
    @Builder.Default
    private List<QnaComment> qnaComments = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "anonymous_id")
    private Anonymous anonymousMember;

    public void setAnonymousQnaBoard(Anonymous anonymous) {
        this.anonymousMember = anonymous;
        anonymous.getQnaBoards().add(this);
    }

    //일반고객 연관관계 설정 필요


    public void updateQnaBoard(QnaBoard qnaBoard){
        this.title = qnaBoard.getTitle();
        this.content = qnaBoard.getContent();
    }

}
