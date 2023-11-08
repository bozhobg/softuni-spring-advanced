package bg.softuni.pathfinder.model.dto;

import bg.softuni.pathfinder.model.entity.enums.CategoryName;

public class CategoryViewModel {
    private Long id;
    private CategoryName categoryName;

    public CategoryViewModel() {}

    public Long getId() {
        return id;
    }

    public CategoryViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public CategoryName getCategoryName() {
        return categoryName;
    }

    public CategoryViewModel setCategoryName(CategoryName categoryName) {
        this.categoryName = categoryName;
        return this;
    }
}
