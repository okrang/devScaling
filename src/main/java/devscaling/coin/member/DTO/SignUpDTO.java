package devscaling.coin.member.DTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignUpDTO {

    private String personalId;
    private String password;
    private String name;
    private String email;
    private String nickname;
    private String backjoonId;
}
