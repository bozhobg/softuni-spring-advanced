package bg.softuni.mobiLeLeLe.web;

import bg.softuni.mobiLeLeLe.model.dto.OfferCreateUpdateDto;
import bg.softuni.mobiLeLeLe.model.dto.OfferDetailsDto;
import bg.softuni.mobiLeLeLe.model.enums.Engine;
import bg.softuni.mobiLeLeLe.model.enums.Transmission;
import bg.softuni.mobiLeLeLe.service.BrandService;
import bg.softuni.mobiLeLeLe.service.OfferService;
import bg.softuni.mobiLeLeLe.util.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/offers")
public class OfferController {
    private final OfferService offerService;
    private final BrandService brandService;
    private final CurrentUser currentUser;

    @Autowired
    public OfferController(
            OfferService offerService
            , BrandService brandService
            , CurrentUser currentUser) {
        this.offerService = offerService;
        this.brandService = brandService;
        this.currentUser = currentUser;
    }


    @GetMapping("/all")
    public String allLoad(ModelMap modelMap) {
        modelMap.addAttribute("offers", this.offerService.getBasicOfferDtos());

        return "offers";
    }

    @GetMapping("/add")
    public String addLoad(
            ModelMap modelMap
    ) {

        if (!currentUser.isLogged()) return "redirect:/users/login";

        getModelMapWithOfferAttributes(modelMap);

        return "offer-add";
    }

    @PostMapping("/add")
    public String addSubmit(
            OfferCreateUpdateDto offerDto
    ) {
//        TODO: resolve return of status codes
        RedirectView rv = new RedirectView();
        boolean isCreated = this.offerService.addOffer(offerDto, currentUser.getId());

        if (isCreated) {
            rv.setStatusCode(HttpStatus.CREATED);
//            mam = new ModelAndView("redirect:/offers");
//            mam.setStatus(HttpStatus.CREATED);

            return "redirect:/offers/all";
        } else {
            rv = new RedirectView("/offers/add");
            rv.setStatusCode(HttpStatus.BAD_REQUEST);

            return "redirect:/offers/add";
        }

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
    ) {

        if (!isAuthorizedUser(offerId)) {
            return "redirect:/offers/details/" + offerId;
        }

        getModelMapWithOfferAttributes(modelMap);
        modelMap.addAttribute("offer", this.offerService.getOfferDto(offerId));

        return "update";
    }

    @PostMapping("/update/{id}")
    public String updateSubmit(
            OfferCreateUpdateDto offerDto,
            @PathVariable(name = "id") Long offerId
    ) {

        boolean isUpdated = this.offerService.updateOffer(offerDto, offerId);


        return isUpdated
                ? "redirect:/offers/details/" + offerId
                : "redirect:/offers/update/" + offerId;
    }

    //        TODO: implement delete
    @GetMapping("/delete/{id}")
    public String deleteOffer(
            @PathVariable(name = "id") Long offerId
    ) {

        boolean isDeleted = false;

        if (isAuthorizedUser(offerId)) {
            isDeleted = this.offerService.deleteOffer(offerId);
        }

        return isDeleted
                ? "redirect:/offers/all"
                : "redirect:/offers/details/" + offerId;

    }

    private boolean isAuthorizedUser(Long offerId) {

        return currentUser.isAdmin() ||
                (currentUser.isLogged()
                        && currentUser.getId()
                        .equals(this.offerService.findSellerIdByOfferId(offerId)));
    }

    private void getModelMapWithOfferAttributes(ModelMap modelMap) {

        modelMap.addAttribute("brands", this.brandService.getBrands());
        modelMap.addAttribute("engines", Engine.values());
        modelMap.addAttribute("transmissions", Transmission.values());
    }

}
