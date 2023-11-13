package bg.softuni.mobiLeLeLe.web;

import bg.softuni.mobiLeLeLe.exceptions.DbException;
import bg.softuni.mobiLeLeLe.exceptions.ExceptionMessages;
import bg.softuni.mobiLeLeLe.model.dto.OfferCreateUpdateDto;
import bg.softuni.mobiLeLeLe.model.dto.OfferDetailsDto;
import bg.softuni.mobiLeLeLe.model.enums.Engine;
import bg.softuni.mobiLeLeLe.model.enums.Transmission;
import bg.softuni.mobiLeLeLe.service.BrandService;
import bg.softuni.mobiLeLeLe.service.OfferService;
//import bg.softuni.mobiLeLeLe.util.CurrentUser;
import bg.softuni.mobiLeLeLe.service.impl.MobileleUserDetailsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequestMapping("/offers")
public class OfferController {
    private final OfferService offerService;
    private final BrandService brandService;
    private final MobileleUserDetailsService mobileleUserDetailsService;


    @Autowired
    public OfferController(
            OfferService offerService,
            BrandService brandService,
            MobileleUserDetailsService mobileleUserDetailsService
    ) {
        this.offerService = offerService;
        this.brandService = brandService;
        this.mobileleUserDetailsService = mobileleUserDetailsService;
    }


    @GetMapping("/all")
    public String allLoad(ModelMap modelMap) {
        modelMap.addAttribute("offers", this.offerService.getBasicOfferDtos());

        return "offers";
    }

    @GetMapping("/add")
    public String addLoad(ModelMap modelMap) {
        getModelMapWithOfferAttributes(modelMap);
        if (!modelMap.containsAttribute("addBindingModel")) {
            modelMap.addAttribute("addBindingModel", new OfferCreateUpdateDto());
        }

        return "offer-add";
    }

    @PostMapping("/add")
    public String addSubmit(
            @Valid @ModelAttribute("addBindingModel") OfferCreateUpdateDto offerDto,
            BindingResult bindingResult,
            RedirectAttributes rAttrs,
            Principal principal
    ) {

        if (bindingResult.hasErrors()) {
            rAttrs.addFlashAttribute("addBindingModel", offerDto);
            rAttrs.addFlashAttribute(
                "org.springframework.validation.BindingResult.addBindingModel",
                    bindingResult
            );

            return "redirect:/offers/add";
        }

        String username = principal.getName();
        Long newOfferId = this.offerService.addOffer(offerDto, username);

        return "redirect:/offers/details/" + newOfferId;
    }

    @GetMapping("/details/{id}")
    public String detailsLoad(
            @PathVariable("id") Long id
            , ModelMap modelMap
    ) {
        OfferDetailsDto detailsDto = this.offerService.getOfferDetailsDto(id);
        modelMap.addAttribute("details", detailsDto);

        return "details";
    }

    @GetMapping("/update/{id}")
    public String updateLoad(
            @PathVariable(name = "id") Long offerId
            , ModelMap modelMap
            , Principal principal
    ) {

        if (!isUsernameOfferSellerOrAdmin(offerId, principal.getName())) {
            return "redirect:/offers/details/" + offerId;
        }

        getModelMapWithOfferAttributes(modelMap);
        if (!modelMap.containsAttribute("offer")) {
            modelMap.addAttribute("offer", this.offerService.getOfferDto(offerId));
        }

        return "update";
    }

    @PostMapping("/update/{id}")
    public String updateSubmit(
            @Valid @ModelAttribute("offer") OfferCreateUpdateDto offerDto,
            BindingResult bindingResult,
            RedirectAttributes rAttr,
            @PathVariable(name = "id") Long offerId,
            Principal principal
    ) {
        isUsernameOfferSellerOrAdmin(offerId, principal.getName());

        if (bindingResult.hasErrors()) {
            rAttr.addFlashAttribute("offer", offerDto);
            rAttr.addFlashAttribute(
                    "org.springframework.validation.BindingResult.offer",
                    bindingResult
            );

            return "redirect:/offers/update/" + offerId;
        }

        this.offerService.updateOffer(offerDto, offerId);

        return "redirect:/offers/details/" + offerId;
    }

    //        TODO: implement delete
    @GetMapping("/delete/{id}")
    public String deleteOffer(
            @PathVariable(name = "id") Long offerId,
            Principal principal
    ) {
        boolean isDeleted = false;

        if (isUsernameOfferSellerOrAdmin(offerId, principal.getName())) {
            this.offerService.deleteOffer(offerId);
        }

        return "redirect:/offers/all";

    }

    @ExceptionHandler(DbException.class)
    public ModelAndView handleDbExceptions(Exception e) {
        ModelAndView mav = new ModelAndView("error");
        mav.addObject("error", e.getMessage());

        return mav;
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ModelAndView handleUsernameNotAuthorized(Exception e) {
        ModelAndView mav = new ModelAndView("error");
        mav.addObject("error", e.getMessage());

        return mav;
    }

    private boolean isUsernameOfferSellerOrAdmin(Long offerId, String username) {
        boolean isUserAuthorized = this.offerService.isUsernameOfferSeller(username, offerId)
                || this.mobileleUserDetailsService.loadUserByUsername(username).getAuthorities()
                .stream().map(GrantedAuthority::getAuthority)
                .anyMatch(a -> a.equals("ROLE_ADMIN"));

        if (!isUserAuthorized) throw new UsernameNotFoundException(ExceptionMessages.USERNAME_NOT_AUTHORIZED);

        return true;
    }


    private void getModelMapWithOfferAttributes(ModelMap modelMap) {

        modelMap.addAttribute("brands", this.brandService.getBrands());
        modelMap.addAttribute("engines", Engine.values());
        modelMap.addAttribute("transmissions", Transmission.values());
    }

}
