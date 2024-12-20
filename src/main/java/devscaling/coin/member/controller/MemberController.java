package devscaling.coin.member.controller;

// Member, MemberService 참조
import devscaling.coin.member.DTO.LoginDTO;
import devscaling.coin.member.DTO.SignUpDTO;
import devscaling.coin.member.model.Member;
import devscaling.coin.member.service.MemberService;

// Spring의 웹 관련 애너테이션(주로 RESTful API)
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

// Final변수 Constructor Injection
import lombok.RequiredArgsConstructor;

import java.util.Map;

@Controller
@RequiredArgsConstructor
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
    @PostMapping("/signup")
    public String signup(@ModelAttribute SignUpDTO signUpDTO, Model model){
        memberService.signup(signUpDTO);
        model.addAttribute("signupMessage", "회원가입이 완료되었습니다.");
        return "home";
    }

    // 로그인 API
//    @PostMapping("/login")
//    public String login(@ModelAttribute LoginDTO loginDTO, Model model){
//        boolean success = memberService.login(loginDTO);
//        if(success){
//            return "user/apply/mypage";
//        }else {
//            model.addAttribute("loginFail", "일치하는 회원정보가 없습니다.");
//            return "home";
//        }
//    }

    // 비밀번호 재설정 요청 API
    @PostMapping("/request-reset-password")
    public ResponseEntity<String> requestResetPassword(@RequestParam String email){
        memberService.sendResetPasswordEmail(email);
        return ResponseEntity.ok("비밀번호 재설정 이메일 발송완료");
    }

}
