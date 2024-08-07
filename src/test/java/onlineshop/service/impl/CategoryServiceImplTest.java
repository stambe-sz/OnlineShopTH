package onlineshop.service.impl;

import onlineshop.model.entity.Category;
import onlineshop.model.service.CategoryServiceModel;
import onlineshop.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class CategoryServiceImplTest {

    @MockBean
    private CategoryRepository categoryRepository;

    @Test
    public void testCreateCategory() {
        ModelMapper modelMapper = new ModelMapper();
        Category category = new Category();
        category.setId(1L);
        category.setCategoryName("Electronics");
        CategoryServiceModel categoryServiceModel = modelMapper.map(category, CategoryServiceModel.class);
        CategoryServiceImpl categoryService = new CategoryServiceImpl(categoryRepository, modelMapper);
        Mockito.when(categoryRepository.findCategoryById(1L)).thenReturn(Optional.of(category));
        Mockito.when(categoryRepository.save(Mockito.any(Category.class))).thenReturn(category);
        boolean result = categoryService.createCategory(categoryServiceModel);
        assert result;
        Mockito.verify(categoryRepository, Mockito.times(1)).save(category);
    }

    @Test
    public void testDeleteCategoryById() {
        ModelMapper modelMapper = new ModelMapper();
        Category category = new Category();
        category.setId(1L);
        category.setCategoryName("Electronics");
        CategoryServiceModel categoryServiceModel = modelMapper.map(category, CategoryServiceModel.class);
        CategoryServiceImpl categoryService = new CategoryServiceImpl(categoryRepository, modelMapper);
        Mockito.when(categoryRepository.findCategoryById(1L)).thenReturn(Optional.of(category));
        boolean result = categoryService.deleteCategoryById(categoryServiceModel.getId());
        assert result;
        Mockito.verify(categoryRepository, Mockito.times(1)).delete(category);
    }
}

