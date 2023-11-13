package bg.softuni.mobiLeLeLe.model.dto;

import bg.softuni.mobiLeLeLe.model.enums.Engine;
import bg.softuni.mobiLeLeLe.model.enums.Transmission;
import bg.softuni.mobiLeLeLe.validation.ValidYear;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Year;

public class OfferCreateUpdateDto {
        @PositiveOrZero
        private Long id;
        @NotEmpty
        private String description;
        @PositiveOrZero
        private Engine engine;
        @NotEmpty
        private String imageUrl;
        @Min(0)
        @Max(900000)
        private Integer mileage;
        @PositiveOrZero
        private BigDecimal price;
        @NotEmpty
        private Transmission transmission;
        @ValidYear
        private Integer year;
        private LocalDateTime created;
        private LocalDateTime modified;
        //      model and seller id or dtos ?
//      TODO: get id from option value
        private Long modelId;
        //      TODO: get id from session props
        private Long sellerId;

        public OfferCreateUpdateDto() {}

        public Long getId() {
                return id;
        }

        public OfferCreateUpdateDto setId(Long id) {
                this.id = id;
                return this;
        }

        public String getDescription() {
                return description;
        }

        public OfferCreateUpdateDto setDescription(String description) {
                this.description = description;
                return this;
        }

        public Engine getEngine() {
                return engine;
        }

        public OfferCreateUpdateDto setEngine(Engine engine) {
                this.engine = engine;
                return this;
        }

        public String getImageUrl() {
                return imageUrl;
        }

        public OfferCreateUpdateDto setImageUrl(String imageUrl) {
                this.imageUrl = imageUrl;
                return this;
        }

        public Integer getMileage() {
                return mileage;
        }

        public OfferCreateUpdateDto setMileage(Integer mileage) {
                this.mileage = mileage;
                return this;
        }

        public BigDecimal getPrice() {
                return price;
        }

        public OfferCreateUpdateDto setPrice(BigDecimal price) {
                this.price = price;
                return this;
        }

        public Transmission getTransmission() {
                return transmission;
        }

        public OfferCreateUpdateDto setTransmission(Transmission transmission) {
                this.transmission = transmission;
                return this;
        }

        public Integer getYear() {
                return year;
        }

        public OfferCreateUpdateDto setYear(Integer year) {
                this.year = year;
                return this;
        }

        public LocalDateTime getCreated() {
                return created;
        }

        public OfferCreateUpdateDto setCreated(LocalDateTime created) {
                this.created = created;
                return this;
        }

        public LocalDateTime getModified() {
                return modified;
        }

        public OfferCreateUpdateDto setModified(LocalDateTime modified) {
                this.modified = modified;
                return this;
        }

        public Long getModelId() {
                return modelId;
        }

        public OfferCreateUpdateDto setModelId(Long modelId) {
                this.modelId = modelId;
                return this;
        }

        public Long getSellerId() {
                return sellerId;
        }

        public OfferCreateUpdateDto setSellerId(Long sellerId) {
                this.sellerId = sellerId;
                return this;
        }
}
