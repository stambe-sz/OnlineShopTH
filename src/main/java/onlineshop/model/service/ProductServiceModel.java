package onlineshop.model.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductServiceModel extends BaseServiceModel {
	
    private String name;
    private String description;
    private int quantity;
    private int orderQuantity;
    private double price;
    private CategoryServiceModel category;
    private String productCondition;
    private List<ImageServiceModel> images;

    private String photoUrl;
}
