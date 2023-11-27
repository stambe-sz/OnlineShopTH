package onlineshop.service.impl;

import lombok.RequiredArgsConstructor;
import onlineshop.model.entity.Category;
import onlineshop.model.service.CategoryServiceModel;
import onlineshop.repository.CategoryRepository;
import onlineshop.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    @Override
    public CategoryServiceModel getById(Long id) {
        return this.categoryRepository.f;
    }

    @Override
    public boolean createCategory(CategoryServiceModel category) {
        return false;
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
}
