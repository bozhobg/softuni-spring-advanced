package bg.softuni.mobiLeLeLe.model.dto;

import bg.softuni.mobiLeLeLe.model.enums.CategoryEnum;

public record ModelBasicDto(
        Long id
        , String name
        , CategoryEnum category
        , Integer startYear
        , Integer endYear
        , String imageUrl
) {
}
