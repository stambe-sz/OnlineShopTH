package onlineshop.model.binding;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import onlineshop.model.entity.Category;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ProductAddBindingModel {
    private String name;
    private String description;
    private int quantity;
    private List<Category> category;
    private String productCondition;
    private List<String> images;
}
