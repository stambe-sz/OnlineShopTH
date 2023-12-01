package onlineshop.service.impl;

import lombok.RequiredArgsConstructor;
import onlineshop.model.entity.Category;
import onlineshop.model.service.CategoryServiceModel;
import onlineshop.repository.CategoryRepository;
import onlineshop.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;
    @Override
    public CategoryServiceModel getById(Long id) {
        Category category = this.categoryRepository
                .findCategoryById(id).orElse(null);
        return modelMapper.map(category,CategoryServiceModel.class);
    }

    @Override
    public boolean createCategory(CategoryServiceModel categoryServiceModel) {
        Category category = modelMapper.map(categoryServiceModel,Category.class);
        this.categoryRepository.save(category);
        this.findCategoryById(category.getId());
        return true;
    }

    @Override
    public boolean updateCategory(CategoryServiceModel category) {
        return false;
    }
    @Override
    public boolean deleteCategoryById(Long id) {
        return false;
    }

    @Override
    public Category findCategoryByName(String categoryName) {
        return this.categoryRepository.findByCategoryName(categoryName).orElse(null);
    }

    private Category findCategoryById(Long id) {
        Category category = this.categoryRepository.findCategoryById(id).orElse(null);
        if (category == null){
            throw new NoSuchElementException();
            //todo change exception
        }
        return category;
    }
}
