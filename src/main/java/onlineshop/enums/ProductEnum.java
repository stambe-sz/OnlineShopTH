package onlineshop.enums;

import lombok.RequiredArgsConstructor;
import onlineshop.model.entity.Category;
import onlineshop.model.entity.Product;
import onlineshop.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Deprecated
public enum ProductEnum {

    PRODUCT_1(new Product(
            "Jeans",
            "Long Jeans",
            10,
            new Category(
                    CategoryEnum.CLOTHES.getCategory().getCategoryName()
            ),
            ProductConditionEnum.NEW.name(),
            List.of("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ-RT42LL4jv1wT0Gv0r0JxBlN6RGeNs7JM5Q&s",
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQEJzPZKICXFWappGBzucYiCrWDjOKDAqB30g&s")
    )),
    PRODUCT_2(new Product(
            "Cake",
            "cheese cake",
            5,
            new Category(
                    CategoryEnum.FOODS.getCategory().getCategoryName()
            ),
            ProductConditionEnum.NEW.name(),
            List.of("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSufEgNBNhPagyjGrKHIkdTxT4o137J3341tw&s")
            )),
    PRODUCT_3(new Product(
            "Soda",
            "fizzy drink",
            3,
            new Category(
                    CategoryEnum.DRINKS.getCategory().getCategoryName()
            ),
            ProductConditionEnum.NEW.name(),
            List.of("https://www.ebag.bg/products/images/121033/800")
    )),
    PRODUCT_4(new Product(
            "Knife",
            "ceramic knife",
            4,
            new Category(
                    CategoryEnum.HOUSEHOLD.getCategory().getCategoryName()
            ),
            ProductConditionEnum.NEW.name(),
            List.of("https://media-cldnry.s-nbcnews.com/image/upload/newscms/2019_20/1437444/knife-ode-today-main-190515.jpg"))),
    PRODUCT_5(new Product(
            "Brandy",
            "alcoholic drink",
            3,
            new Category(
                    CategoryEnum.DRINKS.getCategory().getCategoryName()
            ),
            ProductConditionEnum.NEW.name(),
            List.of("https://seewines.com/uploads/images/products/grozdova-rakija-min.png")));

    private final Product product;

    ProductEnum(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }
}
