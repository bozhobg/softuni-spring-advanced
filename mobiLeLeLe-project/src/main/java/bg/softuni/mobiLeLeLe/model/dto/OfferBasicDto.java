package bg.softuni.mobiLeLeLe.model.dto;

import bg.softuni.mobiLeLeLe.model.enums.Engine;
import bg.softuni.mobiLeLeLe.model.enums.Transmission;

import java.math.BigDecimal;

public record OfferBasicDto(
        Long offerId
        , Engine engine
        , String imageUrl
        , Integer mileage
        , BigDecimal price
        , Transmission transmission
        , Integer year
        , String modelName
) {
}
