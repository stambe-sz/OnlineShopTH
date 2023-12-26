package onlineshop.utils;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import onlineshop.enums.CategoryEnum;
import onlineshop.enums.RoleEnum;
import onlineshop.model.entity.Category;
import onlineshop.model.entity.Product;
import onlineshop.model.entity.Role;
import onlineshop.repository.CategoryRepository;
import onlineshop.repository.ProductRepository;
import onlineshop.repository.RoleRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Init {

    private final RoleRepository roleRepository;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @PostConstruct
    void loadData() {
        this.loadRoles();
        this.loadCategories();
        //this.loadProducts();
    }
    private void loadProducts() {
        if (this.productRepository.count() == 0) {
            Product product = null;
            for (int i = 0; i < ProductEnum.values().length; i++) {
                product = new Product(ProductEnum.values()[i].getProduct().getName(),
                        ProductEnum.values()[i].getProduct().getDescription(),
                        ProductEnum.values()[i].getProduct().getQuantity(),
                        ProductEnum.values()[i].getProduct().getCategory(),
                        ProductEnum.values()[i].getProduct().getProductCondition(),
                        ProductEnum.values()[i].getProduct().getImages());
                this.productRepository.save(product);
            }
        }
    }

    private void loadRoles() {
        if (this.roleRepository.count() == 0) {
            Role role = null;
            for (int i = 0; i < RoleEnum.values().length; i++) {
                role = new Role(RoleEnum.values()[i].name());
                this.roleRepository.save(role);
            }
        }
    }

    private void loadCategories() {
        if (this.categoryRepository.count() == 0) {
            Category category = null;
            for (int i = 0; i < CategoryEnum.values().length; i++) {
                category = new Category(CategoryEnum.values()[i].name());
                this.categoryRepository.save(category);
            }
        }
    }
}
