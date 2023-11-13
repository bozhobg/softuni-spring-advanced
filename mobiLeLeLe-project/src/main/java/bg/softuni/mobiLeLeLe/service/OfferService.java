package bg.softuni.mobiLeLeLe.service;

import bg.softuni.mobiLeLeLe.model.dto.OfferBasicDto;
import bg.softuni.mobiLeLeLe.model.dto.OfferCreateUpdateDto;
import bg.softuni.mobiLeLeLe.model.dto.OfferDetailsDto;

import java.util.List;

public interface OfferService {
    Long addOffer (OfferCreateUpdateDto offerCreateUpdateDto, String username);

    OfferCreateUpdateDto getOfferDto(Long id);

    List<OfferBasicDto> getBasicOfferDtos();

    OfferDetailsDto getOfferDetailsDto(Long id);

    Long findSellerIdByOfferId(Long offerId);
    void updateOffer(OfferCreateUpdateDto offerDto, Long offerId);

    void deleteOffer(Long offerId);
    boolean isUsernameOfferSeller(String username, Long offerId);
}
