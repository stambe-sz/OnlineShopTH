package onlineshop.service;

import onlineshop.model.service.CategoryServiceModel;

public interface CategoryService {
    CategoryServiceModel getById(Long id);

    boolean createCategory(CategoryServiceModel category);

    boolean updateCategory(CategoryServiceModel category);

    boolean deleteCategoryById(Long id);
}
