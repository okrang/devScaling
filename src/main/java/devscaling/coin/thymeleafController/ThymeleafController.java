package devscaling.coin.thymeleafController;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ThymeleafController {
    @GetMapping("/home")
    public String homePage(Model model){
        // 현재 인증된 사용자를 가져옵니다.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // 인증된 사용자가 있을 경우(로그인한 상태)
        boolean isLoggedIn = authentication != null && authentication.isAuthenticated();

        // isLoggedIn 값을 모델에 추가해서 템플릿으로 전달
        model.addAttribute("isLoggedIn", isLoggedIn);

        return "home";
    }

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @GetMapping("/signup")
    public String signupPage(){
        return "signup";
    }

    @GetMapping("/find-password")
    public String findPasswordPage(){
        return "find-password";
    }

    @GetMapping("/admin/dashboard")
    public String adminDashboardPage(){
        return "admin/dashboard";
    }

    @GetMapping("/guest/home")
    public String guestHomePage(){
        return "guest/home";
    }

    @GetMapping("/user/apply/dashboard")
    public String userApplyDashboardPage(){
        return "user/apply/dashboard";
    }

    @GetMapping("/user/non-apply/mypage")
    public String userNonApplyMyPagePage(){
        return "user/non-apply/mypage";
    }
}
