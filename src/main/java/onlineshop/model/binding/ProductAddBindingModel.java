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
    @Column(nullable = false)
    private String name;
    @Column
    private String description;
    @Column(nullable = false)
    private int quantity;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Category> category;
    @Column(name = "product_condition")
    private String productCondition;
    @ElementCollection
    private List<String> images;
}
