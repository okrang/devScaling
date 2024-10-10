package devscaling.coin.member.service;

// 패스워드 보안 강화를 위한 Encoder
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
        return memberRepository.findByPersonalId(personalId) == null;
    }

    // 회원가입 메소드
    public void signup(Member member){
        String encodedPassword = passwordEncoder.encode(member.getPassword());
        member.setPassword(encodedPassword);
        memberRepository.save(member);
    }

    // 로그인 메소드
    public boolean login(String personalId, String password){
        Member member = memberRepository.findByPersonalId(personalId);
        if(member != null){
            return passwordEncoder.matches(password, member.getPassword());
        }
        return false;
    }

    // 패스워드 재설정 요청 메소드
    public void sendResetPasswordEmail(String email){
        Member member = memberRepository.findByEmail(email);
    }

    // rank값 확인 메서드
    public int getRank(String personalId){
        Member member = memberRepository.findByPersonalId(personalId);
        return member != null ? member.getRank() : null;
    }
}
