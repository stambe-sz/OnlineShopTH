package onlineshop.service.impl;

import lombok.RequiredArgsConstructor;
import onlineshop.model.service.CategoryServiceModel;
import onlineshop.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    @Override
    public CategoryServiceModel getById(Long id) {
        return null;
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
}
