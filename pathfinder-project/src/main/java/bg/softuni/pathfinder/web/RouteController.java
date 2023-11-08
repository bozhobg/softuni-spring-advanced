package bg.softuni.pathfinder.web;

import bg.softuni.pathfinder.model.dto.CommentAddBindingModel;
import bg.softuni.pathfinder.model.dto.RouteAddBindingModel;
import bg.softuni.pathfinder.model.dto.RouteDetailsView;
import bg.softuni.pathfinder.model.dto.RoutesWrapperView;
import bg.softuni.pathfinder.model.entity.enums.Level;
import bg.softuni.pathfinder.service.CategoryService;
import bg.softuni.pathfinder.service.RouteService;
import bg.softuni.pathfinder.util.CurrentUser;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/routes")
public class RouteController {

    private final RouteService routeService;
    private final CategoryService categoryService;
    private final CurrentUser currentUser;

    @Autowired
    public RouteController(
            RouteService routeService,
            CategoryService categoryService,
            CurrentUser currentUser
    ) {
        this.routeService = routeService;
        this.categoryService = categoryService;
        this.currentUser = currentUser;
    }

    @GetMapping
    public ModelAndView getRoutes(){
        ModelAndView mav = new ModelAndView("routes");
        RoutesWrapperView routes = this.routeService.getRoutesWrapper();
        mav.addObject("routes", routes);

        return mav;
    }

    @GetMapping("/details/{id}")
    public ModelAndView getRouteDetails(
            @PathVariable("id") Long routeId,
            Model model
    ) {
        RouteDetailsView routesDetailsView = this.routeService.getRouteDetailsView(routeId);

        if (!currentUser.isLogged()) return new ModelAndView("redirect:/users/login");
        if (routesDetailsView == null) return new ModelAndView("redirect:/routes");

        ModelAndView mav = new ModelAndView("route-details");
        model.addAttribute("routeDetails", routesDetailsView);

        if (!model.containsAttribute("commentBindingModel")){
            model.addAttribute("commentBindingModel", new CommentAddBindingModel());
        }

        mav.addAllObjects(model.asMap());

        return mav;
    }

    @GetMapping("/add")
    public ModelAndView getAddRoute(Model model) {
        if (!currentUser.isLogged()) return new ModelAndView("redirect:/users/login");
        if (!model.containsAttribute("addBindingModel")) {
            model.addAttribute("addBindingModel", new RouteAddBindingModel());
        }

        model.addAttribute("levels", Level.values());
        model.addAttribute("categories", this.categoryService.getListCategoryView());

        return new ModelAndView("add-route", model.asMap());
    }

    @PostMapping("/add")
    public ModelAndView postAddRoute(
            @Valid @ModelAttribute("addBindingModel") RouteAddBindingModel addBindingModel,
            BindingResult bindingResult,
            RedirectAttributes rAttr
    ) {
        if (!currentUser.isLogged()) return new ModelAndView("redirect:/users/login");

        if (bindingResult.hasErrors()) {
            rAttr.addFlashAttribute("addBidingModel", addBindingModel);
            rAttr.addFlashAttribute(
                    "org.springframework.validation.BindingResult.addBindingModel",
                    bindingResult
            );

            return new ModelAndView("redirect:/routes/add");
        }



        return new ModelAndView();
    }
}
