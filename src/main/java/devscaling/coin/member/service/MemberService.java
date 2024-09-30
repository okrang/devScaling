package devscaling.coin.member.service;

// 패스워드 보안 강화를 위한 Encoder
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// Member, MemberRepository 참조를 위한 import
import devscaling.coin.member.model.Member;
import devscaling.coin.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor // Final찾아서 DI
@Service // 서비스 클래스
public class MemberService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder;

//    @Autowired
//    public MemberService(MemberRepository memberRepository, BCryptPasswordEncoder passwordEncoder){
//        this.memberRepository = memberRepository;
//        this.passwordEncoder = passwordEncoder;
//    }
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
}
