package bg.softuni.mobiLeLeLe.service.impl;

import bg.softuni.mobiLeLeLe.model.dto.ModelBasicDto;
import bg.softuni.mobiLeLeLe.repository.ModelRepository;
import bg.softuni.mobiLeLeLe.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ModelServiceImpl implements ModelService {
    private final ModelRepository modelRepository;

    @Autowired
    public ModelServiceImpl(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    @Override
    public List<ModelBasicDto> getModelsById(Long brandId) {

        return this.modelRepository.findByBrandId(brandId)
                .stream()
                .map(m -> new ModelBasicDto(
                        m.getId(),
                        m.getName(),
                        m.getCategory(),
                        m.getStartYear(),
                        m.getEndYear(),
                        m.getImageUrl()
                ))
                .collect(Collectors.toList());

    }
}
