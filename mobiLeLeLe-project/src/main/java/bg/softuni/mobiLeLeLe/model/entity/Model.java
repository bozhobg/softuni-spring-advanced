package bg.softuni.mobiLeLeLe.model.entity;

import bg.softuni.mobiLeLeLe.model.enums.CategoryEnum;
import jakarta.persistence.*;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

@Entity
@Table(name = "models")
public class Model extends BasicEntity{

    @Column(nullable = false, unique = true)
    private String name;
    @Column
    @Enumerated(EnumType.STRING)
    private CategoryEnum category;
    @Column(name = "image_url")
    @Length(min = 8, max = 512)
    private String imageUrl;
    @Column(name = "start_year"
//            , columnDefinition = "YEAR(4)"
    )
    private Integer startYear;
    @Column(name = "end_year"
//            , columnDefinition = "YEAR(4)"
    )
    private Integer endYear;
    @Basic
    private LocalDateTime created;
    @Column
    private LocalDateTime modified;
    @ManyToOne
    @JoinColumn(name = "brand_id", referencedColumnName = "id")
    private Brand brand;

    public Model() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public void setCategory(CategoryEnum category) {
        this.category = category;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getStartYear() {
        return startYear;
    }

    public void setStartYear(Integer startYear) {
        this.startYear = startYear;
    }

    public Integer getEndYear() {
        return endYear;
    }

    public void setEndYear(Integer endYear) {
        this.endYear = endYear;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }
}
