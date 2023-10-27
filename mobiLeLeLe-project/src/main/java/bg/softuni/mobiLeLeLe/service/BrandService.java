package bg.softuni.mobiLeLeLe.service;

import bg.softuni.mobiLeLeLe.model.dto.BrandBasicDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface BrandService {
    List<BrandBasicDto> getBrands();
}
