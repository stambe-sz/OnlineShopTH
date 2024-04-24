package onlineshop.enums;

import onlineshop.model.service.CategoryServiceModel;
import onlineshop.model.service.ImageServiceModel;
import onlineshop.model.service.ProductServiceModel;

import java.util.ArrayList;

public enum DefaultProductEnum {

    PRODUCT_1(new ProductServiceModel(
            "banichka",
            "banichka sys sirene",
            5,
            new CategoryServiceModel("food"),
            ProductConditionEnum.NEW.name(),
            new ArrayList<ImageServiceModel>()))

    ;

    private ProductServiceModel product;

    private DefaultProductEnum(ProductServiceModel product){
        this.product = product;
    }

    public ProductServiceModel getProduct() {
        return product;
    }
}
