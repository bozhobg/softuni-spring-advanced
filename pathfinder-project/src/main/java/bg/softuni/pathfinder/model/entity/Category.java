package bg.softuni.pathfinder.model.entity;

import bg.softuni.pathfinder.model.entity.enums.CategoryName;
import jakarta.persistence.*;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity{
    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private CategoryName name;
    @Column(columnDefinition = "TEXT")
    private String description;

    public Category() {}

    public CategoryName getName() {
        return name;
    }

    public void setName(CategoryName name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
