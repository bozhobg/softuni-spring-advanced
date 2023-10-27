package bg.softuni.mobiLeLeLe.web;

import bg.softuni.mobiLeLeLe.model.dto.UserLoginDto;
import bg.softuni.mobiLeLeLe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserLoginController {


    private final UserService userService;

    @Autowired
    public UserLoginController(UserService userService) {

        this.userService = userService;
    }

    @GetMapping("/login")
    public String getLoginPage() {

        return "auth-login";
    }

    @PostMapping("/login")
    public String loginUser(UserLoginDto userLoginDto) {

        boolean isLoggedIn = this.userService.isLoginValid(userLoginDto);

        return isLoggedIn ? "redirect:/" : "redirect:/users/login";
    }

    @GetMapping("/logout")
    public String logout() {
        userService.logoutUser();
        return "index";
    }
}
