package devscaling.coin.submit.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.Builder;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@Builder
@EntityListeners(AuditingEntityListener.class)
@Table(name="SUBMIT_BOARD")
@NoArgsConstructor
@AllArgsConstructor
public class SubmitBoard {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private String language;
    private String code;
    private String member;
    // @Column(name="problemLink")
    private String problemLink;
    @CreatedDate
    @Column(name="created_at",updatable=false, nullable=false )
    private LocalDateTime createdAt;
    @LastModifiedDate
    @Column(name="updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ps_id")
    private PersonalState personalState;

    public void setPersonalStateSubmitBoard(PersonalState personalState){
        this.personalState=personalState;
        personalState.getSubmitBoardList().add(this);
    }


   // private Long member_id;




}
