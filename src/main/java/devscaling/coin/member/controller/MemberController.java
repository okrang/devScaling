package devscaling.coin.member.controller;

// Member, MemberService 참조를 위한 import
import devscaling.coin.member.model.Member;
import devscaling.coin.member.service.MemberService;

// Spring의 웹 관련 애너테이션(주로 RESTful API)
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/member")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    // ID 중복체크 API
    @GetMapping("/check-id/{personalId}")
    public ResponseEntity<String> checkId(@PathVariable String personalId){
        boolean isAvailable = memberService.isIdAvailable(personalId);
        return isAvailable
                ? ResponseEntity.ok("ID 사용가능")
                : ResponseEntity.status(HttpStatus.CONFLICT).body("ID 중복");
    }

    // 회원가입 API
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody Member member){
        memberService.signup(member);
        return ResponseEntity.ok("회원가입 성공");
    }

    // 로그인 API
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Member member){
        boolean success = memberService.login(member.getPersonalId(), member.getPassword());
        return success
                ? ResponseEntity.ok("로그인 성공")
                : ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 실패");
    }
}
