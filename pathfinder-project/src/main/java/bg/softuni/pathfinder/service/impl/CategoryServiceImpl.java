package bg.softuni.pathfinder.service.impl;

import bg.softuni.pathfinder.model.dto.CategoryViewModel;
import bg.softuni.pathfinder.repository.CategoryRepository;
import bg.softuni.pathfinder.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CategoryServiceImpl(
            CategoryRepository categoryRepository,
            ModelMapper modelMapper
    ) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CategoryViewModel> getListCategoryView() {
        return this.categoryRepository.findAll().stream()
                .map(e -> modelMapper.map(e, CategoryViewModel.class))
                .toList();
    }
}
