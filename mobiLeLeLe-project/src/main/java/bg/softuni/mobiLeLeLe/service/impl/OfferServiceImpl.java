package bg.softuni.mobiLeLeLe.service.impl;

import bg.softuni.mobiLeLeLe.model.dto.OfferBasicDto;
import bg.softuni.mobiLeLeLe.model.dto.OfferCreateUpdateDto;
import bg.softuni.mobiLeLeLe.model.dto.OfferDetailsDto;
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
    public boolean addOffer(
            OfferCreateUpdateDto offerCreateUpdateDto
            , Long userId
    ) {
        boolean isAdded = false;

        try {
            this.offerRepository.save(mapFromOfferCreateDto(offerCreateUpdateDto, userId));
            isAdded = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return isAdded;
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

        offer.setDescription(dto.description())
                .setPrice(dto.price())
                .setTransmission(dto.transmission())
                .setEngine(dto.engine())
                .setMilage(dto.mileage())
                .setModel(
                        this.modelRepository.findById(dto.id())
                                .orElseThrow()
                )
                .setModified(LocalDateTime.now())
                .setImageUrl(dto.imageUrl())
                .setYear(dto.year());

        try {
            this.offerRepository.save(offer);
        } catch(Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public boolean deleteOffer(Long offerId) {

        if (this.offerRepository.existsById(offerId)) {
            this.offerRepository.deleteById(offerId);

            return true;
        }

        return false;
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
        return new OfferCreateUpdateDto(
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
                , o.getModel().getId()
                , o.getSeller().getId()
        );
    }

    private Offer mapFromOfferCreateDto(OfferCreateUpdateDto offerCreateUpdateDto, Long userId) {
        return new Offer(
                offerCreateUpdateDto.description()
                , offerCreateUpdateDto.engine()
                , offerCreateUpdateDto.imageUrl()
                , offerCreateUpdateDto.mileage()
                , offerCreateUpdateDto.price()
                , offerCreateUpdateDto.transmission()
                , offerCreateUpdateDto.year()
                , LocalDateTime.now()
                , this.modelRepository
                .findById(offerCreateUpdateDto.modelId())
                .orElseThrow()
                , this.userRepository
                .findById(userId)
                .orElseThrow());
    }

}
