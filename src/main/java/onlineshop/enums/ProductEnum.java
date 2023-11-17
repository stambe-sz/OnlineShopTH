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
                    CategoryEnum.CLOTHES.getCategory().getCategoryName(),
                    CategoryEnum.CLOTHES.getCategory().getDescription())
            ),
            ProductConditionEnum.NEW.name(),
            List.of("https://", "https://", "https://")
    ));

    private Product product;

    private ProductEnum(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }
}
