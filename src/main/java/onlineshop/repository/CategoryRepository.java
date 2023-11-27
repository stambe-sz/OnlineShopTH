package onlineshop.repository;

import onlineshop.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    Optional<Category> findByCategoryName(String name);
    Optional<Category> findCategoryById(Long id);
}
