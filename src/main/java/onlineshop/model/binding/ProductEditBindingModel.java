package onlineshop.model.binding;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import static onlineshop.constants.RegexValidation.*;

@Getter
@Setter
@NoArgsConstructor
public class ProductEditBindingModel {
    @Pattern(regexp = PRODUCT_NAME_REGEX, message = PRODUCT_NAME_MIN_MAX_ERROR_MSG)
    private String name;
    @Pattern(regexp = PRODUCT_DESCRIPTION_REGEX, message = PRODUCT_DESCRIPTION_MIN_MAX_ERROR_MSG)
    private String description;
    @Positive(message = PRODUCT_QUANTITY_MIN_ERROR_MSG)
    private int quantity;
    @Positive(message = PRODUCT_PRICE_MIN_ERROR_MSG)
    private double price;
    @Valid
    private CategoryBindingModel category;
    @Pattern(regexp = PRODUCT_CONDITION_REGEX, message = PRODUCT_CONDITION_MSG)
    private String productCondition;
    private List<String> images;

    private String photoUrl;
}
