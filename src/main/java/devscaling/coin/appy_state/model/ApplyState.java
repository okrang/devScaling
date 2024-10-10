package devscaling.coin.appy_state.model;

// Lombok 라이브러리
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NoArgsConstructor;

// 데이터베이스와의 매핑을 통해 데이터 저장 및 조회
import jakarta.persistence.*;

// NotNull 유효성 검사
import jakarta.validation.constraints.NotNull;

// LocalDateTime
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// Member의 PK참조
import devscaling.coin.member.model.Member;

/*
    [신청상태]
    id :            ApplyState의 Key값
    selectTeam :    신청팀
    payConfirm :    결제여부
    applyMonth :    신청월
    createdAt :     생성된 날짜
    updatedAt :     업데이트된 날짜
    memberId  :     Member와 연결하기 위한 FK
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class ApplyState {
    @Id // 기본키로 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동증가 설정
    @Column(name = "id", updatable = false)
    private int id;

    @NotNull
    @Column(name = "select_team", columnDefinition = "VARCHAR(5)")
    private String selectTeam;

    @Column(name = "pay_confirm", columnDefinition = "BOOLEAN")
    private boolean payConfirm;

    @NotNull
    @Column(name = "apply_month", columnDefinition = "CHAR(7)")
    private String applyMonth;

    // TIMESTAMP(0) 시간초의 소수점 아래 제거
    @Column(name = "created_at", columnDefinition = "TIMESTAMP(0)", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP(0)")
    private LocalDateTime updatedAt;

    @NotNull
    @ManyToOne(cascade = CascadeType.REMOVE) // 참조테이블 레코드가 삭제되면 같이 삭제
    @JoinColumn(name = "member_id", nullable = false)
    private Member memberId;

    //레코드 생성, 업데이트 자동 입력
    @PrePersist
    protected void onCreate(){
        createdAt = LocalDateTime.now();
        updatedAt = createdAt;
    }

    @PreUpdate
    protected void onUpdate(){
        updatedAt = LocalDateTime.now();
    }
}
