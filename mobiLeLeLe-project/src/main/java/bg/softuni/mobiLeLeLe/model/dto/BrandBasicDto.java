package bg.softuni.mobiLeLeLe.model.dto;

import java.util.Set;

public record BrandBasicDto(Long id, String name, Set<ModelBasicDto> models) {
}
