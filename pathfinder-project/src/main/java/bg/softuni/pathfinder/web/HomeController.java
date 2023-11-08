package bg.softuni.pathfinder.web;

import bg.softuni.pathfinder.util.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("/")
public class HomeController {

    private final CurrentUser currentUser;

    @Autowired
    public HomeController(CurrentUser currentUser) {
        this.currentUser = currentUser;
    }


    @GetMapping
    public ModelAndView getIndex() {

        ModelAndView mav = new ModelAndView("index");
        if (currentUser.isLogged()) mav.setViewName("redirect:/home");

        return mav;
    }

    @GetMapping("/home")
    public ModelAndView getHome() {

        ModelAndView mav = new ModelAndView("index");
        if (!currentUser.isLogged()) mav.setViewName("redirect:/");

        return mav;
    }

    @GetMapping("/about")
    public ModelAndView getAbout() {
        return new ModelAndView("about");
    }
}
