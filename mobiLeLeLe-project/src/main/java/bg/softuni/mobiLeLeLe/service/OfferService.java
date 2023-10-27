package bg.softuni.mobiLeLeLe.service;

import bg.softuni.mobiLeLeLe.model.dto.OfferBasicDto;
import bg.softuni.mobiLeLeLe.model.dto.OfferCreateUpdateDto;
import bg.softuni.mobiLeLeLe.model.dto.OfferDetailsDto;

import java.util.List;

public interface OfferService {
    boolean addOffer (OfferCreateUpdateDto offerCreateUpdateDto, Long userId);

    OfferCreateUpdateDto getOfferDto(Long id);

    List<OfferBasicDto> getBasicOfferDtos();

    OfferDetailsDto getOfferDetailsDto(Long id);

    Long findSellerIdByOfferId(Long offerId);
    boolean updateOffer(OfferCreateUpdateDto offerDto, Long offerId);

    boolean deleteOffer(Long offerId);
}