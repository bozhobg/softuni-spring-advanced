package bg.softuni.mobiLeLeLe.service;

import bg.softuni.mobiLeLeLe.model.dto.ModelBasicDto;

import java.util.List;

public interface ModelService {
    List<ModelBasicDto> getModelsById(Long brandId);
}
