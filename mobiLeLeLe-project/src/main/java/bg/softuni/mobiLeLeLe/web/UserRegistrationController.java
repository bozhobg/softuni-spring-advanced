package bg.softuni.mobiLeLeLe.web;

import bg.softuni.mobiLeLeLe.model.dto.UserRegistrationDto;
import bg.softuni.mobiLeLeLe.model.enums.Role;
import bg.softuni.mobiLeLeLe.repository.UserRoleRepository;
import bg.softuni.mobiLeLeLe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserRegistrationController {

    private final UserService userService;
    private final UserRoleRepository userRoleRepository;

    @Autowired
    public UserRegistrationController(
            UserService userService,
            UserRoleRepository userRoleRepository) {
        this.userService = userService;
        this.userRoleRepository = userRoleRepository;
    }

    @ModelAttribute("roles")
    public Role[] roles() {
        return Role.values();
    }

    @GetMapping("/register")
    public String getRegisterPage() {
        return "auth-register";
    }

    @PostMapping("/register")
    public String register(UserRegistrationDto userRegistrationDto) {
        userService.registerUser(userRegistrationDto);

        return "redirect:/users/login";
    }

}
