package bg.softuni.mobiLeLeLe.web;

import bg.softuni.mobiLeLeLe.model.dto.BrandBasicDto;
import bg.softuni.mobiLeLeLe.service.BrandService;
import bg.softuni.mobiLeLeLe.service.ModelService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/brands")
public class BrandsController {

    private final BrandService brandService;
    private final ModelService modelService;

    @Autowired
    public BrandsController(
            BrandService brandService,
            ModelService modelService) {

        this.brandService = brandService;
        this.modelService = modelService;
    }

    @GetMapping("/all")
    public String all(
            ModelMap modelMap
//            , @ModelAttribute("brandId") Long brandInd
            ) {

        modelMap.addAttribute("brands", this.brandService.getBrands());
//        modelMap.addAttribute("models", this.modelService.getModelsById(brandInd));


        return "brands";
    }

}
