package bg.softuni.pathfinder.service;

import bg.softuni.pathfinder.model.dto.CategoryViewModel;

import java.util.List;

public interface CategoryService {
    List<CategoryViewModel> getListCategoryView();
}
