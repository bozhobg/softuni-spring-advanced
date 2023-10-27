package bg.softuni.mobiLeLeLe.model.dto;

import bg.softuni.mobiLeLeLe.model.enums.Engine;
import bg.softuni.mobiLeLeLe.model.enums.Transmission;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record OfferDetailsDto(
        Long offerId
        , String description
        , Engine engine
        , String imageUrl
        , Integer mileage
        , BigDecimal price
        , Transmission transmission
        , Integer year
        , LocalDateTime created
        , LocalDateTime modified
        , String brandName
        , String modelName
        , Integer modelYear
        , Long sellerId
        , String sellerFullName
) {
}
