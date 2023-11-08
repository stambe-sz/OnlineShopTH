package onlineshop.service.impl;

import onlineshop.model.service.CategoryServiceModel;
import onlineshop.service.CategoryService;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {

    @Override
    public CategoryServiceModel getById(Long id) {
        return null;
    }

    @Override
    public CategoryServiceModel getByName(String name) {
        return null;
    }

    @Override
    public List<CategoryServiceModel> getAll() {
        return null;
    }

    @Override
    public boolean create(CategoryServiceModel inputCategory) {
        return false;
    }

    @Override
    public boolean update(CategoryServiceModel newCategory) {
        return false;
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }
}
