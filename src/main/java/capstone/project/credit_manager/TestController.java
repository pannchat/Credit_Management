package capstone.project.credit_manager;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

// k-cloud 로그인 테스트
@Controller
public class TestController {

    @GetMapping("/")
    public String test(Model model){
        return "/test";
    }
}
