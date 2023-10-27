package bg.softuni.mobiLeLeLe.model.dto;

import bg.softuni.mobiLeLeLe.model.enums.Engine;
import bg.softuni.mobiLeLeLe.model.enums.Transmission;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record OfferCreateUpdateDto(
        Long id
        , String description
        , Engine engine
        , String imageUrl
        , Integer mileage
        , BigDecimal price
        , Transmission transmission
        , Integer year
        , LocalDateTime created
        , LocalDateTime modified
//        model and seller id or dtos ?
//        TODO: get id from option value
        , Long modelId
//        TODO: get id from session props
        , Long sellerId
) {
}
