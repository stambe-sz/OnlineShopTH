package onlineshop.model.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import onlineshop.model.entity.Category;
import onlineshop.model.entity.Image;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductServiceModel extends BaseServiceModel {
    private String name;
    private String description;
    private int quantity;
    private CategoryServiceModel category;
    private String productCondition;
    private int orderQuantity;
    private List<ImageServiceModel> images;
}
