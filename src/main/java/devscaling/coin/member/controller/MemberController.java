package devscaling.coin.member.controller;

// Member, MemberService 참조
import devscaling.coin.member.model.Member;
import devscaling.coin.member.service.MemberService;

// Spring의 웹 관련 애너테이션(주로 RESTful API)
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

// Final변수 Constructor Injection
import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/member")
public class MemberController {
    private final MemberService memberService;

    // ID 중복체크 API
    @GetMapping("/check-id/{personalId}")
    public ResponseEntity<String> checkId(@PathVariable String personalId){
        boolean isAvailable = memberService.isIdAvailable(personalId);
        return isAvailable
                ? ResponseEntity.ok("ID 사용가능")
                : ResponseEntity.status(HttpStatus.CONFLICT).body("ID 중복");
    }

    // 회원가입 API
    @PostMapping("/signup-req")
    public ResponseEntity<String> signup(@RequestBody Member member){
        memberService.signup(member);
        return ResponseEntity.ok("회원가입 성공");
    }

    // 로그인 API
    @PostMapping("/login-req")
    public ResponseEntity<Map<String, String>> login(@RequestBody Member member){
        boolean success = memberService.login(member.getPersonalId(), member.getPassword());
        if(success){
            int rank = memberService.getRank(member.getPersonalId());
            return ResponseEntity.ok(Map.of("message", "로그인 성공", "rank", String.valueOf(rank)));
        }else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "로그인 실패"));
        }
    }

    // 비밀번호 재설정 요청 API
    @PostMapping("/request-reset-password")
    public ResponseEntity<String> requestResetPassword(@RequestParam String email){
        memberService.sendResetPasswordEmail(email);
        return ResponseEntity.ok("비밀번호 재설정 이메일 발송완료");
    }

}
