package onlineshop.enums;

import onlineshop.model.entity.Category;
import onlineshop.model.entity.Product;

import java.util.List;

public enum ProductEnum {

    PRODUCT_1(new Product(
            "Jeans",
            "Long Jeans",
            10,
            List.of(new Category(
                    CategoryEnum.CLOTHES.getCategory().getCategoryName())
            ),
            ProductConditionEnum.NEW.name(),
            List.of("https://", "https://", "https://")
    )),
    PRODUCT_2(new Product(
            "T-shirt",
            "cotton",
            5,
            List.of(new Category(
                    CategoryEnum.
            )))),
    PRODUCT_3(new Product()),
    PRODUCT_4(new Product()),
    PRODUCT_5(new Product());


    private Product product;

    private ProductEnum(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }
}
