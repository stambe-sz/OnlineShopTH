package onlineshop.model.binding;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static onlineshop.constants.RegexValidation.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItemBindingModel {
    @Pattern(regexp = USERNAME_REGEX, message = USERNAME_REGEX_ERROR_MSG)
    private String username;
    @Positive(message = ID_MIN_MAX_ERROR_MSG)
    private Long productId;
    @Positive(message = PRODUCT_PRICE_MIN_ERROR_MSG)
    private Double price;
    @Positive(message = PRODUCT_QUANTITY_MIN_ERROR_MSG)
    private int quantity;
    @Pattern(regexp = PRODUCT_NAME_REGEX, message = PRODUCT_NAME_MIN_MAX_ERROR_MSG)
    private String name;
}
