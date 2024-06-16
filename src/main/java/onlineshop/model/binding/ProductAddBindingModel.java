package onlineshop.model.binding;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import onlineshop.model.entity.Category;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ProductAddBindingModel {
    @Size(min = 3,max = 50,message = "Product name must be between 3 and 50 characters.")
    private String name;
    @Size(min = 3,message = "Product description must be more than 3 characters.")
    private String description;
    @Positive
    private int quantity;
    private CategoryBindingModel category;
    private String productCondition;
    private List<String> images;
}
