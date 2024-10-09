package devscaling.coin.submit.entity;

import jakarta.persistence.Entity;
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
import java.util.*;

@Entity
@Getter
@Setter
@ToString
@Builder
@Table(name="PERSONAL_STATE")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class PersonalState {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ps_id")
    private Long id;

    @Column(name="deposit_deducted")
    private int depositDeducted;
    @Column(name="submit_percent")
    private double submitPercent;
    @Column(name="deposit_balance")
    private int depositBalance;
    @Column(name="apply_team")
    private String applyTeam;
    @Column(name="apply_month")
    private int applyMonth;
    @CreatedDate
    @Column(name="created_at", updatable=false, nullable=false )
    private LocalDateTime createdAt;
    @LastModifiedDate
    @Column(name="updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy="personalState",orphanRemoval = true)
    @Builder.Default
    private List<SubmitBoard> submitBoardList=new ArrayList<>();

   // private long member_id;

    //memberid
}
