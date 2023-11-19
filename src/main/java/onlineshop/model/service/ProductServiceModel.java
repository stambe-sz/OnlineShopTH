package onlineshop.model.service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import onlineshop.model.entity.Category;
import onlineshop.model.entity.Image;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
public class ProductServiceModel extends BaseServiceModel {
    private String name;
    private String description;
    private int quantity;
    private Category category;
    private String productCondition;
    private List<Image> images;
}
