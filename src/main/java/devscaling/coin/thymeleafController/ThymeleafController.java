package devscaling.coin.thymeleafController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ThymeleafController {
    @GetMapping("/home")
    public String homePage(){
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
