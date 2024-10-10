package devscaling.coin.problem.entity;

import lombok.Builder;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
//camelCase
@Entity
@Getter
@Setter
@ToString
@Builder
@Table(name="PROBLEM_INFO")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class ProblemInfo {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="pi_id")
    private Long id;
    @Column(name="problem_link")
    private String problemLink;
    @CreatedDate
    @Column(name="created_at",updatable=false, nullable=false )
    private LocalDateTime createdAt;
    @LastModifiedDate
    @Column(name="updated_at")
    private LocalDateTime updatedAt;



}
