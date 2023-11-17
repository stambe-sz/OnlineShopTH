package onlineshop.enums;

import onlineshop.model.entity.Category;

public enum CategoryEnum {

    CLOTHES (new Category(
            "Clothes",
            "All for the clothes"));

    private Category category;

    private CategoryEnum(Category category){
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }
}
