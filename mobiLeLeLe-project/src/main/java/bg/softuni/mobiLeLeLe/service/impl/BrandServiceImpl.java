package bg.softuni.mobiLeLeLe.service.impl;

import bg.softuni.mobiLeLeLe.model.dto.BrandBasicDto;
import bg.softuni.mobiLeLeLe.model.dto.ModelBasicDto;
import bg.softuni.mobiLeLeLe.repository.BrandRepository;
import bg.softuni.mobiLeLeLe.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;

    @Autowired
    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }


    @Override
    public List<BrandBasicDto> getBrands() {

        return  this.brandRepository.findAll()
                .stream()
                .map(b -> new BrandBasicDto(b.getId(), b.getName(),
                        b.getModels().stream()
                                .map(m -> new ModelBasicDto(
                                        m.getId()
                                        , m.getName()
                                        , m.getCategory()
                                        , m.getStartYear()
                                        , m.getEndYear()
                                        , m.getImageUrl()
                                ))
                                .collect(Collectors.toSet())
                        ))
                .collect(Collectors.toList());
    }
}
