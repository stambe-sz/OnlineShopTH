package onlineshop.enums;

import onlineshop.model.entity.Category;

public enum CategoryEnum {

    CLOTHES (new Category("Clothes")),
    FOODS(new Category("Foods")),
    DRINKS(new Category("Drinks"));

    private Category category;

    private CategoryEnum(Category category){
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }
}
