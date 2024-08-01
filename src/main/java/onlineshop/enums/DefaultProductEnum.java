package onlineshop.enums;

import java.util.ArrayList;

import onlineshop.model.service.CategoryServiceModel;
import onlineshop.model.service.ImageServiceModel;
import onlineshop.model.service.ProductServiceModel;

public enum DefaultProductEnum {

    PRODUCT_1(new ProductServiceModel(
            "banichka",
            "banichka sys sirene",
            5,
            -1,
            6.0,
            new CategoryServiceModel("food"),
            ProductConditionEnum.NEW.name(),
            new ArrayList<ImageServiceModel>()));

    private ProductServiceModel product;

    DefaultProductEnum(ProductServiceModel product) {
        this.product = product;
    }

    public ProductServiceModel getProduct() {
        return product;
    }
}
