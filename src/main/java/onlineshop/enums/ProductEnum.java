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
            List.of("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ-RT42LL4jv1wT0Gv0r0JxBlN6RGeNs7JM5Q&s",
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQEJzPZKICXFWappGBzucYiCrWDjOKDAqB30g&s")
    )),
    PRODUCT_2(new Product(
            "Cake",
            "cheese cake",
            5,
            List.of(new Category(
                    CategoryEnum.FOODS.getCategory().getCategoryName()
            )),
            ProductConditionEnum.NEW.name(),
            List.of("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSufEgNBNhPagyjGrKHIkdTxT4o137J3341tw&s")
            )),
    PRODUCT_3(new Product(
            "Soda",
            "fizzy drink",
            3,
            List.of(new Category(
                    CategoryEnum.DRINKS.getCategory().getCategoryName()
            )),
            ProductConditionEnum.NEW.name(),
            List.of("https://www.ebag.bg/products/images/121033/800")
    )),
    PRODUCT_4(new Product(
            "Knife",
            "ceramic knife",
            4,
            List.of(new Category(
                    CategoryEnum.HOUSEHOLD.getCategory().getCategoryName()
            )),
            ProductConditionEnum.NEW.name(),
            List.of(""))),
    PRODUCT_5(new Product(
            "Brandy",
            "alcoholic drink",
            3,
            List.of(new Category(
                    CategoryEnum.DRINKS.getCategory().getCategoryName()
            )),
            ProductConditionEnum.NEW.name(),
            List.of("")));


    private Product product;

    private ProductEnum(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }
}
