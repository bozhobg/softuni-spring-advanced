package bg.softuni.pathfinder.web;

import bg.softuni.pathfinder.model.dto.CommentAddBindingModel;
import bg.softuni.pathfinder.service.CommentService;
import bg.softuni.pathfinder.service.RouteService;
import bg.softuni.pathfinder.util.CurrentUser;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;

@Controller
@RequestMapping
public class CommentController {

    private final CommentService commentService;
    private final RouteService routeService;
    private final CurrentUser currentUser;

    @Autowired
    public CommentController(CommentService commentService, RouteService routeService, CurrentUser currentUser) {
        this.commentService = commentService;
        this.routeService = routeService;
        this.currentUser = currentUser;
    }

    @PostMapping("/routes/details/{id}/add-comment")
    public ModelAndView postComment(
            @PathVariable("id") Long routeId,
            @Valid @ModelAttribute("commentBindingModel") CommentAddBindingModel commentBindingModel,
            BindingResult commentBindingResult,
            RedirectAttributes rAttrs
    ) {
        if (!currentUser.isLogged()) return new ModelAndView("redirect:/users/login");
        if (!this.routeService.routeExists(routeId)) {
            return new ModelAndView("redirect:/routes/add");
        }

        ModelAndView mav = new ModelAndView("redirect:/routes/details/" + routeId);

        if (commentBindingResult.hasErrors()) {
            rAttrs.addFlashAttribute("commentBindingModel", commentBindingModel);
            rAttrs.addFlashAttribute(
                    "org.springframework.validation.BindingResult.commentBindingModel",
                    commentBindingResult
            );

            return mav;
        }
//        user only message as binding model
        commentBindingModel.setApproved(false)
                .setCurrentUserId(currentUser.getId())
                .setRouteId(routeId);

        this.commentService.addComment(commentBindingModel);

        return mav;
    }
}
