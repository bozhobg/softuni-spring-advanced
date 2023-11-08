package bg.softuni.pathfinder.web;

import bg.softuni.pathfinder.exceptions.IllegalEntityException;
import bg.softuni.pathfinder.exceptions.InvalidCredentialsException;
import bg.softuni.pathfinder.model.dto.UserLoginBindingModel;
import bg.softuni.pathfinder.model.dto.UserProfileView;
import bg.softuni.pathfinder.model.dto.UserRegistrationBindingModel;
import bg.softuni.pathfinder.service.UserService;
import bg.softuni.pathfinder.util.CurrentUser;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final CurrentUser currentUser;

    public UserController(UserService userService, CurrentUser currentUser) {
        this.userService = userService;
        this.currentUser = currentUser;
    }

    @GetMapping("/login")
    public ModelAndView getLogin(Model model) {
        if (currentUser.isLogged()) return new ModelAndView("redirect:/home");

        if (!model.containsAttribute("bindingModel")) {
            model.addAttribute("bindingModel", new UserLoginBindingModel());
        }

        return new ModelAndView("login", model.asMap());
    }

    @PostMapping("/login")
    public ModelAndView postLogin(
            @Valid UserLoginBindingModel bindingModel,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {
        if (currentUser.isLogged()) return new ModelAndView("redirect:/home");

        ModelAndView mav = new ModelAndView("redirect:/users/login");

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("bindingModel", bindingModel);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.bindingModel",
                    bindingResult
            );

            return mav;
        }

        try {
            this.userService.loginUser(bindingModel);
        } catch (InvalidCredentialsException e) {
            redirectAttributes.addFlashAttribute("bindingModel", bindingModel);
            return mav;
        }

        mav.setViewName("redirect:/home");

        return mav;
    }

    @GetMapping("/register")
    public ModelAndView getRegister(Model model) {
        if (currentUser.isLogged()) return new ModelAndView("redirect:/home");


        if (!model.containsAttribute("bindingModel")) {
            model.addAttribute("bindingModel", new UserRegistrationBindingModel());
        }

        return new ModelAndView("register", model.asMap());
    }

    @PostMapping("/register")
    public ModelAndView postRegister(
        @Valid @ModelAttribute("bindingModel") UserRegistrationBindingModel bindingModel,
        BindingResult bindingResult,
        RedirectAttributes redAttrs
    ) {
        if (currentUser.isLogged()) return new ModelAndView("redirect:/home");

        ModelAndView mav = new ModelAndView("redirect:/users/register");

        if (bindingResult.hasErrors()) {
            redAttrs.addFlashAttribute("bindingModel", bindingModel);
            redAttrs.addFlashAttribute(
                    "org.springframework.validation.BindingResult.bindingModel",
                    bindingResult);

            return mav;
        }

        try {
            this.userService.registerUser(bindingModel);
        } catch (Exception e) {
            return mav;
        }

        return new ModelAndView("redirect:/users/login");
    }

    @PostMapping("/logout")
    public ModelAndView postLogout() {
        if (!currentUser.isLogged()) new ModelAndView("redirect:/login");

        currentUser.logout();
        return new ModelAndView("redirect:/");
    }

    @GetMapping("/profile/{id}")
    public ModelAndView getProfile(@PathVariable(name = "id") Long userId) {

        ModelAndView mav = new ModelAndView("redirect:/");
        if (!currentUser.isLogged() || !currentUser.getId().equals(userId)) {
            return mav;
        }

        UserProfileView userProfileView;
        try {
            userProfileView = this.userService.getUserProfileDto(userId);
        } catch (IllegalEntityException e) {
            return mav;
        }

        mav.setViewName("profile");
        mav.addObject("userProfile", userProfileView);

        return mav;
    }
}
