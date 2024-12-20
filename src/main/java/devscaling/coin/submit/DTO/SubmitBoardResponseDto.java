package devscaling.coin.submit.DTO;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter @Setter @Builder
public class SubmitBoardResponseDto {
    private Long id;
    private String title;
    private String content;
    private String problemLink;
    private String language;
    private String code;
    private String member;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
