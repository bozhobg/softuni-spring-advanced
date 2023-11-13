package bg.softuni.mobiLeLeLe.web;

import bg.softuni.mobiLeLeLe.exceptions.NotFoundException;
import bg.softuni.mobiLeLeLe.model.dto.OfferBasicDto;
import bg.softuni.mobiLeLeLe.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/offers")
public class OfferRestController {

    private final OfferService offerService;

    @Autowired
    public OfferRestController(OfferService offerService) {
        this.offerService = offerService;
    }

//    TODO: implement RESTFul API for interaction with web app -> CRUD ops, etc.
//    TODO: How to work with authenticated user over Rest API?

    @GetMapping("/all")
    public @ResponseBody List<OfferBasicDto> getAll() {
//        throw new NotFoundException("No entities found for query");

        return this.offerService.getBasicOfferDtos();
    }
}
