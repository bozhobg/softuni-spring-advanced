package bg.softuni.mobiLeLeLe.service.impl;

import bg.softuni.mobiLeLeLe.exceptions.ExceptionMessages;
import bg.softuni.mobiLeLeLe.exceptions.NotFoundException;
import bg.softuni.mobiLeLeLe.model.dto.OfferBasicDto;
import bg.softuni.mobiLeLeLe.model.dto.OfferCreateUpdateDto;
import bg.softuni.mobiLeLeLe.model.dto.OfferDetailsDto;
import bg.softuni.mobiLeLeLe.model.entity.BasicEntity;
import bg.softuni.mobiLeLeLe.model.entity.Offer;
import bg.softuni.mobiLeLeLe.repository.ModelRepository;
import bg.softuni.mobiLeLeLe.repository.OfferRepository;
import bg.softuni.mobiLeLeLe.repository.UserRepository;
import bg.softuni.mobiLeLeLe.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl implements OfferService {
    private final OfferRepository offerRepository;
    private final ModelRepository modelRepository;
    private final UserRepository userRepository;

    @Autowired
    public OfferServiceImpl(
            OfferRepository offerRepository
            , ModelRepository modelRepository
            , UserRepository userRepository
    ) {
        this.offerRepository = offerRepository;
        this.modelRepository = modelRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Long addOffer(OfferCreateUpdateDto offerCreateUpdateDto, String username) {

        Offer newOffer = this.offerRepository.save(mapFromOfferCreateDto(offerCreateUpdateDto, username));

        return newOffer.getId();
    }

    @Override
    public OfferCreateUpdateDto getOfferDto(Long id) {
        return mapToOfferCreateDto(this.offerRepository.findById(id).orElseThrow());
    }

    @Override
    public List<OfferBasicDto> getBasicOfferDtos() {
//        TODO: use jpa projection to dto
        return this.offerRepository.findAll().stream()
                .map(o -> new OfferBasicDto(
                        o.getId()
                        , o.getEngine()
                        , o.getImageUrl()
                        , o.getMilage()
                        , o.getPrice()
                        , o.getTransmission()
                        , o.getYear()
                        , o.getModel().getName()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public OfferDetailsDto getOfferDetailsDto(Long id) {

        return mapToOfferDetailsDto(
                this.offerRepository.findById(id).get());
    }

    @Override
    public Long findSellerIdByOfferId(Long sellerId) {
        return this.offerRepository.findSellerIdByOfferid(sellerId);
    }

    @Override
    public boolean updateOffer(OfferCreateUpdateDto dto, Long offerId) {
        Offer offer = this.offerRepository.findById(offerId).orElseThrow();

        offer.setDescription(dto.getDescription())
                .setPrice(dto.getPrice())
                .setTransmission(dto.getTransmission())
                .setEngine(dto.getEngine())
                .setMilage(dto.getMileage())
                .setModel(
                        this.modelRepository.findById(dto.getModelId())
                                .orElseThrow(() ->
                                        new NotFoundException(ExceptionMessages.MODEL_NOT_FOUND_ID + dto.getModelId()))
                )
                .setModified(LocalDateTime.now())
                .setImageUrl(dto.getImageUrl())
                .setYear(dto.getYear());

        try {
            this.offerRepository.save(offer);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public boolean deleteOffer(Long offerId) {
//        TODO: throw error and handle
        if (this.offerRepository.existsById(offerId)) {
            this.offerRepository.deleteById(offerId);

            return true;
        }

        return false;
    }

    @Override
    public boolean isUsernameOfferSeller(String username, Long offerId) {
        Long offerSellerId = this.offerRepository.findSellerIdByOfferid(offerId);
        Long userId = this.userRepository.findUserByUsername(username)
                .map(BasicEntity::getId)
                .orElse(null);

        return offerSellerId.equals(userId);
    }

    private OfferDetailsDto mapToOfferDetailsDto(Offer o) {
        return new OfferDetailsDto(
                o.getId()
                , o.getDescription()
                , o.getEngine()
                , o.getImageUrl()
                , o.getMilage()
                , o.getPrice()
                , o.getTransmission()
                , o.getYear()
                , o.getCreated()
                , o.getModified()
                , o.getModel().getBrand().getName()
                , o.getModel().getName()
                , o.getModel().getStartYear()
                , o.getModel().getId()
                , o.getSeller().getFirstName() + " " + o.getSeller().getLastName()
        );
    }

    private OfferCreateUpdateDto mapToOfferCreateDto(Offer o) {
        return new OfferCreateUpdateDto()
                .setId(o.getId())
                .setDescription(o.getDescription())
                .setEngine(o.getEngine())
                .setImageUrl(o.getImageUrl())
                .setMileage(o.getMilage())
                .setPrice(o.getPrice())
                .setTransmission(o.getTransmission())
                .setYear(o.getYear())
                .setCreated(o.getCreated())
                .setModified(o.getModified())
                .setModelId(o.getModel().getId())
                .setSellerId(o.getSeller().getId());
    }

    private Offer mapFromOfferCreateDto(OfferCreateUpdateDto dto, String username) {
        return new Offer(
                dto.getDescription()
                , dto.getEngine()
                , dto.getImageUrl()
                , dto.getMileage()
                , dto.getPrice()
                , dto.getTransmission()
                , dto.getYear()
                , LocalDateTime.now()
                , this.modelRepository
                    .findById(dto.getModelId())
                    .orElseThrow(() -> new NotFoundException(ExceptionMessages.MODEL_NOT_FOUND_ID + dto.getModelId()))
                , this.userRepository
                    .findUserByUsername(username)
                    .orElseThrow(() -> new NotFoundException(ExceptionMessages.USERNAME_NOT_FOUND + username)));
    }

}
