package devscaling.coin.totalProgressState.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.Builder;
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
@Table(name="TOTAL_PROGRESS_STATE")
public class TotalProgressState {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="tps_id")
    private Long id;
    @Column(name="total_deposit_deducted")
    private int totalDepositDeducted;
    @Column(name="progress_day")
    private int progressDay;
    @Column(name="day_submit_percent")
    private double daySubmitPercent;
    //추가예정: 팀
    @CreatedDate
    @Column(name="created_at",updatable=false, nullable=false )
    private LocalDateTime createdAt;
    @LastModifiedDate
    @Column(name="updated_at")
    private LocalDateTime updatedAt;

}
