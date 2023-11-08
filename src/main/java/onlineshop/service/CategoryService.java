package onlineshop.service;

import onlineshop.model.service.CategoryServiceModel;

import java.util.List;

public interface CategoryService {

    CategoryServiceModel getById(Long id);

    CategoryServiceModel getByName(String name);

    List<CategoryServiceModel> getAll();

    boolean create(CategoryServiceModel inputCategory);

    boolean update(CategoryServiceModel newCategory);

    boolean deleteById(Long id);
}
