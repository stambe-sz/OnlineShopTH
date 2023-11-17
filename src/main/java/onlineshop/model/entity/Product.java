package onlineshop.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product extends BaseEntity {

    @Column(nullable = false)
    private String name;
    @Column
    private String description;
    @Column(nullable = false)
    private int quantity;
    @OneToMany
    private List<Category> category;
    @Column(name = "product_condition")
    private String productCondition;
    @ElementCollection
    private List<String> images;
}
