package bg.softuni.mobiLeLeLe.web;

import bg.softuni.mobiLeLeLe.model.dto.UserLoginDto;
import bg.softuni.mobiLeLeLe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/users")
public class UserLoginController {

    @GetMapping("/login")
    public String getLoginPage() {

        return "auth-login";
    }

    @PostMapping("/login-error")
    public String onFailure(
            @ModelAttribute("username") String username,
            Model model
    ) {
        model.addAttribute("username", username);
        model.addAttribute("bad_credentials", true);

        return "auth-login";
    }


}
