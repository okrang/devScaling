package devscaling.coin.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "QNACOMMENT")
public class QnaComment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qnacomment_id")
    private Long id;

    @Column(length = 200, nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "qnaboard_id")
    private QnaBoard qnaBoards;

    public void setQnaBoardQnaComment(QnaBoard qnaBoards) {
        this.qnaBoards = qnaBoards;
        qnaBoards.getQnaComments().add(this);
    }

}
