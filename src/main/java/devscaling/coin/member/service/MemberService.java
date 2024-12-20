package devscaling.coin.member.service;

// 패스워드 보안 강화를 위한 Encoder
import devscaling.coin.member.DTO.LoginDTO;
import devscaling.coin.member.DTO.SignUpDTO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// Member, MemberRepository 참조를 위한 import
import devscaling.coin.member.model.Member;
import devscaling.coin.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

// Final변수 Constructor Injection
import lombok.RequiredArgsConstructor;

// Email 요청
import org.springframework.mail.SimpleMailMessage; // 메시지 정보 설정
import org.springframework.mail.javamail.JavaMailSender; // 이메일 전송

@RequiredArgsConstructor // Final찾아서 DI
@Service // 서비스 클래스
public class MemberService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    // ID 중복 체크 메소드
    public boolean isIdAvailable(String personalId){
        return memberRepository.findByUsername(personalId) == null;
    }

    // 회원가입 메소드
    public void signup(SignUpDTO signUpDTO){
        String username = signUpDTO.getUsername();
        String password = signUpDTO.getPassword();
        String name = signUpDTO.getName();
        String email = signUpDTO.getEmail();
        String nickname = signUpDTO.getNickname();
        String backjoonId = signUpDTO.getBackjoonId();

        Member data = new Member();

        data.setUsername(username);
        data.setPassword(passwordEncoder.encode(password));
        data.setName(name);
        data.setEmail(email);
        data.setNickname(nickname);
        data.setBackjoonId(backjoonId);

        memberRepository.save(data);
    }

    // 로그인 메소드
    public boolean login(LoginDTO loginDTO){
        String personalId = loginDTO.getPersonalId();
        String password = loginDTO.getPassword();

        Member member = memberRepository.findByUsername(personalId);
        if(member != null){
            // 디버깅을 위한 출력 (확인용)
            System.out.println("입력된 비밀번호: " + password);
            System.out.println("저장된 암호화된 비밀번호: " + member.getPassword());
            return passwordEncoder.matches(password, member.getPassword());
        }
        return false;
    }

    // 패스워드 재설정 요청 메소드
    public void sendResetPasswordEmail(String email){
        Member member = memberRepository.findByEmail(email);
    }

    // rank값 확인 메서드
    public String getRank(String personalId){
        Member member = memberRepository.findByUsername(personalId);
        return member != null ? member.getRole() : null;
    }
}
