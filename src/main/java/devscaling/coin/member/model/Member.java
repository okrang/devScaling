package devscaling.coin.member.model;

// Lombok 라이브러리
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NoArgsConstructor;

// 데이터베이스와의 매핑을 통해 데이터 저장 및 조회
import jakarta.persistence.*;

// 이메일, NotNull 유효성 검사
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

// created_at, updated_at 필드 타입
import java.time.LocalDateTime;

// ApplyState 참조
import java.util.Set;
import devscaling.coin.appy_state.model.ApplyState;

/*
    [멤버]
    id :            Member의 Key값
    username :    로그인 id
    password :      로그인 pw
    name :          본명
    email :         이메일
    nickname :      닉네임
    backjoonId :    백준아이디
    createdAt :     생성된 날짜
    updatedAt :     업데이트된 날짜
    rank :          Member의 권한 관리 필드
 */

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class Member {
    @Id // 기본키로 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 증가 설정
    @Column(name = "id", updatable = false)
    private int id;

    @NotNull // null값 불가
    @Column(name = "username", columnDefinition = "VARCHAR(20)")
    private String username;

    @NotNull
    @Column(name = "password", columnDefinition = "VARCHAR(255)")
    private String password;

    @NotNull
    @Column(name = "name", columnDefinition = "VARCHAR(10)")
    private String name;

    @Email // 유효한 이메일 형식
    @NotNull
    @Column(name = "email", columnDefinition = "VARCHAR(100)")
    private String email;

    @NotNull
    @Column(name = "nickname", columnDefinition = "VARCHAR(20)")
    private String nickname;

    @NotNull
    @Column(name = "backjoon_id", columnDefinition = "VARCHAR(20)")
    private String backjoonId;

    // TIMESTAMP(0) 시간초의 소수점 아래 제거
    @Column(name = "created_at", columnDefinition = "TIMESTAMP(0)", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP(0)")
    private LocalDateTime updatedAt;

    @Column(name = "role", columnDefinition = "VARCHAR(20)")
    private String role;

    @OneToMany(mappedBy = "memberId", cascade = CascadeType.ALL)
    private Set<ApplyState> applyState;

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
