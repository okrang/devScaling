package devscaling.coin.submit.DTO;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Getter @Setter @Data
public class SubmitBoardDto {
    private String title;
    private String content;
    private String problemLink;
    private String language;
    private String code;
    private String member;

}
